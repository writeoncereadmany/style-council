package com.unrulymedia;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GameTest {
    private Game game;

    @Before
    public void setUp() throws Exception {
        game = new Game();
    }

    @Test
    public void shouldCalculateTheCorrectScoreIfZeroIsThrownTheWholeGame() throws Exception {
        rollMany(20, 0);
        assertThat(game.score(), equalTo(0));
    }

    @Test
    public void shouldCalculateTheCorrectScoreIfOneIsThrownTheWholeGame() {
        rollMany(20, 1);
        assertThat(game.score(), equalTo(20));
    }

    @Test
    public void shouldCalculateTheCorrectScoreIfOneSpareIsThrown() throws Exception {
        game.roll(3);
        game.roll(7);
        game.roll(1);

        rollMany(17, 0);

        assertThat(game.score(), equalTo(12));
    }

    @Test
    public void shouldCalculateTheCorrectScoreIfOneStrikeIsThrown() throws Exception {
        game.roll(10);
        game.roll(3);
        game.roll(5);

        rollMany(17, 0);

        assertThat(game.score(), equalTo(26));
    }

    @Test
    public void shouldCalculateTheCorrectScoreIfTwoStrikesAreThrownInARow() throws Exception {
        game.roll(10);
        game.roll(10);
        game.roll(5);
        game.roll(3);

        rollMany(17, 0);

        assertThat(game.score(), equalTo(51));
    }

    @Test
    public void shouldCalculateTheCorrectScoreIfAStrikeIsThrownInTheTenthFrame() throws Exception {
        rollMany(18, 0);

        game.roll(10);
        game.roll(10);
        game.roll(10);

        assertThat(game.score(), equalTo(30));
    }

    @Test
    public void shouldCalculateTheCorrectScoreIfASpareIsThrownInTheTenthFrame() throws Exception {
        rollMany(18, 0);

        game.roll(1);
        game.roll(9);
        game.roll(10);

        assertThat(game.score(), equalTo(30));
    }

    @Test
    public void shouldCalculateAPerfectGame() throws Exception {
        rollMany(12, 10);

        assertThat(game.score(), equalTo(300));
    }

    private void rollMany(int trys, int pins) {
        for(int i=0; i < trys; i++) {
            game.roll(pins);
        }
    }
}
