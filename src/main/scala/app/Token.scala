package app

import java.nio.charset.StandardCharsets

import com.google.common.io.BaseEncoding
import play.api.libs.json.Json
import play.api.libs.ws.ahc.StandaloneAhcWSClient

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext}

class Token(baseUrl: String)(implicit client: StandaloneAhcWSClient, executionContext: ExecutionContext) {

  import Token._

  def retrieve: String = {

    val response = client.url(baseUrl + "/oauth2/token")
      .withHttpHeaders(("Authorization", s"Basic $getBasicToken()"),
        ("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8"),
        ("Accept-Encoding", "gzip"),
        ("Content-Length", "29"))
      .post("grant_type=client_credentials")
      .map(r => r.status match {
        case 200 => r.body // Applications should verify that the value associated with the token_type key of the returned object is bearer
      })
    retrieveFromJson(Await.result(response, 2.seconds)).as[String]
  }

  private def getBasicToken() = {
    val consumerKey = "j7aJa7DBpELfEPBJtEF4Fo8Nt"
    val consumerSecret = "CO4y4Qt2q9vmByG3ojJ1gG28pPuHgr9zhwoPxweY0vzbI3Ezd3"
    BaseEncoding.base64().encode((consumerKey + ":" + consumerSecret).getBytes(StandardCharsets.UTF_8))
  }
}

object Token {
  def apply(baseUrl: String = "https://api.twitter.com")(implicit client: StandaloneAhcWSClient, executionContext: ExecutionContext) = new Token(baseUrl)(client, executionContext)

  private def retrieveFromJson(result: String) = {
    val j = Json.parse(result)
    (j \ "access_token").get
  }
}
