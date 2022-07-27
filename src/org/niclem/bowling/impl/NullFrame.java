package org.niclem.bowling.impl;

final class NullFrame extends FrameAbstract {

    NullFrame() {

        super(-1, new NullScoreCalculator());
    }

    @Override
    public Frame roll(int hitPins) {

        throw new UnsupportedOperationException("Null frame cannot be rolled.");
    }

    @Override
    public void updateScore(GameScoreResult gameScoreResult) {

    }

    @Override
    public boolean isLastFinished() {

        return false;
    }

}
