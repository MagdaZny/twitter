package app

import java.net.HttpURLConnection._

import play.api.libs.json.{JsValue, Json}
import play.api.libs.ws.ahc.StandaloneAhcWSClient

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext}

class TwitterClient(baseUrl: String, token: String) (implicit client: StandaloneAhcWSClient, ec: ExecutionContext) {

  def call(user: String, numberOfTwitts: Int): Either[String, Seq[String]]=
  {
    val t = client.url(baseUrl + "/1.1/statuses/user_timeline.json")
      .withQueryStringParameters(("screen_name", user), ("count", numberOfTwitts.toString))
      .addHttpHeaders("Authorization" -> s"Bearer $token")
      .get()

    val response = Await.result(t, 2.seconds)

    response.status match {
      case HTTP_OK => Right(getTwitts(response.body))
      case _ => Left("")
    }
  }

  def getTwitts(twitterResponse: String) = {
    val json: JsValue = Json.parse(twitterResponse)
    val twitts =(json \\ "text")
    twitts.map((j: JsValue) => j.as[String])
  }
}

object TwitterClient {
  def apply(baseUrl: String = "https://api.twitter.com", token: String)(implicit client: StandaloneAhcWSClient, executionContext: ExecutionContext): TwitterClient = new TwitterClient(baseUrl, token)(client, executionContext)
}
