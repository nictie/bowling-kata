package org.niclem.bowling.impl;

import org.niclem.bowling.Rules;

public class InitialFrame extends FrameAbstract {

    private FrameAbstract nextFrame;
    private final Rules rules;

    public InitialFrame(Rules rules) {

        super(0, new NullFrameRollCalculator(), new NullScoreCalculator());
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
        var nextScoreCalculator = new FrameScoreCalculator(nextRollCounter, new NullScoreCalculator());

        return new Frame(number + 1, rules, nextRollCounter, nextScoreCalculator);
    }

    @Override
    public boolean isLastFinished() {

        return false;
    }
}
