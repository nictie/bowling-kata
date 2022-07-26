package org.niclem.bowling.impl;

import org.jetbrains.annotations.NotNull;
import org.niclem.bowling.Rules;

public class Frame extends FrameAbstract {
    private final Rules rules;
    private final FrameScoreCalculator scoreCalculator;
    private FrameAbstract nextFrame;

    public Frame(int number, Rules rules, @NotNull RollCounter rollCounter, @NotNull FrameScoreCalculator scoreCalculator) {

        super(number, rollCounter, scoreCalculator);
        this.rules = rules;
        this.scoreCalculator = scoreCalculator;
        this.nextFrame = new NullFrame();
    }

    @Override
    public FrameAbstract roll(int hitPins) {

        FrameAbstract result;

        if (rollCounter.addRoll(hitPins, number)) {
            result = this;
        } else {
            if (rules.isNextFrame(number)) {
                nextFrame = createNextFrame();
                nextFrame.roll(hitPins);
                result = nextFrame;
            } else if (rules.isLastFrame(number)) {
                nextFrame = createLastFrame();
                nextFrame.roll(hitPins);
                result = nextFrame;
            } else {
                throw new IllegalStateException("Frame is over - no further roll allowed");
            }
        }
        return result;
    }

    @Override
    public void updateScore(ScreenModelUpdater screenModelUpdater) {

        super.updateScore(screenModelUpdater);
        nextFrame.updateScore(screenModelUpdater);
    }

    private FrameAbstract createNextFrame() {

        var nextRollCounter = new RollCounter();
        var nextScoreCalculator = new FrameScoreCalculator(nextRollCounter, scoreCalculator);
        scoreCalculator.setNext(nextScoreCalculator);

        return new Frame(number + 1, rules, nextRollCounter, nextScoreCalculator);
    }

    private FrameAbstract createLastFrame() {

        var nextRollCounter = new LastRollCounter();
        var nextScoreCalculator = new LastScoreCalculator(nextRollCounter, scoreCalculator);
        scoreCalculator.setNext(nextScoreCalculator);

        return new LastFrame(number + 1, nextRollCounter, nextScoreCalculator);
    }

    @Override
    public boolean isLastFinished() {

        return false;
    }

    @Override
    public String toString() {

        return "\norg.niclem.bowling.impl.Frame{" +
                "number=" + number +
                ", rollsCounter=" + rollCounter +
                '}';
    }
}
