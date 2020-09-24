package com.github.isuzuki.exercise3

import scala.concurrent.{Future, Await}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

object FutureExample extends App {
  import scala.util.Random

  val future1 = {
    // Initialize Random with a fixed seed:
    val r = new Random(0L)

    // nextInt has the side-effect of moving to
    // the next random number in the sequence:
    val x = Future(r.nextInt)

    for {
      a <- x
      b <- x
    } yield (a, b)
  }

  val future2 = {
    val r = new Random(0L)

    for {
      a <- Future(r.nextInt)
      b <- Future(r.nextInt)
    } yield (a, b)
  }

  val result1 = Await.result(future1, 1.second)
  // result1: (Int, Int) = (-1155484576, -1155484576)
  println(result1)
  val result2 = Await.result(future2, 1.second)
  println(result2)

  import cats.instances.function._ // for Functor
  import cats.syntax.functor._     // for map

  val func1: Int => Double =
    (x: Int) => x.toDouble

  val func2: Double => Double =
    (y: Double) => y * 2

  (func1 map func2)(1)     // composition using map
  // res3: Double = 2.0     // composition using map
  (func1 andThen func2)(1) // composition using andThen
  // res4: Double = 2.0 // composition using andThen
  func2(func1(1))          // composition written out by hand

  import cats.Functor

  println(Functor[List].as(List(1, 2, 3), "hoge"))
}
