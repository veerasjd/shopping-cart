package model

object Model {

  sealed trait Fruit { def price: BigDecimal }

  case object Apple extends Fruit { override val price: BigDecimal = 60 }

  case object Orange extends Fruit { override val price: BigDecimal = 25 }

  case object Banana extends Fruit { override val price: BigDecimal = 20 }
}


//case class FruitItem(fruit: Fruit, offerApplied: Boolean, discountedProc)