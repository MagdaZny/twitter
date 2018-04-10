package controllers

import com.google.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}
import services.Shout

class LetShout @Inject() (cc: ControllerComponents, shout: Shout) extends AbstractController(cc)
{
  def answer(userName: String, numberOfTwitts: Int) ={
    val json: Seq[String] = shout.apply(userName, numberOfTwitts)
    val text = json.reduce((l,r) => l + " " + r)
    Action {
      Ok(text)
    }
  }
}

