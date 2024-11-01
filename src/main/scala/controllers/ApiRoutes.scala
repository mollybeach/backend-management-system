package controllers

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import services.{AuthService, UserService}

class ApiRoutes(authService: AuthService, userService: UserService) {
  val routes: Route = {
    pathPrefix("api") {
      concat(
        path("health") {
          get {
            complete("OK")
          }
        },
        // Add more routes here
      )
    }
  }
} 