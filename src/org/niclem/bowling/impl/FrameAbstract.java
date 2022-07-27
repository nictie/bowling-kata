package org.niclem.bowling.impl;

import org.jetbrains.annotations.NotNull;

public abstract class FrameAbstract implements ScreenUpdater {
    protected final int number;
    protected final FrameScoreCalculatorAbstract scoreCalculator;

    public FrameAbstract(int frameNumber, @NotNull FrameScoreCalculatorAbstract scoreCalculator) {
        this.number = frameNumber;
        this.scoreCalculator = scoreCalculator;
    }

    public abstract FrameAbstract roll(int hitPins);

    public abstract boolean isLastFinished();

    @Override
    public void updateScore(ScreenModelUpdater screenModelUpdater) {

        scoreCalculator.updateScore(screenModelUpdater);
    }
}
