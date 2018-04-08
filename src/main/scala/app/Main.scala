package app

import com.google.inject.Guice
import net.codingwell.scalaguice.InjectorExtensions._


object Main extends App {

  val injector = Guice.createInjector(new ApplicationModule)
  val app = injector.instance[Shout]

  val shouted = app.apply("PutinRF_Eng", 4)
  println(shouted)
}
