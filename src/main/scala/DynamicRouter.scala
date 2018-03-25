//import akka.stream.ActorMaterializer
//import app.TwitterClient
//import com.google.inject.{AbstractModule, Provides}
//import play.api.libs.ws.ahc.StandaloneAhcWSClient
//
//import scala.concurrent.ExecutionContext
//
//class DynamicRouter extends AbstractModule{
//  override def configure(): Unit = ()
//
//  @Provides
//  @Singleton
//  def twitterClient(baseUrl: String, token: String)(client: StandaloneAhcWSClient, materializer: ActorMaterializer, executionContext: ExecutionContext)={
//    implicit val materializer = ActorMaterializer
//    implicit val executionContext = materializer.executionContext
//
//    TwitterClient(token=token)(StandaloneAhcWSClient(), materializer, executionContext)
//  }
//}
