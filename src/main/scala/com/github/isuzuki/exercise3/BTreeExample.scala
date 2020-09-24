package com.github.isuzuki.exercise3

import cats.Functor

sealed trait Tree[+A]

final case class Branch[A](left: Tree[A], right: Tree[A])
  extends Tree[A]

final case class Leaf[A](value: A) extends Tree[A]

object BTreeExample extends App {
  implicit val treeFunctor = new Functor[Tree] {
    override def map[A, B](fa: Tree[A])(f: A => B): Tree[B] = {
      fa match {
        case Leaf(v) => Leaf(f(v))
        case Branch(l, r) => Branch(map(l)(f), map(r)(f))
      }
    }
  }

  import cats.implicits._

  val tree:Tree[Int] = Branch(Branch(Branch(Leaf(1), Leaf(2)), Leaf(3)), Leaf(4))
  println(tree.map(_ * 10))
}
