package org.niclem.bowling.impl;

import org.jetbrains.annotations.NotNull;
import org.niclem.bowling.Rules;

final class Frame extends FrameAbstract {
    private final FrameController controller;
    private FrameAbstract nextFrame;

    Frame(int number, Rules rules, @NotNull FrameRollController rollController, @NotNull FrameScoreCalculator calculator) {

        super(number, calculator);
        nextFrame = new NullFrame();
        controller = new FrameController(this, rules, rollController, calculator);
    }

    @Override
    public FrameAbstract roll(int hitPins) {

        return controller.roll(hitPins);
    }

    void setNextFrame(FrameAbstract nextFrame) {

        this.nextFrame = nextFrame;
    }

    @Override
    public void updateScore(GameScoreResult gameScoreResult) {

        calculator.updateScore(gameScoreResult);
        nextFrame.updateScore(gameScoreResult);
    }

    @Override
    public boolean isLastFinished() {

        return false;
    }

    @Override
    public String toString() {

        return "\nFrame{" +
                "number=" + number +
                ", controller=" + controller +
                '}';
    }
}
