package app.integrationTests

import org.scalatestplus.play._
import play.api.Application
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.mvc.Results
import play.api.test.Helpers.{GET => GET_REQUEST, _}
import play.api.test._


class IntegrationTest extends PlaySpec with Results{

  val app: Application = new GuiceApplicationBuilder()
    .build()

  "respond to the index Action" in new App() {
    val Some(result) = route(app, FakeRequest(GET_REQUEST, "/letshout/bob/2"))
    println("the result is: " + result)
    status(result) mustEqual OK
    contentType(result) mustEqual Some("text/html")
    charset(result) mustEqual Some("utf-8")
    contentAsString(result) must include("Helo Bob")
  }
}
