package com.hipstershop.test

import com.hipstershop.model.Transaction
import org.specs2.mutable.Specification


class TransactionSpec extends Specification {
  "getTotal method" should {
    "add up transaction total by multiplying quantity by price if no offers apply" in
      new MainTestScope {
        transactionA.getTotal must equalTo(0.85)
      }

    "apply offers to transaction total" in
      new MainTestScope {
        transactionB.getTotal must beCloseTo(1.7 within 2.significantFigures)

        Transaction(shoppingCart = Map((apple -> 3), (orange -> 4)),
          offers = hipsterShop.offers).getTotal must beCloseTo(
          1.95 within 2.significantFigures)

        Transaction(shoppingCart = Map((apple -> 4), (orange -> 2)),
          offers = hipsterShop.offers).getTotal must beCloseTo(
            1.7 within 2.significantFigures)

        Transaction(shoppingCart = Map((apple -> 8)),
          offers = hipsterShop.offers).getTotal must beCloseTo(
            2.4 within 2.significantFigures)

        Transaction(shoppingCart = Map((orange -> 9)),
          offers = hipsterShop.offers).getTotal must beCloseTo(
            1.5 within 2.significantFigures)
      }
  }
}
