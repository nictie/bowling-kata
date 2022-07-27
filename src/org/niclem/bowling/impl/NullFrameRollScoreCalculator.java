package org.niclem.bowling.impl;

final class NullFrameRollScoreCalculator implements RollScoreCalculator {

    NullFrameRollScoreCalculator() {

    }

    @Override
    public void updateScore(GameScoreResult gameScoreResult) {

    }

    @Override
    public void addRollScoreTo(int[] result, int index) {

    }

    @Override
    public void addRollScoreTo(int[] result) {

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
