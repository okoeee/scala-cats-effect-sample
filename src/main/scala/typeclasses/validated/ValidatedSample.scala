package typeclasses.validated

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

}
