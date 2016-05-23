package com.hipstershop.test

import com.hipstershop.model._

import org.specs2.mutable.Specification

class OfferSpec extends Specification {
  "isEligible method" should {
    "determine how many times an offer can be applied to the shopping cart" >> {
      val apple = Product(name = "apple", price = 1.0)
      val cheapFruit = Offer(
        name = "Best apples in town",
        items = Map((apple -> 2)),
        price = 1.0)
      val shoppingCart = Map((apple -> 4))

      cheapFruit.isEligible(shoppingCart) must beEqualTo(
        (Map((apple -> 0)), 2))
    }

    "return an immuted shopping cart if an offer cannot be applied" >> {
      val tomato = Product(name = "tomatoes", price = 10)
      val notSoCheapFruit = Offer(
        name = "Rotten tomatoes",
        items = Map((tomato-> 10)),
        price = 1.0)
      val shoppingCart = Map((tomato -> 9))

      notSoCheapFruit.isEligible(shoppingCart) must beEqualTo(
        (Map((tomato -> 9)), 0))
    }
  }
}
