package org.niclem.bowling.impl;

import java.util.List;

abstract class FrameRollCalculatorAbstract implements GameScoreUpdater, RollScoreCalculator, FrameRollController {

    protected static final int maxRolls = 2;
    protected static final int highScore = 10;
    static final int spareBonusRoll = 1;
    protected final List<Roll> rolls;

    FrameRollCalculatorAbstract(List<Roll> rolls) {

        this.rolls = rolls;
    }

    @Override
    public void updateScore(GameScoreResult gameScoreResult) {

        rolls.forEach(roll -> roll.updateScore(gameScoreResult));
    }

    @Override
    public boolean addRoll(int hitPins, int frameNumber) {

        boolean result = false;
        if (rolls.size() < calculateMaxRolls()) {
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

    protected abstract int calculateMaxRolls();

    @Override
    public boolean isStrike() {

        return rolls.size() < maxRolls && getRollScore() == highScore;
    }

    @Override
    public boolean isFull() {

        return rolls.size() == calculateMaxRolls();
    }

    int getRollScore() {

        int[] rollScore = new int[1];
        addRollScoreTo(rollScore);
        return rollScore[0];
    }
}
