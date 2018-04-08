package app

import com.google.inject.Inject

class Shout @Inject() (client: TwitterClient) {

  def apply(user: String, number: Int) = {
    client.call(user, number) match {
      case Right(twitts) => twitts.map(t => t.toUpperCase + "!")
      case _ => ???
    }
  }
}
