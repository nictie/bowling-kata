package org.niclem.bowling.impl;

import org.jetbrains.annotations.NotNull;

abstract class AbstractFrame implements Frame {

    protected final int number;
    protected final AbstractFrameScoreCalculator calculator;

    public AbstractFrame(final int frameNumber, @NotNull final AbstractFrameScoreCalculator calculator) {
        this.number = frameNumber;
        this.calculator = calculator;
    }

    /* package */ final int getNumber() {

        return number;
    }
}
