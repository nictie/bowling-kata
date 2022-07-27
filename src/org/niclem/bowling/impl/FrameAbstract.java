package org.niclem.bowling.impl;

import org.jetbrains.annotations.NotNull;

public abstract class FrameAbstract implements ScreenUpdater {

    protected final int number;
    protected final FrameScoreCalculatorAbstract calculator;

    public FrameAbstract(int frameNumber, @NotNull FrameScoreCalculatorAbstract calculator) {
        this.number = frameNumber;
        this.calculator = calculator;
    }

    public abstract FrameAbstract roll(int hitPins);

    public abstract boolean isLastFinished();

    public int getNumber() {

        return number;
    }
}
