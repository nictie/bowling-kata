package org.niclem.bowling.impl;

import org.jetbrains.annotations.NotNull;

public class LastFrame extends FrameAbstract {

    private final LastFrameRollCalculator controller;

    public LastFrame(int frameNumber, @NotNull LastFrameRollCalculator controller, @NotNull LastFrameScoreCalculator scoreCalculator) {

        super(frameNumber, scoreCalculator);
        this.controller = controller;
    }

    @Override
    public FrameAbstract roll(int hitPins) {

        FrameAbstract result;

        if (controller.addRoll(hitPins, number)) {
            result = this;
        } else {
            throw new IllegalStateException("Game is over - no further roll allowed");
        }
        return result;
    }

    @Override
    public boolean isLastFinished() {

        return controller.isFull();
    }

    @Override
    public String toString() {

        return "\norg.niclem.bowling.impl.LastFrame{" +
                "number=" + number +
                ", rollCouunter=" + controller +
                '}';
    }
}
