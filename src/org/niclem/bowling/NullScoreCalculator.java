package org.niclem.bowling;

public class NullScoreCalculator extends FrameScoreCalculatorAbstract {

    public NullScoreCalculator() {

        super(new NullRollCounter());
    }

    @Override
    public int calculateScore() {

        return 0;
    }
}
