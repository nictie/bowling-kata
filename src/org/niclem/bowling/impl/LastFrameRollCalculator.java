package org.niclem.bowling.impl;

import java.util.ArrayList;

final class LastFrameRollCalculator extends FrameRollCalculatorAbstract {

    private boolean isSpare = false;

    LastFrameRollCalculator() {

        super(new ArrayList<>());
    }

    @Override
    public boolean isSpare() {

        boolean result = rolls.size() == maxRolls && getRollScore() == highScore;
        if (result) {
            isSpare = true;
        }
        return isSpare;
    }

    @Override
    protected int calculateMaxRolls() {

        int result = maxRolls;
        if (isSpare()) {
            result = maxRolls + spareBonusRoll;
        }
        return result;
    }
}
