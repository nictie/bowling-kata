package org.niclem.bowling.impl;

public class FrameScoreCalculator extends FrameScoreCalculatorAbstract {

    private final FrameScoreCalculatorAbstract previousFrameScore;
    private FrameScoreCalculatorAbstract nextFrameScore;

    public FrameScoreCalculator(RollScoreCalculator controller, FrameScoreCalculatorAbstract previousFrameScore, int frameNumber) {

        super(controller, frameNumber);
        this.previousFrameScore = previousFrameScore;
        this.nextFrameScore = new NullScoreCalculator();
    }

    public void setNext(FrameScoreCalculatorAbstract nextFrameScore) {

        this.nextFrameScore = nextFrameScore;
    }

    @Override
    public int calculateScore() {

        final int[] result = { 0 };
        controller.addRollScoreTo(result);
        previousFrameScore.addFrameScoreTo(result);
        addBonusTo(result, nextFrameScore);
        return result[0];
    }

    private void addBonusTo(int[] result, FrameScoreCalculatorAbstract nextFrameScore) {

        if (controller.isSpare()) {
            nextFrameScore.addRollScoreTo(result, 0);
        } else if (controller.isStrike()) {
            nextFrameScore.addRollScoreTo(result, 0);
            nextFrameScore.addRollScoreTo(result, 1);
        }
    }

}
