package com.github.isuzuki.exercise3

import cats.Functor
import cats.implicits.catsSyntaxApplicativeId
import cats.instances.function._
import cats.syntax.functor._     // for map

object CatsFunctorExample extends App {
  val func1 = (a: Int) => a + 1
  val func2 = (a: Int) => a * 2
  val func3 = (a: Int) => s"${a}!"
  val func4 = func1 map func2 map func3

  println(func4(123))



  def doMath[F[_]](start: F[Int])
                  (implicit functor: Functor[F]): F[Int] =
    start.map(n => n + 1 * 2)

  import cats.instances.option._ // for Functor
  import cats.instances.list._   // for Functor

  println(doMath(Option(20)))
  // res4: Option[Int] = Some(22)
  println(doMath(List(1, 2, 3)))

  import cats.instances.int._
  import cats._
  println(doMath(100.pure[Id]))
  // res5: List[Int] = List(3, 4, 5)
}
