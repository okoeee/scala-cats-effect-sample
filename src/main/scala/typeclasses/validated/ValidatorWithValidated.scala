package typeclasses.validated

import cats.data.{Validated, ValidatedNec}
import cats.syntax.all._

trait ValidatorWithNec {

  // ValidatedNec[UserCreateError, A] はValidated[NonEmptyChain[UserCreateError], A] のエイリアス
  type ValidationResult[A] = ValidatedNec[UserCreateError, A]

  def validateUserName(userName: String): ValidationResult[String] =
    if (userName.matches("^[a-zA-Z0-9]+$")) userName.validNec
    else UsernameHasSpecialCharacters.invalidNec

  def validatePassword(password: String): ValidationResult[String] =
    if (password.length >= 10) password.validNec
    else PasswordDoesNotMeetCriteria.invalidNec

  def validateAge(age: Int): ValidationResult[Int] =
    if (age >= 18 && age <= 75) age.validNec
    else AgeIsInvalid.invalidNec

  def validateCreateData(userName: String, password: String, age: Int): ValidationResult[UserCreateData] =
    (
      validateUserName(userName),
      validatePassword(password),
      validateAge(age)
    ).mapN(UserCreateData)

}

object ValidatorWithNec extends ValidatorWithNec
