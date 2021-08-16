package ua.nikitin.snake

import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec
import ua.nikitin.snake.Direction._

class CellSpec extends AnyWordSpec with Matchers {

  "Cell move" should {
    "correct handle zero board" in {
      val board = Board(0,0, List.empty)
      val cell = Cell(0,0)
      val expected = Cell(0,0)

      cell.move(Up, board) mustBe expected
      cell.move(Down, board) mustBe expected
      cell.move(Left, board) mustBe expected
      cell.move(Right, board) mustBe expected
    }

    "correct handle one row/column/cell boards" in {
      val board1 = (Board(1,0, List.empty), Cell(1,0))
      val board2 = (Board(0,1, List.empty), Cell(0,1))
      val board3 = (Board(1,1, List.empty), Cell(1,1))
      val boards = List(board1, board2, board3)

      boards.foreach { case (board, cell) =>
        cell.move(Up, board) mustBe cell
        cell.move(Down, board) mustBe cell
        cell.move(Left, board) mustBe cell
        cell.move(Right, board) mustBe cell
      }
    }

    "correct move cell" in {
      val board = Board(5,5, List.empty)
      val cell = Cell(2,2)

      cell.move(Up, board) mustBe Cell(2,1)
      cell.move(Down, board) mustBe Cell(2,3)
      cell.move(Left, board) mustBe Cell(1,2)
      cell.move(Right, board) mustBe Cell(3,2)
    }

    "correct handle step over the boards" in {
      val board = Board(2,2, List.empty)
      val top = Cell(1,1)
      val bottom = Cell(1,2)
      val left = Cell(1, 1)
      val right = Cell(2, 1)

      top.move(Up, board) mustBe bottom
      bottom.move(Down, board) mustBe top
      left.move(Left, board) mustBe right
      right.move(Right, board) mustBe left

    }
  }
}
