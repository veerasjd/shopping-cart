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

  behavior of "CheckoutService with offers"

  it should " £8.60 when 22 Apples and 12 Oranges with buy one and get one offer on Apples" in {
    new CheckoutService().checkout(fillBasket(22, 12), applyOffers = true) should equal("8.60")
  }

  it should " £2.35 when 2 Apples and 10 Oranges with buy one and get one offer on Apples" in {
    new CheckoutService().checkout(fillBasket(2, 10), applyOffers = true) should equal("2.35")
  }

  it should " £ 6.00 when 35 Oranges with three for two offer on oranges" in {
    new CheckoutService().checkout(fillBasket(0, 35), applyOffers = true) should equal("6.00")
  }

  it should " £3.60 when 2 Apples and 18 Oranges with 2 for 1 apples - three for two offer on oranges" in {
    new CheckoutService().checkout(fillBasket(2, 18), applyOffers = true) should equal("3.60")
  }
}
