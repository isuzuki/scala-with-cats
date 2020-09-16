package com.github.isuzuki.exercise1

import cats.Eq
import cats.syntax.eq._

object EqExample extends App {
  implicit val eqCat: Eq[Cat] = Eq.instance[Cat] { (cat1, cat2) =>
    (cat1.name) === (cat2.name) &&
    (cat1.age) === (cat2.age) &&
    (cat1.color) === (cat2.color)
  }

  val cat1 = Cat("tama", 2, "white")
  val cat2 = Cat("mike", 2, "black")

  println(cat1 === cat2)
}
