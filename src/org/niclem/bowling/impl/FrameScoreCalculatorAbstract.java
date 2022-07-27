package org.niclem.bowling.impl;

abstract class FrameScoreCalculatorAbstract implements GameScoreUpdater {

    protected final RollScoreCalculator controller;
    private final int number;

    FrameScoreCalculatorAbstract(RollScoreCalculator controller, int frameNumber) {

        this.controller = controller;
        this.number = frameNumber;
    }

    @Override
    public void updateScore(GameScoreResult gameScoreResult) {

        gameScoreResult.updateFrameScore(number, calculateScore());
        controller.updateScore(gameScoreResult);
    }

    protected abstract int calculateScore();

    final void addFrameScoreTo(int[] result) {

        result[0] = result[0] + calculateScore();
    }

    final void addRollScoreTo(int[] result, int index) {

        controller.addRollScoreTo(result, index);
    }
}
