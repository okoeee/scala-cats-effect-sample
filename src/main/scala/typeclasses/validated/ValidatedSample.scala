package typeclasses.validated

/**
 * ユーザーの新規登録を想定する
 */

object ValidatedSample {

  /**
   * Eitherを使ったバリデーションの実行
   *
   * パスワードと年齢のエラーが出てほしいが、パスワードのエラーしか出ない
   */
  def executeWithEither() = {
    ValidatorWithEither.validateCreateData("user1", "password", 15) match {
      case Right(userCreateData) => println(s"Success: $userCreateData")
      case Left(error) => println(s"Error: ${error.errorMessage}")
    }
  }

}
