package typeclasses.validated

import cats.data.Validated

/**
 * ユーザーの新規登録を想定する
 */

object ValidatedSample {

  /**
   * Eitherを使ったバリデーションの実行
   *
   * パスワードと年齢のエラーが出てほしいが、パスワードのエラーしか出ない
   * (Eitherで複数のバリデーションを行ったときに、最初に失敗したエラーしか出すことが出来ない)
   */
  def executeWithEither() = {
    ValidatorWithEither.validateCreateData("user1", "password", 15) match {
      case Right(userCreateData) => println(s"Success: $userCreateData")
      case Left(error) => println(s"Error: ${error.errorMessage}")
    }
  }

  /**
   * Validatedを使ったバリデーションの実行
   *
   * パスワードと年齢のエラーが出る
   * 実行結果↓
   * Error: Chain(Password must be at least 10 characters long, You must be aged 18 and not older than 75 to use our services)
   */
  def executeWithValidated() = {
    ValidatorWithNec.validateCreateData("User1", "password", 15) match {
      case Validated.Valid(userCreateData) => println(s"Success: $userCreateData")
      case Validated.Invalid(errors) => println(s"Error: ${errors.map(_.errorMessage)}")
    }
  }

}
