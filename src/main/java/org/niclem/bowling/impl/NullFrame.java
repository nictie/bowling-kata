package org.niclem.bowling.impl;

final class NullFrame extends AbstractFrame {

    /* package */ NullFrame() {

        super(-1, new NullFrameScoreCalculator());
    }

    @Override
    public BasicFrame roll(final int hitPins) {

        throw new UnsupportedOperationException("Null frame cannot be rolled.");
    }

    @Override
    public void updateScore(final GameScoreImpl gameScore) {

    }

    @Override
    public boolean isLastFinished() {

        return false;
    }

}
