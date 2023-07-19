import scala.concurrent.Future

import scala.concurrent.ExecutionContext.Implicits.global

object OptionSample {

  // no OptionT
  val customGreeting = Future.successful(Some("Welcome back"))

  val exitedGreeting = customGreeting.map(_.map(_ + "!"))
  val hasWelcome = customGreeting.map(_.filter(_.contains("Welcome")))
  val noWelcome = customGreeting.map(_.filterNot(_.contains("Welcome")))

}
