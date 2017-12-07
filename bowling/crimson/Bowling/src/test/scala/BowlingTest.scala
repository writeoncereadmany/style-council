import org.scalatest.{BeforeAndAfter, FunSuite}
import stylecouncil.Bowling.BowlingGame
import stylecouncil.{Bowling, GameOverException, TooManyPinsDowned}

class BowlingTest extends FunSuite with BeforeAndAfter {
  var b: BowlingGame = _

  before {
    b = new Bowling.BowlingGame()
  }

  test("Current score becomes two after knocking down one pin in each turn") {
    b.downPins(1)
    b.downPins(1)
    assert(b.score == 2)
  }

  test("Cannot knockdown more than ten pins") {
    intercept[TooManyPinsDowned] {
      b.downPins(11)
    }
  }

  test("Cannot knockdown more than ten pins in a roll") {
    intercept[TooManyPinsDowned] {
      b.downPins(2)
      b.downPins(10)
    }
  }

  test("Current score becomes three after knocking down one and two pins") {
    b.downPins(1)
    b.downPins(2)
    assert(b.score == 3)
  }

  test("Should award a bonus of the value of the next roll for a spare") {
    b.downPins(1)
    b.downPins(9)
    b.downPins(2)
    b.downPins(2)
    assert(b.score == 16)
  }

  test("Should award a bonus of the value of the next two rolls for a strike") {
    b.downPins(10)
    b.downPins(2)
    b.downPins(2)
    assert(b.score == 18)
  }

  test("Should keep adding scores until the end") {
    for (_ <- 0 until 10) {
      b.downPins(1)
    }
    assert(b.score == 10)
  }

  test("Should not double the score if 10 sums across two rolls") {
    b.downPins(1)
    b.downPins(8)
    b.downPins(2)
    b.downPins(1)
    assert(b.score == 12)
  }

  test("The perfect game") {
    for (_ <- 0 until 12) {
      b.downPins(10)
    }
    assert(b.score == 300)
    intercept[GameOverException] {
      b.downPins(6)
    }
  }

  test("The game without a bonus shot") {
    for (_ <- 0 until 9) {
      b.downPins(10)
    }
    b.downPins(2)
    b.downPins(5)
    assert(b.score == 256)
    intercept[GameOverException] {
      b.downPins(6)
    }
  }

  test("The game with a bonus shot from a spare") {
    for (_ <- 0 until 9) {
      b.downPins(10)
    }
    b.downPins(5)
    b.downPins(5)
    b.downPins(2)
    assert(b.score == 267)
    intercept[GameOverException] {
      b.downPins(6)
    }
  }

  test("The game with a spare after a strike") {
    for (_ <- 0 until 9) {
      b.downPins(10)
    }
    b.downPins(10)
    b.downPins(5)
    b.downPins(5)
    assert(b.score == 285)
    intercept[GameOverException] {
      b.downPins(6)
    }
  }

}