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

  // Sequential run
  mbSnake
    .map(snake => Game(board, snake).run(snakeMoves))
    .map(_.fold(showResults("You lose. I'm sorry."), showResults("You reach the target! Yay!")))
    .leftMap(error => println(s"Error: $error"))

  /**
    * To run step by step uncomment the next lines
    */

//  mbSnake
//    .map(snake => Game(board, snake))
//    .map { game =>
//      game.show
//      game.stepAndShow(SnakeLeft).stepAndShow(SnakeRight).stepAndShow(SnakeLeft)
//    }

}
