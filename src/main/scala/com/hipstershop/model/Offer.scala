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
                 startDate: DateTime, endDate: DateTime)
