package com.hipstershop.model

import org.joda.time.DateTime

/**
 * This class represents a Transaction (Shopping Cart).
 *
 * @param cart
 * @param date
 * @param offers
 */
case class Transaction(cart: Map[Product, Int], date: DateTime = DateTime.now(),
                       offers: List[Offer] = List()) {
}
