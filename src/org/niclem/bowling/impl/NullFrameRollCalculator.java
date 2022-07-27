package org.niclem.bowling.impl;

import java.util.Collections;

final class NullFrameRollCalculator extends FrameRollCalculatorAbstract {

    NullFrameRollCalculator() {

        super(Collections.emptyList());
    }

    @Override
    protected int calculateMaxRolls() {

        return 0;
    }

    @Override
    public void updateScore(GameScoreResult gameScoreResult) {

    }

    @Override
    public boolean isSpare() {

        return false;
    }

    @Override
    public boolean isStrike() {

        return false;
    }

    @Override
    public boolean addRoll(int hitPins, int frameNumber) {

        return false;
    }

    @Override
    int getRollScore() {

        return 0;
    }

    @Override
    public boolean isFull() {

        return false;
    }

}
