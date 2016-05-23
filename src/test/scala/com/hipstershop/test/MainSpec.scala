package com.hipstershop.test

import com.hipstershop._

import org.specs2.mutable.Specification

class MainSpec extends Specification {
  "validateProducts method" should {
    "create a map of products from an input string" in new MainTestScope {
      Main.validateProducts(hipsterShop, "apple,orange")
    }

    "be case insensitive" in new MainTestScope {
      Main.validateProducts(hipsterShop,
        "Apple,ORANGE") must haveKeys(apple, orange)
    }

    "ignore spaces" in new MainTestScope {
      Main.validateProducts(hipsterShop,
        "apple, orange") must haveKeys(apple, orange)
    }

    "aggregate multiple occurrences" in new MainTestScope {
      Main.validateProducts(hipsterShop,
        "apple, orange, appale, orange, orange") must haveValues(1, 3)
    }
  }
}
