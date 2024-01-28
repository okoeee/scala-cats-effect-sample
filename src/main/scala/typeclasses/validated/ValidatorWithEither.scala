package typeclasses.validated

trait ValidatorWithEither {

  def validateUserName(userName: String): Either[UserCreateError, String] =
    Either.cond(
      userName.matches("^[a-zA-Z0-9]+$"),
      userName,
      UsernameHasSpecialCharacters
    )

  def validatePassword(password: String): Either[UserCreateError, String] =
    Either.cond(
      password.length >= 10,
      password,
      PasswordDoesNotMeetCriteria
    )

  def validateAge(age: Int): Either[UserCreateError, Int] =
    Either.cond(
      age >= 18 && age <= 75,
      age,
      AgeIsInvalid
    )

  def validateCreateData(userName: String, password: String, age: Int): Either[UserCreateError, UserCreateData] =
    for {
      validatedUserName <- validateUserName(userName)
      validatedPassword <- validatePassword(password)
      validatedAge <- validateAge(age)
    } yield UserCreateData(validatedUserName, validatedPassword, validatedAge)

}

object ValidatorWithEither extends ValidatorWithEither
