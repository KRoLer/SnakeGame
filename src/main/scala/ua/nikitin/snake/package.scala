package ua.nikitin

package object snake {
  implicit class EitherStepExt(game: Either[Game, Game]) {
    def step(move: SnakeMove): Either[Game, Game] = game.flatMap(_.step(move))

    def stepAndShow(move: SnakeMove): Either[Game, Game] = game.flatMap{g =>
      val result = g.step(move)
      result.fold(showResult, showResult)
      result
    }
  }

  implicit class StepExt(game: Game) {
    def step(move: SnakeMove): Either[Game, Game] = game.step(move)

    def stepAndShow(move: SnakeMove): Either[Game, Game] = {
        val result = game.step(move)
        result.fold(showResult, showResult)
        result
      }
  }

  def showResults(message: String): List[Game] => Unit = { games =>
    games.reverse.foreach (showResult)
    println(message)
  }
  def showResult: Game => Unit = { game =>
      game.moves.headOption.foreach(move => println(s"Move $move"))
      game.show
  }

}
