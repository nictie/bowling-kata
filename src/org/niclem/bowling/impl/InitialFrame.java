package org.niclem.bowling.impl;

import org.niclem.bowling.Rules;

final class InitialFrame extends AbstractFrame {

    private AbstractFrame nextFrame;
    private final Rules rules;

    InitialFrame(Rules rules) {

        super(0, new NullFrameScoreCalculator());
        this.rules = rules;
    }

    @Override
    public AbstractFrame roll(int hitPins) {

        nextFrame = createNextFrame();
        nextFrame.roll(hitPins);
        return nextFrame;
    }

    public void updateScore(GameScoreImpl gameScore) {

        nextFrame.updateScore(gameScore);
    }

    private AbstractFrame createNextFrame() {

        var nextRollCounter = new BasicFrameRollController();
        int nextFrameNumber = number + 1;
        var nextScoreCalculator = new BasicFrameScoreCalculator(nextRollCounter, new NullFrameScoreCalculator(), nextFrameNumber);

        return new BasicFrame(nextFrameNumber, rules, nextRollCounter, nextScoreCalculator);
    }

    @Override
    public boolean isLastFinished() {

        return false;
    }
}
