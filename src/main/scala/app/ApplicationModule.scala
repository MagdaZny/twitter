package app

import javax.inject.Singleton

import akka.actor.ActorSystem
import akka.stream.{ActorMaterializer, ActorMaterializerSettings}
import com.google.inject.{AbstractModule, Provides}
import net.codingwell.scalaguice.ScalaModule
import play.api.libs.ws.ahc.StandaloneAhcWSClient

import scala.concurrent.Await
import scala.concurrent.duration._

class ApplicationModule extends AbstractModule with ScalaModule {
  override def configure(): Unit = {
    bind[ActorSystem].toInstance(ActorSystem("actor"))
  }

    @Provides
  def shout(client: TwitterClient) = {
    new Shout(client)
  }

  @Provides
  @Singleton
  def actorMaterializer(system: ActorSystem)={
    val actorMaterializer = ActorMaterializer(
      ActorMaterializerSettings(system)
    )(system)

    scala.sys.addShutdownHook {
      actorMaterializer.system.terminate()
      Await.result(actorMaterializer.system.whenTerminated, 30.seconds)
    }

    actorMaterializer
  }

  @Provides
  @Singleton
  def twitterClient(baseUrl: String,
                    token: String,
                    materializer: ActorMaterializer) = {
    TwitterClient("https://api.twitter.com", tokenClient(baseUrl, materializer).retrieve)(
      StandaloneAhcWSClient()(materializer),
      materializer.executionContext)
  }

  @Provides
  @Singleton
  def tokenClient(baseUrl: String,
                  materializer: ActorMaterializer) = {
    Token()(
      StandaloneAhcWSClient()(materializer),
      materializer.executionContext)
  }
}
