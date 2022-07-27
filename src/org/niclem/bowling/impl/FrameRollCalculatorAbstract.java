package org.niclem.bowling.impl;

import java.util.List;

public abstract class FrameRollCalculatorAbstract implements ScreenUpdater, RollScoreCalculator, FrameController {

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

    @Override
    public boolean addRoll(int hitPins, int frameNumber) {

        boolean result = false;
        if (rolls.size() < getMaxRolls()) {
            Roll roll = new Roll(frameNumber, rolls.size() + 1, hitPins);
             result = rolls.add(roll);
        }
        return result;
    }

    @Override
    public void addRollScoreTo(int[] result, int index) {

        if (rolls.size() < index + 1) {
            return;
        }
        rolls.get(index).addScoreTo(result);
    }

    @Override
    public void addRollScoreTo(int[] result) {

        rolls.forEach(roll -> roll.addScoreTo(result));
    }

    protected abstract int getMaxRolls();

    @Override
    public boolean isStrike() {

        return rolls.size() < maxRolls && getRollScore() == highScore;
    }

    @Override
    public boolean isFull() {

        return rolls.size() == getMaxRolls();
    }

    int getRollScore() {

        int[] rollScore = new int[1];
        addRollScoreTo(rollScore);
        return rollScore[0];
    }
}
