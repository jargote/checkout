package com.hipstershop

import com.hipstershop.model._

/**
 * HipsterShop Application
 */
object Main extends App with MainScope {
  /**
   * Application Initialization & Run Loop.
   */
  def run = {
    // Console Menu.
    val mainMenu = s"""
      |Welcome to *** ${hipsterShop.name} *** Shop
      |
      |Select one of the following options:
      |
      |1. Show Cash Total
      |2. Serve New Customer.
      |3. Exit
    """.stripMargin
    val checkoutMenu = """
      |Checkout System:
      |
      |Type list of items to purchase followed by <Enter> key.
    """.stripMargin

    println("Press <Enter> start...")

    io.Source.stdin.getLines().foldLeft(hipsterShop) {
      case (shop: Shop, input: String) =>
        input match {
          case "1" =>
            println(s"*** ${hipsterShop.name} *** CashTotal: £${shop.cashTotal}")
            println(mainMenu)

            shop
          case "2" =>
            println(checkoutMenu)

            shop
          case "3" =>
            System.exit(0)

            shop
          case input =>
            val products = validateProducts(shop, input)

            if (!products.isEmpty) {
              val transaction = Transaction(products, offers = shop.offers)

              println(s"\n >>>>> Total to Pay: £${transaction.getTotal} <<<<")

              val newShop = shop.addTransaction(transaction = transaction)

              println(mainMenu)

              newShop
            } else {
              println(mainMenu)

              shop
            }
        }
    }
  }
  
  /**
   * Helper method that validates product names input via console.
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

  run
}
