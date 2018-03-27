package app

import app.helpers.{ClientParameters, Test}
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock._

class TokenTest extends Test with ClientParameters {

  val wireMockServer = new WireMockServer()
  import scala.concurrent.ExecutionContext.Implicits.global

  override def beforeEach {
    wireMockServer.start()
  }
  override def afterEach {
    wireMockServer.stop()
  }


  "should retrieve valid token for given customer credentials" in {
    val token = new Token("http://localhost:8080")
    val randomToken = randomString(30)

    stubFor(post(urlEqualTo("/oauth2/token"))
      .withHeader("Content-Type", matching(".*application/x-www-form-urlencoded;charset=UTF-8.*"))
      .withHeader("Accept-Encoding", matching(".*gzip.*"))
      .withHeader("Authorization", matching(".*Basic.*"))
      .withHeader("Content-Length", matching(".*29.*"))
      .withRequestBody(matching(".*" + "grant_type=client_credentials" + ".*"))

      .willReturn(
        aResponse()
          .withStatus(200)
          .withBody(s"""{\"token_type\":\"bearer\",\"access_token\":\"$randomToken\"}""")))

    token.retrieve should be(randomToken)
  }
}
