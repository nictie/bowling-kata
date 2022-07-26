package org.niclem.bowling.impl;

import java.util.ArrayList;

public class RollCounter extends AbstractRollCounter {

    public RollCounter() {

        super(new ArrayList<>());
    }

    @Override
    protected boolean isSpare() {

        return rolls.size() == maxRolls && getRollScore() == highScore;
    }

    @Override
    protected int getMaxRolls() {

        int result = maxRolls;
        if (isStrike()) {
            result = maxRolls - 1;
        }
        return result;
    }
}
