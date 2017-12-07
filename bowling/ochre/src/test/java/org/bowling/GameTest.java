package org.bowling;

import org.junit.jupiter.api.Test;

import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GameTest {

    Game game = new Game();

    @Test
    public void scoreIsZeroBeforeAnyRolls() {
        assertEquals(0, game.score());
    }

    @Test
    public void aRollWhichKnocksDownPinsIncreasesScore() {
        game.roll(6);

        assertEquals(6, game.score());
    }

    @Test
    public void multipleRollsIncreaseScoreBySumOfPinsKnockedDown() {
        game.roll(6, 3);

        assertEquals(9, game.score());
    }

    @Test
    public void scoresBonusOnSpare() {
        game.roll(5, 5);

        // this one gets a bonus
        game.roll(3);
        // but this one doesn't
        game.roll(2);

        assertEquals(18, game.score());
    }

    @Test
    public void scoresDoubleBonusOnStrike() {
        game.roll(10);

        // these two get a bonus
        game.roll(5);
        game.roll(3);

        // but this one doesn't
        game.roll(7);

        assertEquals(33, game.score());
    }

    @Test
    public void cannotRollBeyondTheEndOfTheGame() {
        range(0, 20).forEach(__ -> game.roll(0));

        assertThrows(GameOver.class, () -> game.roll(0));
    }

    @Test
    public void thePerfectGame() {
        game.roll(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);

        assertEquals(300, game.score());
    }

    @Test
    public void cannotRollBeyondTheEndOfThePerfectGame() {
        game.roll(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);

        assertThrows(GameOver.class, () -> game.roll(0));
    }
}