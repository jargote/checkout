package com.hipstershop.model

import org.joda.time.DateTime

/**
 * This class represents a sale Offer (discount) that could be applied to
 * the total value of a purchase if all items and their quantities are satisfied.
 * .
 * @param name, Human friendly offer name.
 * @param items, Items included in this offer.
 * @param price, sale price.
 * @param startDate, when this offer starts.
 * @param endDate, When this offer expires.
 */
case class Offer(name: String, items: Map[Product, Int], price: Double,
                 startDate: DateTime = DateTime.now(),
                 endDate: DateTime = DateTime.now().plusDays(1)) {

  /**
   * Determines whether an offer could be applied or not to the current
   * shopping cart.
   *
   * @param shoppingCart, products with their respective quantities
   * @param times, how many times this offer could be applied
   * @return Whether this offer applies to items in the shopping cart (T/F) and
   *         updated shopping cart if offer applies, unmodified otherwise
   */
  def isEligible(shoppingCart: Map[Product, Int], times: Int = 0): (Map[Product, Int], Int) = {
    val hasAllItems = (
      items.foldLeft(Seq[Boolean]()) {
        case (valid: Seq[Boolean], (prod: Product, qty: Int)) =>
          valid :+ ((shoppingCart.getOrElse(prod, 0) - qty) >= 0)
      }).forall(x => x)

    if (hasAllItems) {
      isEligible(items.foldLeft(shoppingCart) {
        case (cart: Map[Product, Int], (product: Product, qty)) =>
          cart.updated(product, cart.getOrElse(product, 0) - qty)
      }, times + 1)
    } else (shoppingCart, times)
  }
}
