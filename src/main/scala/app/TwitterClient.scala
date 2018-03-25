package app

import play.api.libs.ws.ahc.StandaloneAhcWSClient

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class TwitterClient(baseUrl: String, token: String)(implicit client: StandaloneAhcWSClient) {
  def get(user: String, numberOfTwitts: String): Future[String] = {

    client.url(baseUrl + "/1.1/statuses/user_timeline.json")
      .withQueryStringParameters(("screen_name", user), ("count", numberOfTwitts))
      .addHttpHeaders("Authorization" -> s"Bearer $token")
      .get()
      .map(r => r.body)
  }
}

object TwitterClient {
  def apply(baseUrl: String = "https://api.twitter.com", token: String)(implicit client: StandaloneAhcWSClient): TwitterClient = new TwitterClient(baseUrl, token)(client)
}
