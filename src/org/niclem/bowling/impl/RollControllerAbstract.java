package org.niclem.bowling.impl;

import java.util.List;

abstract class RollControllerAbstract implements RollScoreCalculator, RollController {

    protected static final int maxRolls = 2;
    protected static final int highScore = 10;
    static final int spareBonusRoll = 1;
    protected final List<Roll> rolls;

    RollControllerAbstract(List<Roll> rolls) {

        this.rolls = rolls;
    }

    @Override
    public final void updateScore(GameScoreResult gameScoreResult) {

        rolls.forEach(roll -> roll.updateScore(gameScoreResult));
    }

    @Override
    public final boolean addRoll(int hitPins, int frameNumber) {

        boolean result = false;
        if (rolls.size() < calculateMaxRolls()) {
            Roll roll = new Roll(frameNumber, rolls.size() + 1, hitPins);
            result = rolls.add(roll);
        }
        return result;
    }

    protected abstract int calculateMaxRolls();

    @Override
    public final void addRollScoreTo(int[] result, int index) {

        if (rolls.size() < index + 1) {
            return;
        }
        rolls.get(index).addScoreTo(result);
    }

    @Override
    public final void addRollScoreTo(int[] result) {

        rolls.forEach(roll -> roll.addScoreTo(result));
    }

    @Override
    public boolean isStrike() {

        return rolls.size() < maxRolls && getRollScore() == highScore;
    }

    @Override
    public boolean isSpare() {

        return rolls.size() == maxRolls && getRollScore() == highScore;
    }

    @Override
    public boolean isFull() {

        return rolls.size() == calculateMaxRolls();
    }

    final int getRollScore() {

        int[] rollScore = new int[1];
        addRollScoreTo(rollScore);
        return rollScore[0];
    }
}
