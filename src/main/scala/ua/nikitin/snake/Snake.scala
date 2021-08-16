package ua.nikitin.snake

import ua.nikitin.snake.Direction.Down
import ua.nikitin.snake.Direction.Up

import scala.math.BigDecimal.RoundingMode

case class Snake(cells: List[Cell], direction: Direction)

object Snake {

  def init(board: Board): Either[String, Snake] = {
    val center = Cell(BigDecimal(board.width / 2.0).setScale(0, RoundingMode.UP).intValue,
                      BigDecimal(board.height / 2.0).setScale(0, RoundingMode.UP).intValue
    )
    val isValidBoard = board.width >= 1 && board.height >= 2
    val isValidMove  = center.move(Down, board) != center

    Either.cond(isValidBoard && isValidMove,
                Snake(cells = List(center, center.move(Down, board)), direction = Up),
                "Board configuration is not suitable for positioning snake"
    )
  }

}
