package service

import model.Model.{Apple, Banana, Fruit, Orange}

class CheckoutService {

  def checkout(basket: List[Fruit], applyOffers: Boolean = false): String = {
    val totalPrice = {
      if (applyOffers) applyAllOffers(basket) else basket
    }.map(_.price).sum / 100
    "%1.2f".format(totalPrice)
  }

  private def applyAllOffers(basket: List[Fruit]): List[Fruit] = {
    val basketAfterApplyOffer = applyIndividualOffer(basket, Apple, _ / 2)
    val basket1 = applyIndividualOffer(basketAfterApplyOffer, Orange, _ * 2 / 3)
    val basket2 = applyIndividualOffer(basket1, Banana, _ / 2)
    applyIndividualOfferMultiFruit(basket2, Apple, Banana)
  }
/*
Step 3: Add Bananas
  Price of Banana is 20p.
  • Bananas are on buy one get one offer.
  • When Bananas are bought together with Apple cheapest one is
  free.
   */
  private def applyIndividualOfferMultiFruit(basket: List[Fruit], prod1: Fruit, prod2: Fruit): List[Fruit] = {
    val basketWithGivenProds = basket.partition(a => a == prod1 || a == prod2 ) //List[Apple|Banana] , List[RestOfF]
    val applesBananasInBasket = basketWithGivenProds._1.partition(_ == prod1) //List[Apple] , List[Banana]
    val numberOfApples = applesBananasInBasket._1.size
    val numberOfBananas = applesBananasInBasket._2.size
    val newNumberOfBananas = numberOfBananas - numberOfApples
    List.fill[Fruit](numberOfApples)(prod1) :::  List.fill[Fruit](newNumberOfBananas)(prod2) ::: basketWithGivenProds._2
  }

  private def applyIndividualOffer(basket: List[Fruit], prod: Fruit, offerFormula: Double => Double): List[Fruit] = {
    val splitBasket = basket.partition(_ == prod)
    val items = math.ceil(offerFormula(splitBasket._1.length.toDouble)).toInt
    List.fill[Fruit](items)(prod) ::: splitBasket._2
  }

}
