package services

import models.User
import repository.UserRepository
import scala.concurrent.{ExecutionContext, Future}
import util.HashingUtil

class UserService(userRepository: UserRepository)(implicit ec: ExecutionContext) {
  def createUser(user: User): Future[User] = {
    val hashedPassword = HashingUtil.hashPassword(user.password)
    userRepository.create(user.copy(password = hashedPassword))
  }

  def updateUser(id: Long, user: User): Future[Option[User]] = {
    userRepository.update(id, user)
  }

  def getUser(id: Long): Future[Option[User]] = {
    userRepository.find(id)
  }

  def deleteUser(id: Long): Future[Boolean] = {
    userRepository.delete(id)
  }

  def listUsers(page: Int, pageSize: Int): Future[Seq[User]] = {
    userRepository.list(page, pageSize)
  }
} 