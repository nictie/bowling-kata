package org.niclem.bowling.impl;

public class LastFrameScoreCalculator extends FrameScoreCalculatorAbstract {

    private final FrameScoreCalculatorAbstract previousFrameScore;

    public LastFrameScoreCalculator(RollScoreCalculator rollScoreCalculator, FrameScoreCalculatorAbstract previousFrameScore, int frameNumber) {

        super(rollScoreCalculator, frameNumber);
        this.previousFrameScore = previousFrameScore;
    }

    @Override
    public int calculateScore() {

        final int[] result = { 0 };
        controller.addRollScoreTo(result);
        previousFrameScore.addFrameScoreTo(result);
        return result[0];
    }
}
