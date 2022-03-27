package service

import model.Model.Fruit

class CheckoutService {

  def checkout(basket: List[Fruit]): String = {
    val totalPrice = basket.map(_.price).sum /100
    "%1.2f".format(totalPrice)
  }
}
