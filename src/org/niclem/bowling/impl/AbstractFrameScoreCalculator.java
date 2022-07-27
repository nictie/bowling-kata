package org.niclem.bowling.impl;

abstract class AbstractFrameScoreCalculator implements GameScoreUpdater {

    protected final RollScoreCalculator controller;
    private final int number;

    AbstractFrameScoreCalculator(RollScoreCalculator controller, int frameNumber) {

        this.controller = controller;
        this.number = frameNumber;
    }

    @Override
    public void updateScore(GameScoreImpl gameScore) {

        gameScore.updateFrameScore(number, calculateScore());
        controller.updateScore(gameScore);
    }

    protected abstract int calculateScore();

    final void addFrameScoreTo(int ... result) {

        result[0] = result[0] + calculateScore();
    }

    final void addRollScoreTo(int index, int ... result) {

        controller.addRollScoreTo(index, result);
    }
}
