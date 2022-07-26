package org.niclem.bowling.impl;

public class NullScoreCalculator extends FrameScoreCalculatorAbstract {

    public NullScoreCalculator() {

        super(new NullRollCounter());
    }

    @Override
    public int calculateScore() {

        return 0;
    }
}
