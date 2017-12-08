package com.unrulymedia;

import java.util.LinkedList;

public class Game {

    private LinkedList<Frame> frames = new LinkedList<>();

    public void roll(int pins) {
        getNonFinishedFrame().roll(pins);
    }

    public int score() {
        int totalScore = 0;
        Frame previousFrame = null;
        Frame twoFramesAgo = null;

        for (Frame frame : frames) {
            if (twoFramesAgo != null && twoFramesAgo.isStrike() && previousFrame.isStrike() && twoFramesAgo.frameNumber() < 10) {
                totalScore += frame.getFirstRoll();
            }

            if (previousFrame != null && previousFrame.isStrike() && previousFrame.frameNumber() < 10) {
                totalScore += frame.getScore();
            }

            if (previousFrame != null && previousFrame.isSpare()) {
                totalScore += frame.getFirstRoll();
            }

            totalScore += frame.getScore();

            twoFramesAgo = previousFrame;
            previousFrame = frame;
        }

        return totalScore;
    }

    private Frame getNonFinishedFrame() {
        if (frames.peekLast() == null || frames.getLast().isComplete()) {
            frames.addLast(new Frame(frames.size() + 1));
        }

        return frames.getLast();
    }
}
