package com.github.isuzuki.exercise3

trait Printable[A] { self =>
  def format(value: A): String

  def contramap[B](f: B => A): Printable[B] = new Printable[B] {
    override def format(value: B): String = self.format(f(value))
  }
}

final case class Box[A](value: A)

object ContramapExample extends App {
  def format[A](value: A)(implicit p: Printable[A]): String = p.format(value)

  implicit val stringPrintable: Printable[String] =
    new Printable[String] {
      def format(value: String): String =
        s"'${value}'"
    }

  implicit val booleanPrintable: Printable[Boolean] =
    new Printable[Boolean] {
      def format(value: Boolean): String =
        if(value) "yes" else "no"
    }

  implicit def boxPrintable[A](implicit p: Printable[A]): Printable[Box[A]] = p.contramap[Box[A]](_.value)

  format(Box("hello world"))
  // res4: String = "'hello world'"
  format(Box(true))
}
