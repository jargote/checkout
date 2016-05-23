package com.hipstershop.model

import org.joda.time.DateTime

/**
 * This class represents a Transaction (Shopping Cart).
 *
 * @param shopppingCart
 * @param date
 * @param offers
 */
case class Transaction(shoppingCart: Map[Product, Int], date: DateTime = DateTime.now(),
                       offers: List[Offer] = List()) {

  /**
   * Calculates the total amount to pay for this products.
   *
   * @return purchase total
   */
  def getTotal: Double = {
    val (updatedCart: Map[Product, Int], oTotal: Double) = {
      offers.foldLeft((shoppingCart, 0.0)) {
        case ((cart: Map[Product, Int], oTotal: Double), offer: Offer) =>
          val (updatedCart, oTimes) = offer.isEligible(cart)

          (updatedCart, oTotal + offer.price * oTimes)
      }
    }

    // Calculating total for remiaining items in shopping cart.
    oTotal + updatedCart.foldLeft(0.0) {
      case (acc: Double, (prod: Product, qty: Int)) => acc + (prod.price * qty)
    }
  }
}
