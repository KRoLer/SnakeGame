package ua.nikitin.snake

import org.scalatest.EitherValues
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec
import ua.nikitin.snake.GameSpec.EitherStepExt
import ua.nikitin.snake.SnakeMove.{Left => SnakeLeft}
import ua.nikitin.snake.SnakeMove.{Right => SnakeRight}

class GameSpec extends AnyWordSpec with Matchers with EitherValues {

  "Game" should {

    "step left on an empty cell" in {
      val board = Board(3, 3, List.empty)
      val snake = Snake.init(board).value

      val game   = Game(board, snake)
      val result = game.step(SnakeLeft).value

      result.snake.direction mustBe Direction.Left
      result.snake.cells mustBe List(Cell(1,2), Cell(2,2))
    }
    "step right on an empty cell" in {
      val board = Board(3, 3, List.empty)
      val snake = Snake.init(board).value

      val game   = Game(board, snake)
      val result = game.step(SnakeRight).value

      result.snake.direction mustBe Direction.Right
      result.snake.cells mustBe List(Cell(3,2), Cell(2,2))
    }

    "eat fruit" in {
      val board = Board(3, 3, List(Cell(3,2)))
      val snake = Snake.init(board).value

      val game   = Game(board, snake)
      val result = game.step(SnakeRight).value

      result.snake.direction mustBe Direction.Right
      result.snake.cells mustBe List(Cell(3,2), Cell(2,2), Cell(2,3))
    }

    "handle snake collision" in {
      val board = Board(3, 3, List(Cell(3,2), Cell(3,3)))
      val snake = Snake.init(board).value

      val game   = Game(board, snake)
      val result = game.step(SnakeRight).step(SnakeRight).step(SnakeRight)

      result mustBe 'left
    }

  }
}
object GameSpec {
  implicit class EitherStepExt(game: Either[Game, Game]) {
    def step(move: SnakeMove): Either[Game, Game] = game.flatMap(_.step(move))
  }
}
