package app.helpers

import scala.util.Random

trait Faker {
  def randomString(size: Int = 10): String = {
    Random.alphanumeric.take(size).mkString("")
  }
  def randomNumber(size: Int = 30): Int = Random.nextInt(size)
}