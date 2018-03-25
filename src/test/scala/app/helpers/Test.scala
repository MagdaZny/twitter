package app.helpers

import org.scalatest.{BeforeAndAfterEach, Matchers, WordSpec}
import org.scalatest.mockito.MockitoSugar

/**
  * Created by magdazielezny on 25/03/2018.
  */
trait Test extends WordSpec with Matchers with MockitoSugar with Faker with BeforeAndAfterEach{

}
