package org.niclem.bowling.impl;

public abstract class FrameScoreCalculatorAbstract implements ScoreCalculator, ScreenUpdater {

    protected final RollScoreCalculator controller;
    private final int number;

    public FrameScoreCalculatorAbstract(RollScoreCalculator controller, int frameNumber) {

        this.controller = controller;
        this.number = frameNumber;
    }

    public abstract int calculateScore();

    final void addFrameScoreTo(int[] result) {

        result[0] = result[0] + calculateScore();
    }

    final void addRollScoreTo(int[] result, int index) {

        controller.addRollScoreTo(result, index);
    }

    @Override
    public void updateScore(ScreenModelUpdater screenModelUpdater) {

        screenModelUpdater.updateFrameScore(number, calculateScore());
        controller.updateScore(screenModelUpdater);
    }
}
