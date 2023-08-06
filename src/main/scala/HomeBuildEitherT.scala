import HomeBuildEitherT.DivideError.{Indivisible, ZeroDivision}

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}

object HomeBuildEitherT {

  def main(args: Array[String]): Unit = {
    import scala.concurrent.ExecutionContext.Implicits.global
    val fe: FutureEither[DivideError, Int] = for {
      res1 <- FutureEither(divideAsyncEither(12, 2))
      res2 <- FutureEither(divideAsyncEither(res1, 2))
      res3 <- FutureEither(divideAsyncEither(res2, 2))
    } yield res3
    val result = Await.result(fe.value, Duration.Inf)
    println(result)
  }

  sealed trait DivideError
  object DivideError {
    case class Indivisible(num: Int, denom: Int) extends DivideError

    object ZeroDivision extends DivideError
  }

  def divideEither(num: Int, denom: Int): Either[DivideError, Int] = {
    if (denom == 0) Left(Indivisible(num, denom))
    else if (num % denom != 0) Left(ZeroDivision)
    else Right(num / denom)
  }

  def divideAsyncEither(num: Int, denom: Int)(implicit ec: ExecutionContext): Future[Either[DivideError, Int]] = {
    Future(divideEither(num, denom))
  }

  case class FutureEither[A, B](
      value: Future[Either[A, B]]
  ) {

    def map[BB](f: B => BB)(implicit ec: ExecutionContext): FutureEither[A, BB] = {
      FutureEither(value.map(_.map(f))(ec))
    }

    def flatMap[BB](f: B => FutureEither[A, BB])(implicit ec: ExecutionContext): FutureEither[A, BB] = {
      FutureEither(
        value.flatMap {
          case l @ Left(_) => Future.successful(l.asInstanceOf[Either[A, BB]])
          case Right(r) => f(r).value
        }
      )
    }

  }

}
