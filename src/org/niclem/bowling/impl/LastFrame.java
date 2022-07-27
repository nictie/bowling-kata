package org.niclem.bowling.impl;

import org.jetbrains.annotations.NotNull;

final class LastFrame extends FrameAbstract {

    private final @NotNull FrameRollController frameRollController;

    LastFrame(int frameNumber, @NotNull FrameRollController frameRollController, @NotNull LastFrameScoreCalculator scoreCalculator) {

        super(frameNumber, scoreCalculator);
        this.frameRollController = frameRollController;
    }

    @Override
    public FrameAbstract roll(int hitPins) {

        FrameAbstract result;

        if (frameRollController.addRoll(hitPins, number)) {
            result = this;
        } else {
            throw new IllegalStateException("Game is over - no further roll allowed");
        }
        return result;
    }

    @Override
    public void updateScore(GameScoreResult gameScoreResult) {

        calculator.updateScore(gameScoreResult);
    }

    @Override
    public boolean isLastFinished() {

        return frameRollController.isFull();
    }

    @Override
    public String toString() {

        return "\norg.niclem.bowling.impl.LastFrame{" +
                "number=" + number +
                ", rollCouunter=" + frameRollController +
                '}';
    }
}
