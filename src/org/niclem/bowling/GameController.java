package org.niclem.bowling;

import org.jetbrains.annotations.NotNull;

public class GameController implements ScreenUpdater {
    private final ScreenModelUpdater screenModelUpdater;
    private final FrameAbstract initialFrame;
    private final int maxFrames;
    private FrameAbstract currentFrame;

    public GameController(@NotNull ScreenModelUpdater screenModelUpdater, int maxFrames) {

        this.screenModelUpdater = screenModelUpdater;
        this.maxFrames = maxFrames;
        this.initialFrame = new InitialFrame(this.maxFrames);
        this.currentFrame = initialFrame;
    }

    public void registerRoll(int hitPins) {

        currentFrame = currentFrame.roll(hitPins);
        screenModelUpdater.updateModel(this);
    }

    @Override
    public void updateScore(ScreenModelUpdater screenModelUpdater) {

        initialFrame.updateScore(screenModelUpdater);
    }

    public boolean isFinished() {

        return currentFrame.isLastFinished();
    }

    @Override
    public String toString() {

        return "BowlingGame{" +
                "screenModel=" + screenModelUpdater +
                ", firstFrame=" + initialFrame +
                ", currentFrame=" + currentFrame +
                '}';
    }
}
