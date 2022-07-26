package org.niclem.bowling;

public class FrameScoreCalculator extends FrameScoreCalculatorAbstract {

    private final FrameScoreCalculatorAbstract previousFrame;
    private FrameScoreCalculatorAbstract nextFrame;

    public FrameScoreCalculator(RollCounter rollCounter, FrameScoreCalculatorAbstract previousFrame) {

        super(rollCounter);

        this.previousFrame = previousFrame;
        this.nextFrame = new NullScoreCalculator();
    }

    public void setNext(FrameScoreCalculatorAbstract aFrame) {

        nextFrame = aFrame;
    }


    @Override
    public int calculateScore() {

        final int[] result = { 0 };
        addRollScoreTo(result);
        if (rollCounter.isSpare()) {
            nextFrame.addRollScoreTo(result, 0);
        } else if (rollCounter.isStrike()) {
            nextFrame.addRollScoreTo(result, 0);
            nextFrame.addRollScoreTo(result, 1);
        }
        previousFrame.addFrameScoreTo(result);
        return result[0];
    }
}
