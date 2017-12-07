package org.bowling;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.summingInt;
import static java.util.stream.IntStream.range;

public class Game {

    private final List<Frame> frames = range(0, 10).mapToObj(__ -> new Frame()).collect(Collectors.toList());
    private int currentFrameIndex = 0;

    public void roll(int... rolls) {
        for (int pins : rolls) {
            rollBall(pins);
        }
    }

    private void rollBall(int pins) {
        if (currentFrameIndex >= 10 && !frames.stream().anyMatch(Frame::hasOutstandingBonuses)) {
            throw new GameOver();
        }

        frames.forEach(frame -> frame.onRollInOtherFrame(pins));

        if (currentFrameIndex < 10) {

            currentFrame().roll(pins);

            if (currentFrame().isComplete()) {
                currentFrameIndex++;
            }
        }
    }

    public int score() {
        return frames.stream().collect(summingInt(Frame::score));
    }

    private Frame currentFrame() {
        return frames.get(currentFrameIndex);
    }
}
