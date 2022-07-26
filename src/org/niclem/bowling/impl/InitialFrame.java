package org.niclem.bowling.impl;

import org.niclem.bowling.Rules;

public class InitialFrame extends FrameAbstract {

    private Frame nextFrame;
    private final Rules rules;

    public InitialFrame(Rules rules) {

        super(-1, new NullRollCounter(), new NullScoreCalculator());
        this.rules = rules;
    }

    @Override
    public Frame roll(int hitPins) {

        RollCounter rollCounter = new RollCounter();
        FrameScoreCalculator scoreCalculator = new FrameScoreCalculator(rollCounter, new NullScoreCalculator());
        nextFrame = new Frame(1, rules, rollCounter, scoreCalculator);
        nextFrame.roll(hitPins);
        return nextFrame;
    }

    @Override
    public void updateScore(ScreenModelUpdater screenModelUpdater) {

        nextFrame.updateScore(screenModelUpdater);
    }

    @Override
    public boolean isLastFinished() {

        return false;
    }
}
