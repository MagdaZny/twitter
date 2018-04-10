package app.helpers

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import play.api.libs.ws.ahc.StandaloneAhcWSClient

trait ClientParameters {
  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  implicit val clients = StandaloneAhcWSClient()
}
