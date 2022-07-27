package org.niclem.bowling.impl;

final class LastFrameScoreCalculator extends AbstractFrameScoreCalculator {

    private final AbstractFrameScoreCalculator previousFrameScore;

    /* package */ LastFrameScoreCalculator(final RollScoreCalculator rollScoreCalculator, final AbstractFrameScoreCalculator previousFrameScore, final int frameNumber) {

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
