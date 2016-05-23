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
}
