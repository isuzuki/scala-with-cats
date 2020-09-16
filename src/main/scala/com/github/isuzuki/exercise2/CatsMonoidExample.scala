package com.github.isuzuki.exercise2

import cats.Monoid
import cats.instances.int._
import cats.syntax.semigroup._

case class Order(totalCost: Double, quantity: Double)

object CatsMonoidExample extends App {
  implicit val orderMonoid = new Monoid[Order] {
    override def empty: Order = Order(0, 0)
    override def combine(x: Order, y: Order): Order = Order(
      x.totalCost + y.totalCost,
      x.quantity + y.quantity
    )
  }

  def add(items: List[Int]): Int = items.foldLeft(Monoid[Int].empty)(_ |+| _)

  // general
  def add[A](items: List[A])(implicit m: Monoid[A]): A = items.foldLeft(m.empty)(_ |+| _)
}
