package org.niclem.bowling.impl;

import java.util.List;

abstract class AbstractRollController implements RollScoreCalculator, RollController {

    protected static final int MAX_ROLLS = 2;
    protected static final int HIGH_SCORE = 10;
    protected static final int SPARE_BONUS_ROLL = 1;
    protected final List<Roll> rolls;

    /* package */ AbstractRollController(final List<Roll> rolls) {

        this.rolls = rolls;
    }

    @Override
    public final void updateScore(final GameScoreImpl gameScore) {

        rolls.forEach(roll -> roll.updateScore(gameScore));
    }

    @Override
    public final boolean addRoll(final int hitPins, final int frameNumber) {

        boolean result = false;
        if (rolls.size() < calculateMaxRolls()) {
            final Roll roll = new Roll(frameNumber, rolls.size() + 1, hitPins);
            result = rolls.add(roll);
        }
        return result;
    }

    protected abstract int calculateMaxRolls();

    @Override
    public final void addRollScoreTo(final int index, final int... result) {

        if (rolls.size() < index + 1) {
            return;
        }
        rolls.get(index).addScoreTo(result);
    }

    @Override
    public final void addRollScoreTo(final int ... result) {

        rolls.forEach(roll -> roll.addScoreTo(result));
    }

    @Override
    public boolean isStrike() {

        return rolls.size() < MAX_ROLLS && getRollScore() == HIGH_SCORE;
    }

    @Override
    public boolean isSpare() {

        return rolls.size() == MAX_ROLLS && getRollScore() == HIGH_SCORE;
    }

    @Override
    public boolean isFull() {

        return rolls.size() == calculateMaxRolls();
    }

    protected final int getRollScore() {

        final int[] rollScore = new int[1];
        addRollScoreTo(rollScore);
        return rollScore[0];
    }
}
