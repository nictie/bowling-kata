package org.niclem.bowling.impl;

import java.util.ArrayList;

final class FrameRollCalculator extends FrameRollCalculatorAbstract {

    FrameRollCalculator() {

        super(new ArrayList<>());
    }

    @Override
    public boolean isSpare() {

        return rolls.size() == maxRolls && getRollScore() == highScore;
    }

    @Override
    protected int calculateMaxRolls() {

        int result = maxRolls;
        if (isStrike()) {
            result = maxRolls - 1;
        }
        return result;
    }
}
