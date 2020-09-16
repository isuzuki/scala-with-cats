package com.github.isuzuki.exercise1

trait Printable[A] {
  def format(a: A): String
}

object Printable {
  implicit val stringPrintable = new Printable[String] {
    override def format(a: String): String = a
  }

  implicit val intPrintable = new Printable[Int] {
    override def format(a: Int): String = a.toString
  }
}

object PrintableSyntax {
  implicit class PrintableOps[A](a: A) {
    def format(implicit p: Printable[A]): String = p.format(a)
    def print(implicit p: Printable[A]): Unit = println(format(p))
  }
}

object PrintableExample extends App {
  import PrintableSyntax._

  def format[A](input: A)(implicit p: Printable[A]): String = p.format(input)
  def print[A](input: A)(implicit p: Printable[A]): Unit = println(format(input))

  print("a")
  print(100)

  "a".print
}
