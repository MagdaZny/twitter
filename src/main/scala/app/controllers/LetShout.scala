package app.controllers

import javax.inject.Inject

import app.Shout
import play.api.mvc._


class LetShout @Inject()(cc: ControllerComponents, shout : Shout) extends AbstractController(cc){

  def answer(userName: String, numberOfTwitts: Int) ={
    val json: Seq[String] = shout.apply(userName, numberOfTwitts)
    val text = json.reduce((l,r) => l + " " + r)
    Action {
      Ok(text)
    }
  }
}

