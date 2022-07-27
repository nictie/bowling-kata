package org.niclem.bowling.impl;

import java.util.Collections;

public class NullFrameRollCalculator extends FrameRollCalculatorAbstract {

    public NullFrameRollCalculator() {

        super(Collections.emptyList());
    }

    @Override
    protected int calculateMaxRolls() {

        return 0;
    }

    @Override
    public void updateScore(ScreenModelUpdater screenModelUpdater) {

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
