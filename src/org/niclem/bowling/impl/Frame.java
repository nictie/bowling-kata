package org.niclem.bowling.impl;

import org.jetbrains.annotations.NotNull;
import org.niclem.bowling.Rules;

final class Frame extends FrameAbstract {
    private final FrameChainController chainController;
    private FrameAbstract nextFrame;

    Frame(int number, Rules rules, @NotNull RollController rollController, @NotNull FrameScoreCalculator calculator) {

        super(number, calculator);
        nextFrame = new NullFrame();
        chainController = new FrameChainController(this, rules, rollController, calculator);
    }

    @Override
    public FrameAbstract roll(int hitPins) {

        return chainController.roll(hitPins);
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
                ", controller=" + chainController +
                '}';
    }
}
