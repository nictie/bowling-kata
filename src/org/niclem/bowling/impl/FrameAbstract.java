package org.niclem.bowling.impl;

import org.jetbrains.annotations.NotNull;

public abstract class FrameAbstract implements ScreenUpdater {
    protected final int number;
    protected final AbstractRollCounter rollCounter;
    protected final ScoreCalculator scoreCalculator;

    public FrameAbstract(int frameNumber, @NotNull AbstractRollCounter rollCounter, @NotNull ScoreCalculator scoreCalculator) {
        this.number = frameNumber;
        this.rollCounter = rollCounter;
        this.scoreCalculator = scoreCalculator;
    }

    public abstract FrameAbstract roll(int hitPins);

    public abstract boolean isLastFinished();

    @Override
    public void updateScore(ScreenModelUpdater screenModelUpdater) {

        screenModelUpdater.updateFrameScore(number, scoreCalculator.calculateScore());
        rollCounter.updateScore(screenModelUpdater);
    }
}
