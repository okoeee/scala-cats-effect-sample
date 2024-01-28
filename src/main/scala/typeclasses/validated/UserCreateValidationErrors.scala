package typeclasses.validated

sealed trait UserCreateError {
  def errorMessage: String
}

case object UsernameHasSpecialCharacters extends UserCreateError {
  def errorMessage: String = "Username cannot contain special characters"
}

case object PasswordDoesNotMeetCriteria extends UserCreateError {
  def errorMessage: String = "Password must be at least 10 characters long"
}

case object AgeIsInvalid extends UserCreateError {
  def errorMessage: String = "You must be aged 18 and not older than 75 to use our services"
}
