package models

case class User(
  id: Option[Long] = None,
  email: String,
  password: String,
  firstName: String,
  lastName: String,
  isActive: Boolean = true
) 