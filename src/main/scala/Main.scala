import scala.concurrent.Await
import scala.concurrent.duration.Duration

object Main extends App {

  val futureResult1 = EitherSample.divisionProgramAsync("4", "2").value

  val result1 = Await.result(futureResult1, Duration.Inf)

  println(result1)

}
