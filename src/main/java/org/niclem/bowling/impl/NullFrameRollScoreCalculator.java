package org.niclem.bowling.impl;

final class NullFrameRollScoreCalculator implements RollScoreCalculator {

    @Override
    public void updateScore(final GameScoreImpl gameScore) {

    }

    @Override
    public void addRollScoreTo(final int index, final int... result) {

    }

    @Override
    public void addRollScoreTo(final int... result) {

    }

    @Override
    public boolean isSpare() {

        return false;
    }

    @Override
    public boolean isStrike() {

        return false;
    }
}
