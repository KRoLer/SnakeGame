package ua.nikitin.snake

import ua.nikitin.snake.SnakeMove.{Left => SnakeLeft}
import ua.nikitin.snake.SnakeMove.{Right => SnakeRight}
import cats.syntax.either.catsSyntaxEither

object Main extends App {

  val board   = Board(width = 5, height = 5, fruits = List(Cell(1, 2), Cell(5, 4)))
  val mbSnake = Snake.init(board)

  val snakeMoves = List[SnakeMove](SnakeLeft,
                                   SnakeRight,
                                   SnakeLeft,
                                   SnakeRight,
                                   SnakeRight,
                                   SnakeLeft,
                                   SnakeLeft,
                                   SnakeRight,
                                   SnakeLeft,
                                   SnakeRight,
                                   SnakeLeft
  )

  mbSnake
    .map(snake => Game(board, snake).run(snakeMoves))
    .map(_.bimap(showResult("You lose. I'm sorry."), showResult("You reach the target! Yay!")))
    .leftMap(error => println(s"Error: $error"))

  private def showResult(message: String): List[Game] => Unit = { games =>
    games.reverse.foreach { game =>
      game.moves.headOption.foreach(move => println(s"Move $move"))
      game.show
    }
    println(message)
  }

}
