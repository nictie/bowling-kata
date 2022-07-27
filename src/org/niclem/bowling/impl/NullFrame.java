package org.niclem.bowling.impl;

final class NullFrame extends AbstractFrame {

    NullFrame() {

        super(-1, new NullFrameScoreCalculator());
    }

    @Override
    public BasicFrame roll(int hitPins) {

        throw new UnsupportedOperationException("Null frame cannot be rolled.");
    }

    @Override
    public void updateScore(GameScoreImpl gameScore) {

    }

    @Override
    public boolean isLastFinished() {

        return false;
    }

}
