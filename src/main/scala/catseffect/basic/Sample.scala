package catseffect.basic

import cats.effect.{IO, IOApp}

object Sample extends IOApp.Simple {

  val run = IO.println("Hello, World!")

}
