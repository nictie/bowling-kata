package org.niclem.bowling.impl;

import org.jetbrains.annotations.NotNull;
import org.niclem.bowling.Rules;

final class FrameChainController {

    private final Frame parentFrame;
    private final Rules rules;
    private final RollController rollController;
    private final FrameScoreCalculator calculator;

    FrameChainController(@NotNull Frame parentFrame,  Rules rules, @NotNull RollController rollController, @NotNull FrameScoreCalculator calculator) {

        this.parentFrame = parentFrame;
        this.rules = rules;
        this.rollController = rollController;
        this.calculator = calculator;
    }

    FrameAbstract roll(int hitPins) {

        FrameAbstract result;

        if (rollController.addRoll(hitPins, parentFrame.getNumber())) {
            result = parentFrame;
        } else {
            FrameAbstract nextFrame;
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

    private FrameAbstract createNextFrame() {

        int nextFrameNumber = parentFrame.getNumber() + 1;
        var nextRollCounter = new FrameRollController();
        var nextScoreCalculator = new FrameScoreCalculator(nextRollCounter, calculator, nextFrameNumber);
        calculator.setNext(nextScoreCalculator);

        return new Frame(nextFrameNumber, rules, nextRollCounter, nextScoreCalculator);
    }

    private FrameAbstract createLastFrame() {

        int nextFrameNumber = parentFrame.getNumber() + 1;
        var nextRollCounter = new LastFrameRollController();
        var nextScoreCalculator = new LastFrameScoreCalculator(nextRollCounter, calculator, nextFrameNumber);
        calculator.setNext(nextScoreCalculator);

        return new LastFrame(nextFrameNumber, nextRollCounter, nextScoreCalculator);
    }
}
