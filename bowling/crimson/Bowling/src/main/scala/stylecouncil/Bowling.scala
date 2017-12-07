package stylecouncil

object Bowling {

  type Frames = List[List[Int]]

  class BowlingGame {

    var frameIdx = 0
    var frames: Frames = List()

    def downPins(pinsDowned: Int): Unit = {
      if (frameIdx > 9) {
        throw new GameOverException()
      }
      if (pinsDowned > 10) {
        throw new TooManyPinsDowned()
      }
      val shouldStartNextRoll = frameIdx >= frames.length

      if (shouldStartNextRoll) {
        frames = frames :+ List(pinsDowned)
        if (isStrike(pinsDowned) && !isBonusShot) {
          frameIdx += 1
        }
      } else {
        val downedMoreThanTenPinsInTheRoll = frames(frameIdx).sum + pinsDowned > 10
        if (!isLastFrame && downedMoreThanTenPinsInTheRoll) {
          throw new TooManyPinsDowned()
        }
        val currentRoll = frames(frameIdx) :+ pinsDowned
        frames = frames.updated(frameIdx, currentRoll)
        if (!isBonusShot) {
          frameIdx += 1
        }
      }
    }

    def isStrike(pinsDowned: Int): Boolean = {
      pinsDowned == 10
    }

    def isLastFrame: Boolean = {
      frameIdx == 9
    }

    def isBonusShot: Boolean = {
      isLastFrame && frames(frameIdx).length <= 2 && frames(frameIdx).sum >= 10
    }

    def score: Int = {
      BowlingScore.scoreGame(frames)
    }


  }

  object BowlingScore {
    def pinsDownInNextShot(frames: Frames, frameIdx: Int): Int = {
      if (frameIdx + 1 < frames.length) {
        frames(frameIdx + 1).head
      } else {
        0
      }
    }

    def pinsDownInShotAfterNext(frames: Frames, frameIdx: Int): Int = {
      if (frameIdx + 1 < frames.length) {
        if (frames(frameIdx + 1).length > 1) {
          frames(frameIdx + 1)(1)
        } else if (frameIdx + 2 < frames.length) {
          frames(frameIdx + 2).head
        } else {
          0
        }
      } else {
        0
      }
    }

    def scoreFrame(frames: Frames, frameIdx: Int): Int = {
      val roll = frames(frameIdx)
      val pinsInNext = pinsDownInNextShot(frames, frameIdx)
      val pinsInAfterNext = pinsDownInShotAfterNext(frames, frameIdx)
      roll match {
        case List(10) => 10 + pinsInNext + pinsInAfterNext
        case List(x, y) if x + y == 10 => x + y + pinsInNext
        case List(x, y) => x + y
        case List(x, y, z) => x + y + z
        case _ => throw new InvalidGameException()
      }
    }

    def scoreGame(frames: Frames): Int = {
      var cumulativeScore = 0
      for (frameIdx <- frames.indices) {
        cumulativeScore += scoreFrame(frames, frameIdx)
      }
      cumulativeScore
    }
  }

}