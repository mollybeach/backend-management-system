package util

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.ExceptionHandler
import akka.http.scaladsl.model.StatusCodes
import scala.concurrent.TimeoutException

object ErrorHandler {
  val handler = ExceptionHandler {
    case _: TimeoutException =>
      complete(StatusCodes.RequestTimeout -> "The request timed out")
    
    case ex: IllegalArgumentException =>
      complete(StatusCodes.BadRequest -> ex.getMessage)
    
    case ex: Exception =>
      complete(StatusCodes.InternalServerError -> "An unexpected error occurred")
  }
} 