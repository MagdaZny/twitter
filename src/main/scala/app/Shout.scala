package app

import scala.concurrent.Await
import scala.concurrent.duration._

class Shout(client: TwitterClient) {

  def apply(user: String, number: String) = {
    client.call(user, number) match {
      case Right(twitts) => twitts.map(t => t.toUpperCase + "!")
      case _ => "oh no"
    }
  }
}
