package ua.nikitin.snake

import cats.implicits.catsSyntaxApplicativeErrorId
import cats.implicits.catsSyntaxApplicativeId
import ua.nikitin.snake.Direction.Down
import ua.nikitin.snake.Direction.Left
import ua.nikitin.snake.Direction.Right
import ua.nikitin.snake.Direction.Up
import ua.nikitin.snake.SnakeMove.{Left => SnakeLeft}
import ua.nikitin.snake.SnakeMove.{Right => SnakeRight}
import cats.implicits.toFoldableOps
import cats.syntax.either.catsSyntaxEither
import cats.instances.either.catsStdInstancesForEither

case class Game(board: Board, snake: Snake, moves: List[SnakeMove] = List.empty) {

  protected def calculateDirection(move: SnakeMove, direction: Direction): Direction =
    (move, direction) match {
      case (SnakeLeft, Up)    => Direction.Left
      case (SnakeLeft, Down)  => Direction.Right
      case (SnakeLeft, Right) => Direction.Up
      case (SnakeLeft, Left)  => Direction.Down

      case (SnakeRight, Up)    => Direction.Right
      case (SnakeRight, Down)  => Direction.Left
      case (SnakeRight, Right) => Direction.Down
      case (SnakeRight, Left)  => Direction.Up
    }

  def step(move: SnakeMove): Either[Game, Game] = {
    val direction = calculateDirection(move, snake.direction)
    val newHead   = snake.cells.head.move(direction, board)

    if (board.fruits.contains(newHead)) {
      val newSnake  = newHead +: snake.cells
      val newFruits = board.fruits.filterNot(_ == newHead)

      this.copy(board.copy(fruits = newFruits), snake.copy(newSnake, direction), move +: moves).pure
    } else if (snake.cells.contains(newHead))
      this.raiseError
    else {
      val newSnake = newHead +: snake.cells.dropRight(1)

      this.copy(snake = snake.copy(newSnake, direction), moves = move +: moves).pure
    }

  }

  def show: Unit =
    if (board.height > 500 || board.width > 500)
      println("Cannot render the board higher or wider than 500.")
    else {
      val chars = for {
        y <- 1 to board.height
        x <- 1 to board.width

        char = Cell(x, y) match {
                 case cell if board.fruits.contains(cell)                                  => "% "
                 case cell if snake.cells.contains(cell) && snake.cells.indexOf(cell) == 0 => "* "
                 case cell if snake.cells.contains(cell)                                   => "o "
                 case _                                                                    => ". "
               }
      } yield char

      chars.zipWithIndex.foreach {
        case (char, i) if (i + 1) % board.width == 0 => println(char)
        case (char, _) => print(char)
      }
    }

  def run(snakeMoves: List[SnakeMove]): Either[List[Game], List[Game]] =
    snakeMoves
      .foldLeftM(List(this)) {
        case (head :: tail, move) =>
          head.step(move).bimap(fail => fail :: head :: tail, ok => ok :: head :: tail)
      }

}
