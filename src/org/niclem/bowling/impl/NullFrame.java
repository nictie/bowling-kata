package org.niclem.bowling.impl;

public class NullFrame extends FrameAbstract {

    public NullFrame() {

        super(-1, new NullFrameRollCalculator(), new NullScoreCalculator());
    }

    @Override
    public Frame roll(int hitPins) {

        throw new UnsupportedOperationException("Null frame cannot be rolled.");
    }

    @Override
    public void updateScore(ScreenModelUpdater screenModelUpdater) {

    }

    @Override
    public boolean isLastFinished() {

        return false;
    }

}
