package ua.nikitin.snake

import org.scalatest.EitherValues
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec


class SnakeSpec extends AnyWordSpec with Matchers with EitherValues {

  "Snake init" should {
    "correct handle not suitable boards" in {
      Snake.init(Board(0, 0, List.empty)) mustBe 'left
      Snake.init(Board(1, 0, List.empty)) mustBe 'left
      Snake.init(Board(0, 1, List.empty)) mustBe 'left
      Snake.init(Board(1, 1, List.empty)) mustBe 'left
      Snake.init(Board(2, 1, List.empty)) mustBe 'left

    }

    "correct positioning snake" in {
      Snake.init(Board(1, 2, List.empty)) mustBe 'right

      val b1 = Board(2, 2, List.empty)
      val s1 = Snake.init(b1)

      s1 mustBe 'right
      s1.value.cells.distinct must have size(2)

      val b2 = Board(5, 5, List.empty)
      val s2 = Snake.init(b2)

      s2 mustBe 'right
      s2.value.cells.distinct must have size(2)
      s2.value.cells.head mustBe Cell(3, 3)
      s2.value.cells.last mustBe Cell(3, 4)
    }
  }
}
