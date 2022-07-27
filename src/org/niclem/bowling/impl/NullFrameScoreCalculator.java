package org.niclem.bowling.impl;

final class NullFrameScoreCalculator extends FrameScoreCalculatorAbstract {

    NullFrameScoreCalculator() {

        super(new NullFrameRollScoreCalculator(), -1);
    }

    @Override
    protected int calculateScore() {

        return 0;
    }
}
