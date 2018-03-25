package app

import app.helpers.Faker
import org.mockito.ArgumentMatchers._
import org.mockito.Mockito._
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{Matchers, WordSpec}

import scala.concurrent.Future

class ShoutTest extends WordSpec with Matchers with MockitoSugar with Faker{

  "Shout should format twitts" in {
    val client = mock[TwitterClient]
    when(client.call(any(), any())).thenReturn(Right(Seq("first message", "Second Message")))
    val shout = new Shout(client)

    shout.apply(randomString(), randomString()) should be(Seq("FIRST MESSAGE!", "SECOND MESSAGE!"))
  }
}
