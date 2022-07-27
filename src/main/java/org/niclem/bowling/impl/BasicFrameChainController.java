package org.niclem.bowling.impl;

import org.jetbrains.annotations.NotNull;
import org.niclem.bowling.Rules;

final class BasicFrameChainController {

    private final BasicFrame parentFrame;
    private final Rules rules;
    private final RollController rollController;
    private final BasicFrameScoreCalculator calculator;

    /* package */ BasicFrameChainController(@NotNull final BasicFrame parentFrame, final Rules rules, @NotNull final RollController rollController, final @NotNull BasicFrameScoreCalculator calculator) {

        this.parentFrame = parentFrame;
        this.rules = rules;
        this.rollController = rollController;
        this.calculator = calculator;
    }

    /* package */ AbstractFrame roll(final int hitPins) {

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

        final int nextFrameNumber = parentFrame.getNumber() + 1;
        final var nextRollCounter = new BasicFrameRollController();
        final var nextScoreCalculator = new BasicFrameScoreCalculator(nextRollCounter, calculator, nextFrameNumber);
        calculator.setNext(nextScoreCalculator);

        return new BasicFrame(nextFrameNumber, rules, nextRollCounter, nextScoreCalculator);
    }

    private AbstractFrame createLastFrame() {

        final int nextFrameNumber = parentFrame.getNumber() + 1;
        final var nextRollCounter = new LastFrameRollController();
        final var nextScoreCalculator = new LastFrameScoreCalculator(nextRollCounter, calculator, nextFrameNumber);
        calculator.setNext(nextScoreCalculator);

        return new LastFrame(nextFrameNumber, nextRollCounter, nextScoreCalculator);
    }
}
