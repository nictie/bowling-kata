package org.niclem.bowling.impl;

import java.util.List;

public abstract class FrameRollCalculatorAbstract implements ScreenUpdater, RollScoreCalculator {

    protected static final int maxRolls = 2;
    protected static final int highScore = 10;
    static final int spareBonusRoll = 1;
    protected final List<Roll> rolls;

    public FrameRollCalculatorAbstract(List<Roll> rolls) {

        this.rolls = rolls;
    }

    @Override
    public void updateScore(ScreenModelUpdater screenModelUpdater) {

        rolls.forEach(roll -> roll.updateScore(screenModelUpdater));
    }

    boolean addRoll(int hitPins, int frameNumber) {

        boolean result = false;
        if (rolls.size() < getMaxRolls()) {
            Roll roll = new Roll(frameNumber, rolls.size() + 1, hitPins);
             result = rolls.add(roll);
        }
        return result;
    }

    int getRollScore() {

        int[] rollScore = new int[1];
        addRollScoreTo(rollScore);
        return rollScore[0];
    }

    @Override
    public void addRollScoreTo(int[] result, int index) {

        if (rolls.size() < index + 1) {
            return;
        }
        result[0] = result[0] + rolls.get(index).calculateScore();
    }

    @Override
    public void addRollScoreTo(int[] result) {

        rolls.forEach(roll -> result[0] = result[0] + roll.calculateScore());
    }

    protected abstract int getMaxRolls();

    @Override
    public boolean isStrike() {

        return rolls.size() < maxRolls && getRollScore() == highScore;
    }

    boolean isFull() {

        return rolls.size() == getMaxRolls();
    }
}
