package services

import models.User
import repository.UserRepository
import scala.concurrent.{ExecutionContext, Future}

class AuthService(userRepository: UserRepository)(implicit ec: ExecutionContext) {
  def authenticate(email: String, password: String): Future[Option[User]] = {
    userRepository.findByEmail(email).map {
      case Some(user) if validatePassword(password, user.password) => Some(user)
      case _ => None
    }
  }

  private def validatePassword(raw: String, encoded: String): Boolean = {
    // TODO: Implement proper password validation
    raw == encoded
  }
} 