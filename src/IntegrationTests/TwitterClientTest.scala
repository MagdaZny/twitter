package app

import org.scalatest.WordSpec
import play.api.libs.ws.ahc.StandaloneAhcWSClient

class TwitterClientTest extends WordSpec{

  "App should shout" in {

    val shout = new Shout(new StandaloneAhcWSClient)
    shout(twitterapi, 3) should be(Seq("ANNOUNCING A NEW API STATUS PAGE -&GT; HTTPS://T.CO/VHJRLJJPHO!", "RT @TWITTERDEV: WE’RE EXCITED TO ANNOUNCE A CHANGE TO DIRECT MESSAGE RATE LIMITS THAT GIVES THOSE USING THE APIS THE ABILITY TO RESPOND TO…!", "WE’RE EXCITED TO ANNOUNCE A CHANGE TO DIRECT MESSAGE RATE LIMITS THAT GIVES THOSE USING THE APIS THE ABILITY TO RES… HTTPS://T.CO/SMJVPCE2NM!"))
  }
}
