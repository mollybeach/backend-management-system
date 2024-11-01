import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import com.typesafe.config.ConfigFactory
import routes.ApiRoutes
import services.{AuthService, UserService}
import repository.UserRepository

import scala.concurrent.ExecutionContextExecutor
import scala.util.{Failure, Success}

object App {
  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem(Behaviors.empty, "backend-management-system")
    implicit val executionContext: ExecutionContextExecutor = system.executionContext

    val config = ConfigFactory.load()
    val host = config.getString("server.host")
    val port = config.getInt("server.port")

    // Initialize repositories
    val userRepository = new UserRepository()

    // Initialize services
    val authService = new AuthService(userRepository)
    val userService = new UserService(userRepository)

    // Initialize routes
    val apiRoutes = new ApiRoutes(authService, userService)

    // Start the server
    val bindingFuture = Http().newServerAt(host, port).bind(apiRoutes.routes)

    bindingFuture.onComplete {
      case Success(binding) =>
        val address = binding.localAddress
        system.log.info(s"Server online at http://${address.getHostString}:${address.getPort}/")
      case Failure(ex) =>
        system.log.error("Failed to bind HTTP endpoint, terminating system", ex)
        system.terminate()
    }
  }
} 