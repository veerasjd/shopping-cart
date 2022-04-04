package service


import model.Model.{Apple, Banana, Fruit, Orange}
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

  it should "return 0.40 when checkbox contains 2 bananas" in {
    new CheckoutService().checkout(fillBasket(0, 0, 2)) should equal("0.40")
  }

  it should "return 22.50 when checkbox contains 25 Apples and 22 Oranges 10 bananas" in {
    new CheckoutService().checkout(fillBasket(25, 22, 10)) should equal("22.50")
  }

  private def fillBasket(apples: Int, oranges: Int, bananas: Int =0): List[Fruit] = {
    List.fill(apples)(Apple) ::: List.fill(oranges)(Orange) ::: List.fill(bananas)(Banana)
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

  it should " £1.00 when 10 banana with buy one and get one offer on Bananas" in {
    new CheckoutService().checkout(fillBasket(0, 0, 10), applyOffers = true) should equal("1.00")
  }

  it should " £1.00 when 11 banana with buy one and get one offer on Bananas" in {
    new CheckoutService().checkout(fillBasket(0, 0, 11), applyOffers = true) should equal("1.20")
  }

  it should " £3.35 when 2 Apples and 10 Oranges 10 bananas with buy one and get one offer on Apples" in {
    new CheckoutService().checkout(fillBasket(2, 10, 10), applyOffers = true) should equal("3.35")
  }

  it should " £3.60 when 11 banana 11 Apples with buy one and get one offer on Bananas" in {
    new CheckoutService().checkout(fillBasket(11, 0, 11), applyOffers = true) should equal("3.60")
  }

  it should " £4.20 when 11 banana 13 Apples with buy one and get one offer on Bananas" in {
    new CheckoutService().checkout(fillBasket(13, 0, 11), applyOffers = true) should equal("4.20")
  }

  it should " £3.80 when 13 banana 11 Apples with buy one and get one offer on Bananas" in {
    new CheckoutService().checkout(fillBasket(11, 0, 13), applyOffers = true) should equal("3.80")
  }

  it should " £3.80 when 13 banana 22 Apples with buy one and get one offer on 11 Bananas" in {
    new CheckoutService().checkout(fillBasket(22, 0, 12), applyOffers = true) should equal("6.60")
  }

  it should " £3.80 when 13 banana 12 Apples with buy one and get one offer on 22 Bananas" in {
    new CheckoutService().checkout(fillBasket(12, 0, 22), applyOffers = true) should equal("3.80")
  }
}
