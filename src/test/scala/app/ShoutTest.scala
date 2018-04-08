package app

import app.helpers.Test
import org.mockito.ArgumentMatchers._
import org.mockito.Mockito._

class ShoutTest extends Test{

  "Shout should format twitts" in {
    val client = mock[TwitterClient]
    when(client.call(any(), any())).thenReturn(Right(Seq("first message", "Second Message")))
    val shout = new Shout(client)

    shout.apply(randomString(), randomNumber()) should be(Seq("FIRST MESSAGE!", "SECOND MESSAGE!"))
  }
}
