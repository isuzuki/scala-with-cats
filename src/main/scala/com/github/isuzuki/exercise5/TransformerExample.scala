package com.github.isuzuki.exercise5

import cats.data.EitherT
import cats.implicits._

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.util.{Failure, Success}

object TransformerExample extends App {
  val powerLevels = Map(
    "Jazz"      -> 6,
    "Bumblebee" -> 8,
    "Hot Rod"   -> 10
  )

  type Response[A] = EitherT[Future, String, A]

  def getPowerLevel(autobot: String): Response[Int] = {
    EitherT {
      Future {
        Either.fromOption(powerLevels.get(autobot), s"Comms error: $autobot unreachable")
      }
    }
  }

  def canSpecialMove(ally1: String, ally2: String): Response[Boolean] =
    for {
      a <- getPowerLevel(ally1)
      b <- getPowerLevel(ally2)
    } yield (a + b) > 15

  def tacticalReport(ally1: String, ally2: String): String = {
    val r = canSpecialMove(ally1, ally2).value.map {
      case Right(v) => if (v) {
        s"$ally1 and $ally2 are ready to roll out!"
      } else {
        s"$ally1 and $ally2 need a recharge."
      }
      case Left(v) => v
    }
    Await.result(r, Duration.Inf)
  }

  // "Jazz"      -> 6,
  // "Bumblebee" -> 8,
  // "Hot Rod"   -> 10
  println(tacticalReport("Jazz", "Bumblebee"))
  println(tacticalReport("Jazz", "Hot Rod"))
  println(tacticalReport("Jazz", "Bumblebee"))
  println(tacticalReport("Hot Rod", "Hot Rod"))
  println(tacticalReport("hoge", "Bumblebee"))
}
