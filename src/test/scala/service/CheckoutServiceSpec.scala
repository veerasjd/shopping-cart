package service


import model.Model.{Apple, Fruit, Orange}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class CheckoutServiceSpec extends AnyFlatSpec with Matchers {

  behavior of "CheckoutService"
  it should "return 0 when the basket is empty" in {
    new CheckoutService().checkout(List.empty[Fruit]) should equal("0.00")
  }

  it should "return 2.05 when basket contains 3 apples and 1 orange" in {

      val basket = List(Apple, Apple, Orange, Apple)
      new CheckoutService().checkout(basket) should equal("2.05")
  }

  it should "return 20.50 when checkbox contains 25 Apples and 22 Oranges" in {
     new CheckoutService().checkout(fillBasket(25, 22)) should equal("20.50")
  }

  private def fillBasket(apples: Int, oranges: Int): List[Fruit] = {
    List.fill(apples)(Apple) ::: List.fill(oranges)(Orange)
  }
}
