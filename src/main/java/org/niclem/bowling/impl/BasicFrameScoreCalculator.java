package org.niclem.bowling.impl;

final class BasicFrameScoreCalculator extends AbstractFrameScoreCalculator {

    private final AbstractFrameScoreCalculator previousFrameScore;
    private AbstractFrameScoreCalculator nextFrameScore;

    /* package */ BasicFrameScoreCalculator(final RollScoreCalculator controller,final  AbstractFrameScoreCalculator previousFrameScore, final int frameNumber) {

        super(controller, frameNumber);
        this.previousFrameScore = previousFrameScore;
        this.nextFrameScore = new NullFrameScoreCalculator();
    }

    public void setNext(final AbstractFrameScoreCalculator nextFrameScore) {

        this.nextFrameScore = nextFrameScore;
    }

    @Override
    protected int calculateScore() {

        final int[] result = { 0 };
        controller.addRollScoreTo(result);
        previousFrameScore.addFrameScoreTo(result);
        addBonusTo(result, nextFrameScore);
        return result[0];
    }

    private void addBonusTo(final int[] result, final AbstractFrameScoreCalculator frameScore) {

        if (controller.isSpare()) {
            frameScore.addRollScoreTo(0, result);
        } else if (controller.isStrike()) {
            frameScore.addRollScoreTo(0, result);
            frameScore.addRollScoreTo(1, result);
        }
    }

}
