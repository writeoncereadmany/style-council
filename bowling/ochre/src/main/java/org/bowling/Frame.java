package org.bowling;

public class Frame {

    private int balls = 0;
    private int pins = 0;
    private int bonus = 0;
    private int bonusRolls = 0;

    public void roll(int pins) {
        if (isComplete()) {
            throw new FrameComplete();
        }

        if(this.pins + pins > 10) {
            throw new TooManyPins();
        }

        this.balls++;
        this.pins += pins;

        if(this.pins == 10) {
            applyBonuses();
        }
    }

    public boolean isComplete() {
        return pins == 10 || balls == 2;
    }

    private void applyBonuses() {
        if(balls == 1) {
            bonusRolls = 2;
        } else {
            bonusRolls = 1;
        }
    }

    public void onRollInOtherFrame(int pins) {
        if(hasOutstandingBonuses()) {
            bonus += pins;
            bonusRolls--;
        }
    }

    public int score() {
        return pins + bonus;
    }

    public boolean hasOutstandingBonuses() {
        return bonusRolls > 0;
    }
}
