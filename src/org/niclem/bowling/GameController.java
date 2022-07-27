package org.niclem.bowling;

import org.jetbrains.annotations.NotNull;
import org.niclem.bowling.impl.AbstractFrame;
import org.niclem.bowling.impl.GameScoreResult;
import org.niclem.bowling.impl.InitialFrame;

public class GameController {
    private final GameScoreResult gameScoreResult;
    private final AbstractFrame initialFrame;
    private AbstractFrame currentFrame;

    public GameController(@NotNull Rules rules) {

        this.gameScoreResult = new GameScoreResult(rules);
        this.initialFrame = new InitialFrame(rules);
        this.currentFrame = initialFrame;
    }

    public void registerRoll(int hitPins) {

        currentFrame = currentFrame.roll(hitPins);
        initialFrame.updateScore(gameScoreResult);
    }

    public GameScore getScore() {

        return gameScoreResult;
    }

    public boolean isFinished() {

        return currentFrame.isLastFinished();
    }

    @Override
    public String toString() {

        return "BowlingGame{" +
                "score=" + gameScoreResult +
                ", initialFrame=" + initialFrame +
                ", currentFrame=" + currentFrame +
                '}';
    }
}
