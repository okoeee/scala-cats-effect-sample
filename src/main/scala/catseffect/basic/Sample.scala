package catseffect.basic

import cats.effect.{IO, IOApp}

object Sample extends App {

  val run = IO.println("Hello, World!")

  val sampleIO1 = IO { println("sample1") }
  val sampleIO2 = IO { println("sample2") }

  val sampleExecution = for {
    _ <- sampleIO1
    _ <- sampleIO2
  } yield ()

  import cats.effect.unsafe.implicits.global
  sampleExecution.unsafeRunSync()

}
