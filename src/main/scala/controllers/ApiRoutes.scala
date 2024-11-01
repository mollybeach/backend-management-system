package controllers

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.model.StatusCodes
import services.{AuthService, UserService}
import models.{User, LoginRequest}
import directives.AuthDirectives
import spray.json.DefaultJsonProtocol._

class ApiRoutes(authService: AuthService, userService: UserService) extends AuthDirectives {
  val routes: Route = {
    pathPrefix("api") {
      concat(
        pathPrefix("auth") {
          concat(
            path("login") {
              post {
                entity(as[LoginRequest]) { request =>
                  onSuccess(authService.authenticate(request.email, request.password)) {
                    case Some(user) => complete(StatusCodes.OK -> Map("token" -> JwtUtil.generateToken(user)))
                    case None => complete(StatusCodes.Unauthorized -> "Invalid credentials")
                  }
                }
              }
            },
            path("register") {
              post {
                entity(as[User]) { user =>
                  onSuccess(userService.createUser(user)) { createdUser =>
                    complete(StatusCodes.Created -> createdUser)
                  }
                }
              }
            }
          )
        },
        pathPrefix("users") {
          authenticated { currentUser =>
            concat(
              get {
                parameters("page".as[Int].optional, "pageSize".as[Int].optional) { (page, pageSize) =>
                  onSuccess(userService.listUsers(page.getOrElse(1), pageSize.getOrElse(10))) { users =>
                    complete(StatusCodes.OK -> users)
                  }
                }
              },
              path(LongNumber) { userId =>
                concat(
                  get {
                    onSuccess(userService.getUser(userId)) {
                      case Some(user) => complete(StatusCodes.OK -> user)
                      case None => complete(StatusCodes.NotFound -> "User not found")
                    }
                  },
                  put {
                    entity(as[User]) { userUpdate =>
                      onSuccess(userService.updateUser(userId, userUpdate)) {
                        case Some(updated) => complete(StatusCodes.OK -> updated)
                        case None => complete(StatusCodes.NotFound -> "User not found")
                      }
                    }
                  },
                  delete {
                    onSuccess(userService.deleteUser(userId)) { deleted =>
                      if (deleted) complete(StatusCodes.NoContent)
                      else complete(StatusCodes.NotFound -> "User not found")
                    }
                  }
                )
              }
            )
          }
        }
      )
    }
  }
}