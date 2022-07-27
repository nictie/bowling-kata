package org.niclem.bowling.impl;

final class NullScoreCalculator extends FrameScoreCalculatorAbstract {

    NullScoreCalculator() {

        super(new NullFrameRollCalculator(), -1);
    }

    @Override
    protected int calculateScore() {

        return 0;
    }
}
