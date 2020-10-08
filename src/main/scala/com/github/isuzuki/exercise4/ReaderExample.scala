package com.github.isuzuki.exercise4

import cats.data.Reader
import cats.implicits._

// object ReaderExample  extends App {
//
//   final case class Db(
//                        usernames: Map[Int, String],
//                        passwords: Map[String, String]
//                      )
//
//   type DbReader[A] = Reader[Db, A]
//
//   def findUsername(userId: Int): DbReader[Option[String]] =
//     Reader(db => db.usernames.get(userId))
//
//   def checkPassword(
//                      username: String,
//                      password: String): DbReader[Boolean] =
//     Reader(db => db.passwords.get(username).contains(password))
//
//   def checkLogin(
//                   userId: Int,
//                   password: String): DbReader[Boolean] =
//     for {
//       username <- findUsername(userId)
//       r <- username.map(u => checkPassword(u, password)).getOrElse(Reader(_ => false))
//     } yield r
// }
//