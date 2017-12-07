package co.unruly.bowling;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class BowlingGameTest {

    private BowlingGame bowlingGame = new BowlingGame();

    @Test
    public void score_is_zero_when_no_balls_have_been_rolled() throws Exception {
        assertThat(bowlingGame.score(), is(0));
    }

    @Test
    public void scores_a_gutter_ball() throws Exception {
        givenRolls(0);

        assertThat(bowlingGame.score(), is(0));
    }

    @Test
    public void scores_a_single_pin_roll() throws Exception {
        givenRolls(1);

        assertThat(bowlingGame.score(), is(1));
    }

    @Test
    public void scores_a_frame() throws Exception {
        givenRolls(3,5);

        assertThat(bowlingGame.score(), is(8));
    }

    @Test
    public void scores_a_spare() throws Exception {
        givenRolls(4,6, 1);

        assertThat(bowlingGame.score(), is(12));
    }

    @Test
    public void scores_a_spare_when_next_frame_is_incomplete() throws Exception {
        givenRolls(5,5);

        assertThat(bowlingGame.score(), is(10));
    }

    @Test
    public void scores_a_strike() throws Exception {
        givenRolls(10, 3,2);

        assertThat(bowlingGame.score(), is(20));
    }

    @Test
    public void scores_a_strike_when_next_frame_is_incomplete() throws Exception {
        givenRolls(10, 3);

        assertThat(bowlingGame.score(), is(13));
    }

    @Test
    public void scores_a_game_which_ends_with_a_spare_then_single() throws Exception {
        givenRolls(0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 5,5,2);

        assertThat(bowlingGame.score(), is(12));
    }

    @Test
    public void scores_a_game_which_ends_with_a_spare_then_strike() throws Exception {
        givenRolls(0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 5,5,10);

        assertThat(bowlingGame.score(), is(20));
    }

    @Test
    public void scores_a_game_which_ends_with_a_strike_then_singles() throws Exception {
        givenRolls(0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 10,1,1);

        assertThat(bowlingGame.score(), is(12));
    }

    @Test
    public void scores_a_gutter_game() throws Exception {
        givenRolls(0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0);

        assertThat(bowlingGame.score(), is(0));
    }

    @Test
    public void scores_a_perfect_game() throws Exception {
        givenRolls(10, 10, 10, 10, 10, 10, 10, 10, 10, 10,10,10);

        assertThat(bowlingGame.score(), is(300));
    }

    @Test(expected = GameOverException.class)
    public void enforces_maximum_of_10_frames() throws Exception {
        givenRolls(0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0);
    }

    @Test(expected = InvalidRollException.class)
    public void cannot_knock_down_fewer_than_0_pins() throws Exception {
        givenRolls(-1);
    }

    @Test(expected = InvalidRollException.class)
    public void cannot_knock_down_more_than_ten_pins() throws Exception {
        givenRolls(11);
    }

    private void givenRolls(int... rolls) {
        for (int pinsKnockedDown : rolls) {
            bowlingGame.roll(pinsKnockedDown);
        }
    }
}