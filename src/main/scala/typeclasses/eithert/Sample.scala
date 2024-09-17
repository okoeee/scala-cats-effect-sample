package typeclasses.eithert

import cats.data.{EitherT, OptionT}
import cats.implicits._

/**
 * 型クラス(Functor, Monad, Applicative)などのインスタンスがスコープ内で利用できるようにするため
 *
 * 型クラス:
 * 特定の振る舞いを定義するインターフェース
 * 例えば、Functorはある方に対してmap操作ができるかどうかを定義している
 */

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object Sample {

  def exec: EitherT[Future, String, Unit] =
    for {
      // 複雑なことをする際に型パラメータを指定しないとcompile errorが出る
      _ <- EitherT.right[String](Future.successful(42))
      _ <- EitherT.left[String](Future.successful("Error"))
      _ <- EitherT.rightT[Future, String](1)
      _ <- EitherT.fromOption[Future](Option(1), "Error")
      _ <- EitherT.fromOptionM[Future, String, Int](Future.successful(Option(2)), Future.successful("Error"))
      _ <- EitherT.fromOptionF[Future, String, Int](Future.successful(Option(2)), "Error")
    } yield ()

}
