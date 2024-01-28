object Basic {

  /**
   * 型クラスの説明と実装
   * 型クラスとはHaskellに由来するアドホック多相をサポートする型システムである
   *
   * > アドホック多相とは、同じ名前の関数や演算子が型の異なる引数に適用でき、その振る舞いが
   * > 引数の型によって異なるような関数の多様性を示す
   *
   * Catsでは型クラスを構成する重要な要素として以下の4つを挙げている
   *
   * 1. trait
   * 2. implicit value
   * 3. implicit parameter
   * 4. implicit class
   */

  // implicit classを使わないver ----------
  case class UserAccount(
      id: Long,
      name: String,
      password: String
  )

  // trait
  trait Show[A] {
    def show(value: A): String
  }

  // implicit value
  object ShowInstance {
    implicit val userAccountShow: Show[UserAccount] =
      new Show[UserAccount] {
        override def show(value: UserAccount): String =
          s"UserAccount(id = ${value.id}, name = ${value.name}, password = ${value.password})"
      }
  }

  // 具象型クラスをスコープ内で利用できるようにする
  import ShowInstance.userAccountShow

  // implicit parameter
  def printlnAsString[A](value: A)(implicit s: Show[A]) = println(s.show(value))

  val userAccount = UserAccount(1, "user", "password")
  printlnAsString(userAccount)

  // implicit classを使ったver ----------

  object ShowSyntax {
    implicit class ShowOps[A](value: A) {
      def show(implicit s: Show[A]): String = s.show(value)
    }
  }

  import ShowSyntax._

  val userAccount1 = UserAccount(1, "user", "password")
  println(userAccount1.show)

  val list: List[Int] = List(1,3,4,5)

}
