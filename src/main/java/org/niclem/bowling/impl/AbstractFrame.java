package org.niclem.bowling.impl;

import org.jetbrains.annotations.NotNull;

abstract class AbstractFrame implements Frame {

    protected final int number;
    protected final AbstractFrameScoreCalculator calculator;

    public AbstractFrame(int frameNumber, @NotNull AbstractFrameScoreCalculator calculator) {
        this.number = frameNumber;
        this.calculator = calculator;
    }

    int getNumber() {

        return number;
    }
}
