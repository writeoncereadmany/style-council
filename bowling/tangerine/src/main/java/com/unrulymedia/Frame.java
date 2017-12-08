package com.unrulymedia;

public class Frame {
    private int score = 0;
    private int rollCount = 0;
    private int firstRoll;
    private int frameNumber;


    public Frame(int frameNumber) {

        this.frameNumber = frameNumber;
    }

    public void roll(int pins) {
        if (rollCount == 0) {
            firstRoll = pins;
        }
        score += pins;
        rollCount++;
    }

    public int frameNumber() {
        return frameNumber;
    }

    public boolean isComplete() {
        return rollCount >= 2 || isStrike();
    }

    public boolean isStrike() {
        return rollCount == 1 && score == 10;
    }

    public boolean isSpare() {
        return rollCount == 2 && score == 10;
    }

    public int getScore() {
        return score;
    }

    public int getFirstRoll() {
        return firstRoll;
    }
}
