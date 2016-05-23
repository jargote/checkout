package com.hipstershop

import com.hipstershop.model._

/**
 * Application Scope
 */
trait MainScope {
  // Initializing Products
  val apple = Product(name = "apple", price = 0.6)
  val orange = Product(name = "orange", price = 0.25)

  // Initializing Offers
  val offerA = Offer(
    name = "Buy One, get One free",
    items = Map((apple -> 2)),
    price = 0.6)
  val offerB = Offer(
    name = "Three for the price of Two",
    items = Map((orange -> 3)),
    price = 0.5)

  // Initializing Shop
  val hipsterShop = Shop(name = "Only Gluten Free")
    .updateStock(apple, 50, _ + _)
    .updateStock(orange, 50, _ + _)
    .addOffer(offerA)
    .addOffer(offerB)

  // Dummy Transaction (no offers)
  val transactionA = Transaction(shoppingCart = Map(
    (apple -> 1), (orange -> 1)), offers = hipsterShop.offers)

  // Dummy Transaction (offers apply)
  val transactionB = Transaction(shoppingCart = Map(
    (apple -> 3), (orange -> 3)), offers = hipsterShop.offers)
}
