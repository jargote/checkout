package com.hipstershop.model

/**
 *
 * @param name, shop name
 * @param offers, item offers.
 * @param stock, stock of items available.
 * @param cashTotal, total amount of money in the shop till.
 */
case class Shop(name: String, offers: List[Offer] = List(),
                transactions: List[Transaction] = List(),
                stock: Map[String, (Product, Int)] = Map(),
                cashTotal: Double = 0.0) {
}
