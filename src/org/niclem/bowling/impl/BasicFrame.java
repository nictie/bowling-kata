package org.niclem.bowling.impl;

import org.jetbrains.annotations.NotNull;
import org.niclem.bowling.Rules;

final class BasicFrame extends AbstractFrame {
    private final BasicFrameChainController chainController;
    private AbstractFrame nextFrame;

    BasicFrame(int number, Rules rules, @NotNull RollController rollController, @NotNull BasicFrameScoreCalculator calculator) {

        super(number, calculator);
        nextFrame = new NullFrame();
        chainController = new BasicFrameChainController(this, rules, rollController, calculator);
    }

    @Override
    public AbstractFrame roll(int hitPins) {

        return chainController.roll(hitPins);
    }

    void setNextFrame(AbstractFrame nextFrame) {

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
