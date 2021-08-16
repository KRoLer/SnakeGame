package ua.nikitin.snake

import ua.nikitin.snake.Direction.Down
import ua.nikitin.snake.Direction.Left
import ua.nikitin.snake.Direction.Right
import ua.nikitin.snake.Direction.Up

case class Cell(x: Int, y: Int) {

  def move(direction: Direction, board: Board): Cell =
    direction match {
      case Up    => this.copy(y = alignWithBoard(y - 1, board.height))
      case Down  => this.copy(y = alignWithBoard(y + 1, board.height))
      case Left  => this.copy(x = alignWithBoard(x - 1, board.width))
      case Right => this.copy(x = alignWithBoard(x + 1, board.width))
    }

  private def alignWithBoard(point: Int, size: Int): Int =
    if (size <= 1) size else if (point > size) point - size else if (point <= 0) size else point
}
