package org.niclem.bowling.impl;

final class LastFrameScoreCalculator extends FrameScoreCalculatorAbstract {

    private final FrameScoreCalculatorAbstract previousFrameScore;

    LastFrameScoreCalculator(RollScoreCalculator rollScoreCalculator, FrameScoreCalculatorAbstract previousFrameScore, int frameNumber) {

        super(rollScoreCalculator, frameNumber);
        this.previousFrameScore = previousFrameScore;
    }

    @Override
    protected int calculateScore() {

        final int[] result = { 0 };
        controller.addRollScoreTo(result);
        previousFrameScore.addFrameScoreTo(result);
        return result[0];
    }
}
