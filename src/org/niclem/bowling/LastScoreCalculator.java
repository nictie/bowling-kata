package org.niclem.bowling;

public class LastScoreCalculator extends FrameScoreCalculatorAbstract {

    private final FrameScoreCalculatorAbstract previousFrame;

    public LastScoreCalculator(LastRollCounter rollCounter, FrameScoreCalculatorAbstract previousFrame) {

        super(rollCounter);
        this.previousFrame = previousFrame;
    }

    @Override
    public int calculateScore() {

        final int[] result = { 0 };
        addRollScoreTo(result);
        previousFrame.addFrameScoreTo(result);
        return result[0];
    }
}
