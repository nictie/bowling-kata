package org.niclem.bowling.impl;

public class NullScoreCalculator extends FrameScoreCalculatorAbstract {

    public NullScoreCalculator() {

        super(new NullFrameRollCalculator(), -1);
    }

    @Override
    protected int calculateScore() {

        return 0;
    }
}
