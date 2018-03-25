package app

import app.helpers.{ClientParameters, Faker}
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock._
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{BeforeAndAfterEach, Matchers, WordSpec}
import scala.concurrent.duration._

class TwitterClientTest extends WordSpec with BeforeAndAfterEach with Matchers with ScalaFutures with Faker with ClientParameters{

  val wireMockServer = new WireMockServer()

  override def beforeEach {
    wireMockServer.start()

  }
  override def afterEach {
    wireMockServer.stop()
  }

  "TwitterClient" should {
    "return json" in {

      val user = randomString()
      val numberOfTwitts = randomNumber().toString
      val token = randomString(30)
      val client = TwitterClient("http://localhost:8080", token)
      val path = s"/1.1/statuses/user_timeline.json?screen_name=$user&count=$numberOfTwitts"

      stubFor(get(urlEqualTo(path))
        .willReturn(
          aResponse()
            .withStatus(200)
            .withBody("ok")))

      whenReady(client.get(user, numberOfTwitts), timeout(2.seconds)){result => result should be("ok")}
    }
  }
}
