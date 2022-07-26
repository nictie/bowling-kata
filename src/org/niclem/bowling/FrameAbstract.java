package org.niclem.bowling;

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

}
