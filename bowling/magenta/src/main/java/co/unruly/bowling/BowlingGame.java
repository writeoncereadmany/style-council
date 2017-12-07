package co.unruly.bowling;

import static co.unruly.bowling.Rules.checkThat;

class BowlingGame {

    private static final int PINS_PER_FRAME = 10;
    private static final int FRAMES_PER_GAME = 10;

    private int[] rolls = new int[21];
    private int currentRoll = 0;

    void roll(int pinsKnockedDown) {
        checkThat(gameIsStillInProgress()).otherwiseThrow(GameOverException::new);
        checkThat(rollIsValid(pinsKnockedDown)).otherwiseThrow(InvalidRollException::new);

        rolls[currentRoll++] = pinsKnockedDown;
    }

    int score() {
        int totalScore = 0;

        for (int frameIndex = 0, frame = 0; frame < FRAMES_PER_GAME; frame++) {
            if (isStrike(frameIndex)) {
                totalScore += scoreForStrike(frameIndex);
                frameIndex += 1;
            } else if (isSpare(frameIndex)) {
                totalScore += scoreForSpare(frameIndex);
                frameIndex += 2;
            } else {
                totalScore += scoreForOpenFrame(frameIndex);
                frameIndex += 2;
            }
        }

        return totalScore;
    }

    private boolean isStrike(int frameIndex) {
        return allPinsKnockedDown(rolls[frameIndex]);
    }

    private boolean isSpare(int frameIndex) {
        return allPinsKnockedDown(rolls[frameIndex] + rolls[frameIndex + 1]);
    }

    private boolean allPinsKnockedDown(int frameScore) {
        return frameScore == PINS_PER_FRAME;
    }

    private int scoreForStrike(int frameIndex) {
        return PINS_PER_FRAME + strikeBonusForFrame(frameIndex);
    }

    private int scoreForSpare(int frameIndex) {
        return PINS_PER_FRAME + spareBonusForFrame(frameIndex);
    }

    private int scoreForOpenFrame(int frameIndex) {
        int score = rolls[frameIndex];
        if (frameIndex + 1 < currentRoll) {
            score += rolls[frameIndex + 1];
        }
        return score;
    }

    private int strikeBonusForFrame(int frameIndex) {
        if (bonusHasBeenRolled(frameIndex)) {
            return rolls[frameIndex + 1] + rolls[frameIndex + 2];
        } else {
            return 0;
        }
    }

    private int spareBonusForFrame(int frameIndex) {
        return rolls[frameIndex + 2];
    }

    private boolean bonusHasBeenRolled(int frameIndex) {
        return frameIndex + 2 < currentRoll;
    }

    private boolean gameIsStillInProgress() {
        return currentRoll < rolls.length;
    }

    private boolean rollIsValid(int pinsKnockedDown) {
        return pinsKnockedDown >= 0 && pinsKnockedDown <= PINS_PER_FRAME;
    }
}
