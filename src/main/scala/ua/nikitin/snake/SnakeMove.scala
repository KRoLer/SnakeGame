package ua.nikitin.snake

import enumeratum._


sealed trait SnakeMove extends EnumEntry

object SnakeMove extends Enum[SnakeMove] {
  val values = findValues

  case object Right  extends SnakeMove
  case object Left  extends SnakeMove
}