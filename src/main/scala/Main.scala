import scala.concurrent.{Await, Future}
import scala.util.Try
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

object Main extends App {

  val futureResult1 = EitherSample.divisionProgramAsync("4", "2").value

  val result1 = Await.result(futureResult1, Duration.Inf)

  println(result1)

}
