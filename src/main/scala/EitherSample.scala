import cats.data.EitherT
import cats.syntax.all._

import scala.concurrent.Future
import scala.util.Try
import scala.concurrent.ExecutionContext.Implicits.global

object EitherSample {

  // non EitherT
  def parseDouble(s: String): Future[Either[String, Double]] = {
    Future(Try(s.toDouble).map(Right(_)).getOrElse(Left(s"$s is not a number")))
  }

  def divide(a: Double, b: Double): Future[Either[String, Double]] = {
    Future(Either.cond(b != 0, a / b, "Cannot divide by zero"))
  }

  def divisionProgram(inputA: String, inputB: String): Future[Either[String, Double]] = {
    parseDouble(inputA) flatMap { eitherA =>
      parseDouble(inputB) flatMap { eitherB =>
        (eitherA, eitherB) match {
          case (Right(a), Right(b)) => divide(a, b)
          case (Left(err), _) => Future.successful(Left(err))
          case (_, Left(err)) => Future.successful(Left(err))
        }
      }
    }
  }


  // with EitherT
  def divisionProgramAsync(inputA: String, inputB: String): EitherT[Future, String, Double] = {
    for {
      a <- EitherT(parseDouble(inputA))
      b <- EitherT(parseDouble(inputB))
      result <- EitherT(divide(a, b))
    } yield result
  }

}
