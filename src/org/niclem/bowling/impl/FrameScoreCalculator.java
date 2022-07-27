package org.niclem.bowling.impl;

final class FrameScoreCalculator extends FrameScoreCalculatorAbstract {

    private final FrameScoreCalculatorAbstract previousFrameScore;
    private FrameScoreCalculatorAbstract nextFrameScore;

    FrameScoreCalculator(RollScoreCalculator controller, FrameScoreCalculatorAbstract previousFrameScore, int frameNumber) {

        super(controller, frameNumber);
        this.previousFrameScore = previousFrameScore;
        this.nextFrameScore = new NullFrameScoreCalculator();
    }

    public void setNext(FrameScoreCalculatorAbstract nextFrameScore) {

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

    private void addBonusTo(int[] result, FrameScoreCalculatorAbstract frameScore) {

        if (controller.isSpare()) {
            frameScore.addRollScoreTo(result, 0);
        } else if (controller.isStrike()) {
            frameScore.addRollScoreTo(result, 0);
            frameScore.addRollScoreTo(result, 1);
        }
    }

}
