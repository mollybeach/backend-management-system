package util

import pdi.jwt.{JwtAlgorithm, JwtSprayJson, JwtClaim}
import spray.json._
import models.User
import com.typesafe.config.ConfigFactory
import scala.util.{Success, Failure, Try}

object JwtUtil {
  private val config = ConfigFactory.load()
  private val secretKey = config.getString("jwt.secret")
  private val algorithm = JwtAlgorithm.HS256
  private val expirationTime = config.getString("jwt.expiration")

  def generateToken(user: User): String = {
    val claim = JwtClaim(
      content = user.toJson.toString,
      expiration = Some(System.currentTimeMillis() / 1000 + 24 * 60 * 60),
      issuedAt = Some(System.currentTimeMillis() / 1000)
    )
    JwtSprayJson.encode(claim, secretKey, algorithm)
  }

  def validateToken(token: String): Try[JwtClaim] = {
    JwtSprayJson.decode(token, secretKey, Seq(algorithm))
  }
} 