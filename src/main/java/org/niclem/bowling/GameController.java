package org.niclem.bowling;

import static org.niclem.bowling.impl.Frame.create;

import org.jetbrains.annotations.NotNull;
import org.niclem.bowling.impl.Frame;
import org.niclem.bowling.impl.GameScoreImpl;

public class GameController {
    private final GameScoreImpl gameScore;
    private final Frame initialFrame;
    private Frame currentFrame;

    public GameController(@NotNull final Rules rules) {

        this.gameScore = new GameScoreImpl(rules);
        this.initialFrame = create(rules);
        this.currentFrame = initialFrame;
    }

    public void play(final int hitPins) {

        currentFrame = currentFrame.roll(hitPins);
        initialFrame.updateScore(gameScore);
    }

    public GameScore getScore() {

        return gameScore;
    }

    public boolean isFinished() {

        return currentFrame.isLastFinished();
    }

    @Override
    public String toString() {

        return "BowlingGame{" +
                "score=" + gameScore +
                ", initialFrame=" + initialFrame +
                ", currentFrame=" + currentFrame +
                '}';
    }
}
