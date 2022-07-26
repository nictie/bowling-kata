package org.niclem.bowling;

import org.jetbrains.annotations.NotNull;
import org.niclem.bowling.impl.FrameAbstract;
import org.niclem.bowling.impl.InitialFrame;
import org.niclem.bowling.impl.ScreenModelUpdater;

public class GameController {
    private final ScreenModelUpdater screenModelUpdater;
    private final FrameAbstract initialFrame;
    private FrameAbstract currentFrame;

    public GameController(@NotNull ScreenModelUpdater screenModelUpdater, int maxFrames) {

        this.screenModelUpdater = screenModelUpdater;
        this.initialFrame = new InitialFrame(maxFrames);
        this.currentFrame = initialFrame;
    }

    public void registerRoll(int hitPins) {

        currentFrame = currentFrame.roll(hitPins);
        initialFrame.updateScore(screenModelUpdater);
    }

    public boolean isFinished() {

        return currentFrame.isLastFinished();
    }

    @Override
    public String toString() {

        return "BowlingGame{" +
                "screenModel=" + screenModelUpdater +
                ", initialFrame=" + initialFrame +
                ", currentFrame=" + currentFrame +
                '}';
    }
}
