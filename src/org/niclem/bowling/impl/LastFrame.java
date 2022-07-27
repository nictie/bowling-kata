package org.niclem.bowling.impl;

import org.jetbrains.annotations.NotNull;

public class LastFrame extends FrameAbstract {

    public LastFrame(int frameNumber, @NotNull LastFrameRollCalculator rollCounter, @NotNull LastFrameScoreCalculator scoreCalculator) {

        super(frameNumber, rollCounter, scoreCalculator);
    }

    @Override
    public FrameAbstract roll(int hitPins) {

        FrameAbstract result;

        if (rollCounter.addRoll(hitPins, number)) {
            result = this;
        } else {
            throw new IllegalStateException("Game is over - no further roll allowed");
        }
        return result;
    }

    @Override
    public boolean isLastFinished() {

        return rollCounter.isFull();
    }

    @Override
    public String toString() {

        return "\norg.niclem.bowling.impl.LastFrame{" +
                "number=" + number +
                ", rollCouunter=" + rollCounter +
                '}';
    }
}
