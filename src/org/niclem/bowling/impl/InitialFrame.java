package org.niclem.bowling.impl;

import org.niclem.bowling.Rules;

public class InitialFrame extends FrameAbstract {

    private FrameAbstract nextFrame;
    private final Rules rules;

    public InitialFrame(Rules rules) {

        super(0, new NullScoreCalculator());
        this.rules = rules;
    }

    @Override
    public FrameAbstract roll(int hitPins) {

        nextFrame = createNextFrame();
        nextFrame.roll(hitPins);
        return nextFrame;
    }

    public void updateScore(ScreenModelUpdater screenModelUpdater) {

        nextFrame.updateScore(screenModelUpdater);
    }

    private FrameAbstract createNextFrame() {

        var nextRollCounter = new FrameRollCalculator();
        int nextFrameNumber = number + 1;
        var nextScoreCalculator = new FrameScoreCalculator(nextRollCounter, new NullScoreCalculator(), nextFrameNumber);

        return new Frame(nextFrameNumber, rules, nextRollCounter, nextScoreCalculator);
    }

    @Override
    public boolean isLastFinished() {

        return false;
    }
}
