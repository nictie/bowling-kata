package org.niclem.bowling.impl;

import java.util.ArrayList;

public class LastFrameRollCalculator extends FrameRollCalculatorAbstract {

    private boolean isSpare = false;

    public LastFrameRollCalculator() {

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
    protected int getMaxRolls() {

        int result = maxRolls;
        if (isSpare()) {
            result = maxRolls + spareBonusRoll;
        }
        return result;
    }
}
