package org.niclem.bowling.impl;

import org.niclem.bowling.ScreenModelUpdater;

final class NullFrame extends FrameAbstract {

    NullFrame() {

        super(-1, new NullScoreCalculator());
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
