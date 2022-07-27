package org.niclem.bowling.impl;

abstract class AbstractFrameScoreCalculator implements GameScoreUpdater {

    protected final RollScoreCalculator controller;
    private final int number;

    /* package */ AbstractFrameScoreCalculator(final RollScoreCalculator controller, final  int frameNumber) {

        this.controller = controller;
        this.number = frameNumber;
    }

    @Override
    public void updateScore(final GameScoreImpl gameScore) {

        gameScore.updateFrameScore(number, calculateScore());
        controller.updateScore(gameScore);
    }

    protected abstract int calculateScore();

    protected final void addFrameScoreTo(int ... result) {

        result[0] = result[0] + calculateScore();
    }

    protected final void addRollScoreTo(final int index, final int ... result) {

        controller.addRollScoreTo(index, result);
    }
}
