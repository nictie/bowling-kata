package org.niclem.bowling.impl;

import org.jetbrains.annotations.NotNull;

public abstract class AbstractFrame implements GameScoreUpdater {

    protected final int number;
    protected final AbstractFrameScoreCalculator calculator;

    public AbstractFrame(int frameNumber, @NotNull AbstractFrameScoreCalculator calculator) {
        this.number = frameNumber;
        this.calculator = calculator;
    }

    public abstract AbstractFrame roll(int hitPins);

    public abstract boolean isLastFinished();

    int getNumber() {

        return number;
    }
}
