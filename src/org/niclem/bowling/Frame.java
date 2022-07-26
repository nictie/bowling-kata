package org.niclem.bowling;

import org.jetbrains.annotations.NotNull;

public class Frame extends FrameAbstract {
    private final int maxFrames;
    private final FrameScoreCalculator scoreCalculator;
    private FrameAbstract nextFrame;

    public Frame(int number, int maxFrames, @NotNull RollCounter rollCounter, @NotNull FrameScoreCalculator scoreCalculator) {

        super(number, rollCounter, scoreCalculator);
        this.maxFrames = maxFrames;
        this.scoreCalculator = scoreCalculator;
        this.nextFrame = new NullFrame();
    }

    @Override
    public FrameAbstract roll(int hitPins) {

        FrameAbstract result;

        if (rollCounter.addRoll(hitPins, number)) {
            result = this;
        } else {
            if (number < maxFrames - 1) {
                var nextRollCounter = new RollCounter();
                var nextScoreCalculator = new FrameScoreCalculator(nextRollCounter, scoreCalculator);
                nextFrame = new Frame(number + 1, maxFrames, nextRollCounter, nextScoreCalculator);
                nextFrame.roll(hitPins);
                scoreCalculator.setNext(nextScoreCalculator);
                result = nextFrame;
            } else if (number == maxFrames - 1) {
                var nextRollCounter = new LastRollCounter();
                var nextScoreCalculator = new LastScoreCalculator(nextRollCounter, this.scoreCalculator);
                nextFrame = new LastFrame(number + 1, nextRollCounter, nextScoreCalculator);
                nextFrame.roll(hitPins);
                scoreCalculator.setNext(nextScoreCalculator);
                result = nextFrame;
            } else {
                throw new IllegalStateException("org.niclem.bowling.Frame is over - no further roll allowed");
            }
        }
        return result;
    }

    @Override
    public void updateScore(ScreenModelUpdater screenModelUpdater) {

        screenModelUpdater.updateFrameScore(number, scoreCalculator.calculateScore());
        rollCounter.updateScore(screenModelUpdater);
        nextFrame.updateScore(screenModelUpdater);
    }

    @Override
    public boolean isLastFinished() {

        return false;
    }

    @Override
    public String toString() {

        return "\norg.niclem.bowling.Frame{" +
                "number=" + number +
                ", rollsCounter=" + rollCounter +
                '}';
    }
}
