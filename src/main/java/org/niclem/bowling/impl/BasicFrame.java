package org.niclem.bowling.impl;

import org.jetbrains.annotations.NotNull;
import org.niclem.bowling.Rules;

final class BasicFrame extends AbstractFrame {
    private final BasicFrameChainController chainController;
    private AbstractFrame nextFrame;

    /* package */ BasicFrame(final int number, final Rules rules, @NotNull final RollController rollController, final @NotNull BasicFrameScoreCalculator calculator) {

        super(number, calculator);
        nextFrame = new NullFrame();
        chainController = new BasicFrameChainController(this, rules, rollController, calculator);
    }

    @Override
    public AbstractFrame roll(final int hitPins) {

        return chainController.roll(hitPins);
    }

    /* package */ void setNextFrame(final AbstractFrame nextFrame) {

        this.nextFrame = nextFrame;
    }

    @Override
    public void updateScore(final GameScoreImpl gameScore) {

        calculator.updateScore(gameScore);
        nextFrame.updateScore(gameScore);
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
