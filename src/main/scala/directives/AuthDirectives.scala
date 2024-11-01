package directives

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{Directive1, StandardRoute}
import akka.http.scaladsl.model.headers.OAuth2BearerToken
import models.User
import util.JwtUtil

trait AuthDirectives {
  def authenticated: Directive1[User] = {
    bearerToken.flatMap {
      case Some(token) =>
        JwtUtil.validateToken(token.token) match {
          case Success(claim) => provide(claim.content.parseJson.convertTo[User])
          case Failure(_) => complete(Unauthorized -> "Invalid token")
        }
      case None => complete(Unauthorized -> "No token provided")
    }
  }

  private def bearerToken = 
    optionalHeaderValueByType[OAuth2BearerToken]().map(_.map(_.token))
} 