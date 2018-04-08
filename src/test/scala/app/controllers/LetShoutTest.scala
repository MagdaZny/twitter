package app.controllers

import app.Shout
import app.helpers.Test
import org.mockito.Mockito._
import play.api.mvc._
import play.api.test.Helpers._
import play.api.test._

class LetShoutTest extends Test with Results {

  "LetShout should return string with last tweets" in {
    val userName = randomString()
    val numberOfTwitts = randomNumber()
    val twitt1 = randomString()
    val twitt2 = randomString()
    val shout = mock[Shout]
    when(shout.apply(userName, numberOfTwitts)).thenReturn(Seq(twitt1, twitt2))
    val controller = new LetShout(Helpers.stubControllerComponents(), shout)

    val result = controller.answer(userName, numberOfTwitts).apply(FakeRequest())

    status(result) should be(200)
    contentAsString(result) should be(twitt1 + " " + twitt2)
    contentType(result) should be(Some("text/plain"))
    charset(result) should be(Some("utf-8"))
  }
}
