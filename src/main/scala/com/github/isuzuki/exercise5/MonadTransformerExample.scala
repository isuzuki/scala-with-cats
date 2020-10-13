package com.github.isuzuki.exercise5

import cats.Monad
import cats.data.OptionT
import cats.syntax.applicative._ // for pure

object MonadTransformerExample extends App{
  type ListOption[A] = OptionT[List, A]

  val l1 = List(Option(1), Option(2))
  val l2 = List(Option(10), Option(20))

  val aa = l1.pure[ListOption]
  val a = for {
    x <- OptionT(l1)
    y <- OptionT(l2)
  } yield x + y
}
