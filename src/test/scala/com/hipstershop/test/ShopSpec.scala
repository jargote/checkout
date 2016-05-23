package com.hipstershop.test

import org.specs2.mutable.Specification

class ShopSpec extends Specification {
  "addOffer method" should {
    "add a new shop offer" in new MainTestScope {
      hipsterShop.addOffer(offerA).offers must contain(offerA)
    }

  }

  "addTransaction method" should {
    "add a new transaction" in new MainTestScope {
      hipsterShop.addTransaction(
        transactionA).transactions must contain(transactionA)
    }

      "update cash total" in new MainTestScope {
        hipsterShop.addTransaction(
          transactionA).cashTotal must equalTo(0.85)
      }
    }
  }

}
