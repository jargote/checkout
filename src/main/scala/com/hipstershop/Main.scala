package com.hipstershop

import com.hipstershop.model._

/**
 * HipsterShop Application
 */
object Main extends App with MainScope {
  /**
   * Helper method that validates product names input from console.
   *
   * @param shop, Shop model instance.
   * @param input, String representation of a shopping cart.
   * @return Map of Products (key) with their corresponding quantities (value).
   */
  def validateProducts(shop: Shop, input: String): Map[model.Product, Int] = {
    input.split(",").foldLeft(Map[model.Product, Int]()) {
      case (prods: Map[model.Product, Int], name: String)
        if shop.stock.keys.toSeq.contains(name.toLowerCase.trim) =>
        val product = shop.stock.get(name.toLowerCase.trim).get._1

        prods.updated(product, prods.getOrElse(product, 0) + 1)
      case (prods: Map[model.Product, Int], _) => prods
    }
  }
}
