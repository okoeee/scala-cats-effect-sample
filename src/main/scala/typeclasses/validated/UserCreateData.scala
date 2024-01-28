package typeclasses.validated

/**
 * ユーザー作成時の入力データ
 */
final case class UserCreateData(
    userName: String,
    password: String,
    age: Int
)
