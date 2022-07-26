package org.niclem.bowling;

public abstract class FrameScoreCalculatorAbstract implements ScoreCalculator {

    protected final AbstractRollCounter rollCounter;

    public FrameScoreCalculatorAbstract(AbstractRollCounter rollCounter) {

        this.rollCounter = rollCounter;
    }

    public abstract int calculateScore();

    final void addFrameScoreTo(int[] result) {

        result[0] = result[0] + calculateScore();
    }

    final void addRollScoreTo(int[] result, int index) {

        rollCounter.addRollScoreTo(result, index);
    }

    final void addRollScoreTo(int[] result) {

        rollCounter.addRollScoreTo(result);
    }
}
