package com.github.isuzuki.exercise1

import cats.Show
import cats.implicits._

object ShowExample extends App {
  implicit val catShow: Show[Cat] =
    Show.show(cat => s"${cat.name.show} is a ${cat.age.show} yaer-old ${cat.color.show} cat." )

  val cat = Cat("tama", 2, "white")
  println(cat.show)
}
