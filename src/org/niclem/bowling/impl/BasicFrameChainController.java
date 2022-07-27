package org.niclem.bowling.impl;

import org.jetbrains.annotations.NotNull;
import org.niclem.bowling.Rules;

final class BasicFrameChainController {

    private final BasicFrame parentFrame;
    private final Rules rules;
    private final RollController rollController;
    private final BasicFrameScoreCalculator calculator;

    BasicFrameChainController(@NotNull BasicFrame parentFrame,  Rules rules, @NotNull RollController rollController, @NotNull BasicFrameScoreCalculator calculator) {

        this.parentFrame = parentFrame;
        this.rules = rules;
        this.rollController = rollController;
        this.calculator = calculator;
    }

    AbstractFrame roll(int hitPins) {

        AbstractFrame result;

        if (rollController.addRoll(hitPins, parentFrame.getNumber())) {
            result = parentFrame;
        } else {
            AbstractFrame nextFrame;
            if (rules.isNextFrame(parentFrame.getNumber())) {
                nextFrame = createNextFrame();
                nextFrame.roll(hitPins);
                parentFrame.setNextFrame(nextFrame);
                result = nextFrame;
            } else if (rules.isLastFrame(parentFrame.getNumber())) {
                nextFrame = createLastFrame();
                nextFrame.roll(hitPins);
                parentFrame.setNextFrame(nextFrame);
                result = nextFrame;
            } else {
                throw new IllegalStateException("Frame is over - no further roll allowed");
            }
        }
        return result;
    }

    private AbstractFrame createNextFrame() {

        int nextFrameNumber = parentFrame.getNumber() + 1;
        var nextRollCounter = new BasicFrameRollController();
        var nextScoreCalculator = new BasicFrameScoreCalculator(nextRollCounter, calculator, nextFrameNumber);
        calculator.setNext(nextScoreCalculator);

        return new BasicFrame(nextFrameNumber, rules, nextRollCounter, nextScoreCalculator);
    }

    private AbstractFrame createLastFrame() {

        int nextFrameNumber = parentFrame.getNumber() + 1;
        var nextRollCounter = new LastFrameRollController();
        var nextScoreCalculator = new LastFrameScoreCalculator(nextRollCounter, calculator, nextFrameNumber);
        calculator.setNext(nextScoreCalculator);

        return new LastFrame(nextFrameNumber, nextRollCounter, nextScoreCalculator);
    }
}
