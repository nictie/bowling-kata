package org.niclem.bowling.impl;

import org.jetbrains.annotations.NotNull;

final class LastFrame extends AbstractFrame {

    private final @NotNull RollController rollController;

    LastFrame(int frameNumber, @NotNull RollController rollController, @NotNull LastFrameScoreCalculator scoreCalculator) {

        super(frameNumber, scoreCalculator);
        this.rollController = rollController;
    }

    @Override
    public AbstractFrame roll(int hitPins) {

        AbstractFrame result;

        if (rollController.addRoll(hitPins, number)) {
            result = this;
        } else {
            throw new IllegalStateException("Game is over - no further roll allowed");
        }
        return result;
    }

    @Override
    public void updateScore(GameScoreImpl gameScore) {

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
