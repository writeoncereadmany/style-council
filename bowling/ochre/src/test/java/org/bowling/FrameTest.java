package org.bowling;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FrameTest {

    Frame frame = new Frame();

    @Test
    public void frameIsNotCompleteBeforeAnyRolls() {
        assertFalse(frame.isComplete());
    }

    @Test
    public void frameIsCompleteAfterRollingAStrike() {
        frame.roll(10);

        assertTrue(frame.isComplete());
    }

    @Test
    public void frameIsNotCompleteAfterRollingANonStrike() {
        frame.roll(7);

        assertFalse(frame.isComplete());
    }

    @Test
    public void frameIsCompleteAfterRollingTwoBalls() {
        frame.roll(0);
        frame.roll(0);

        assertTrue(frame.isComplete());
    }

    @Test
    public void rejectsRollIfFrameIsComplete() {
        frame.roll(0);
        frame.roll(0);

        assertThrows(FrameComplete.class, () -> frame.roll(0));
    }

    @Test
    public void appliesTwoBonusesIfStrike() {
        frame.roll(10);

        // these two bonuses should be applied
        frame.onRollInOtherFrame(3);
        frame.onRollInOtherFrame(5);
        // but not this one
        frame.onRollInOtherFrame(7);

        assertEquals(18, frame.score());
    }

    @Test
    public void appliesOneBonusIfSpare() {
        frame.roll(5);
        frame.roll(5);

        // this bonus should be applied
        frame.onRollInOtherFrame(3);
        // but not this one
        frame.onRollInOtherFrame(5);

        assertEquals(13, frame.score());
    }

    @Test
    public void cannotRollMoreThan10() {
        assertThrows(TooManyPins.class, () -> frame.roll(11));
    }

    @Test
    public void cannotRollMoreThan10WithTwoBalls() {
        frame.roll(5);
        assertThrows(TooManyPins.class, () -> frame.roll(6));
    }

    @Test
    public void hasOutstandingBonusesAfterASpare() {
        frame.roll(5);
        frame.roll(5);

        assertTrue(frame.hasOutstandingBonuses());
    }
}