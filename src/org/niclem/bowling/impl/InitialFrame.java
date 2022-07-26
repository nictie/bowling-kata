package org.niclem.bowling.impl;

public class InitialFrame extends FrameAbstract {

    private Frame nextFrame;
    private final int maxFrames;

    public InitialFrame(int maxFrames) {

        super(-1, new NullRollCounter(), new NullScoreCalculator());
        this.maxFrames = maxFrames;
    }

    @Override
    public Frame roll(int hitPins) {

        RollCounter rollCounter = new RollCounter();
        FrameScoreCalculator scoreCalculator = new FrameScoreCalculator(rollCounter, new NullScoreCalculator());
        nextFrame = new Frame(1, maxFrames, rollCounter, scoreCalculator);
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
