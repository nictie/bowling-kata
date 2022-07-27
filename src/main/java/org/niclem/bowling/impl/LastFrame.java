package org.niclem.bowling.impl;

import org.jetbrains.annotations.NotNull;

final class LastFrame extends AbstractFrame {

    private final @NotNull RollController rollController;

    /* package */ LastFrame(final int frameNumber, @NotNull final RollController rollController, @NotNull final LastFrameScoreCalculator scoreCalculator) {

        super(frameNumber, scoreCalculator);
        this.rollController = rollController;
    }

    @Override
    public AbstractFrame roll(final int hitPins) {

        AbstractFrame result;

        if (rollController.addRoll(hitPins, number)) {
            result = this;
        } else {
            throw new IllegalStateException("Game is over - no further roll allowed");
        }
        return result;
    }

    @Override
    public void updateScore(final GameScoreImpl gameScore) {

        calculator.updateScore(gameScore);
    }

    @Override
    public boolean isLastFinished() {

        return rollController.isFull();
    }

    @Override
    public String toString() {

        return "\norg.niclem.bowling.impl.LastFrame{" +
                "number=" + number +
                ", rollCouunter=" + rollController +
                '}';
    }
}
