package ua.nikitin.snake

import enumeratum._


sealed trait Direction extends EnumEntry

object Direction extends Enum[Direction] {
  val values = findValues

  case object Down  extends Direction
  case object Up  extends Direction
  case object Right  extends Direction
  case object Left  extends Direction
}