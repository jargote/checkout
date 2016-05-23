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

  /**
   * Adds an offer to the shop
   *
   * @param offer, Products offer.
   * @return An Immutable Shop object
   */
  def addOffer(offer: Offer): Shop = this.copy(offers = offer :: offers)


  /**
   * Adds a new transaction to the shop.
   * @param transaction
   * @return
   */
  def addTransaction(transaction: Transaction): Shop = this.copy(
    transactions = transaction :: transactions,
    cashTotal = cashTotal + transaction.getTotal)

}
