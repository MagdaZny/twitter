package app

import com.google.inject.Inject

class Shout @Inject() (client: TwitterClient) {

  def apply(user: String, number: String) = {
    println("client calls")
    client.call(user, number) match {
      case Right(twitts) => twitts.map(t => t.toUpperCase + "!")
      case _ => "oh no"
    }
  }
}
