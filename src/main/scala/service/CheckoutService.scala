package service

import model.Model.{Apple, Fruit, Orange}

class CheckoutService {

  def checkout(basket: List[Fruit], applyOffers: Boolean = false): String = {
    val totalPrice = {
      if (applyOffers) applyAllOffers(basket) else basket
    }.map(_.price).sum / 100
    "%1.2f".format(totalPrice)
  }

  private def applyAllOffers(basket: List[Fruit]): List[Fruit] = {
    val basketAfterApplyOffer = applyIndividualOffer(basket, Apple, _ / 2)
    applyIndividualOffer(basketAfterApplyOffer, Orange, _ * 2 / 3)
  }

  private def applyIndividualOffer(basket: List[Fruit], prod: Fruit, offerFormula: Double => Double): List[Fruit] = {
    val splitBasket = basket.partition(_ == prod)
    val items = math.ceil(offerFormula(splitBasket._1.length.toDouble)).toInt
    List.fill[Fruit](items)(prod) ::: splitBasket._2
  }

}
