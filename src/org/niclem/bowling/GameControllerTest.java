package org.niclem.bowling;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.niclem.bowling.GameScore.NO_SCORE;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameControllerTest {
    private GameScore gameScore;
    private GameController gameController;
    private int maxFrames;

    @BeforeEach
    void setUp() {

        maxFrames = 2;
        Rules rules = new Rules(maxFrames);
        this.gameController = new GameController(rules);
        this.gameScore = gameController.getScore();
    }

    @Test
    @DisplayName("Maximum number of frames should be bigger than 0")
    void initRulesWithZeroMaxFrameNumber() {

        assertThatThrownBy(() -> new Rules(0)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Initial game score should be NO_SCORE of all frames and rolls.")
    void initialGameScore() {

        checkNoScoreFromFrame(1);
    }

    @Test
    @DisplayName("Count the first roll")
    void playFirstRoll() {

        gameController.play(1);

        checkScoreOfFrame(1, 1, 1, NO_SCORE);
        checkNoScoreFromFrame(maxFrames - 1);
    }

    @Test
    @DisplayName("Count the second roll")
    void playSecondRoll() {

        gameController.play(1);
        gameController.play(4);

        checkScoreOfFrame(1, 5, 1, 4);
        checkNoScoreFromFrame(maxFrames - 1);
    }

    @Test
    @DisplayName("First frame has a - spare - should add the bonus of the next roll")
    void spareCountInFirstFrame() {

        gameController.play(1);
        gameController.play(9);

        gameController.play(2);

        checkScoreOfFrame(1, 12, 1, 9);
    }

    @Test
    @DisplayName("First frame has a - strike - should add the bonus of the next two rolls")
    void strikeCountInFirstFrame() {

        gameController.play(10);

        gameController.play(1);
        gameController.play(2);

        checkScoreOfFrame(1, 13, 10, NO_SCORE);
    }

    @Test
    @DisplayName("A roll after the last frame is finished - should throws an exception")
    void rollAfterFinish() {

        gameController.play(1);
        gameController.play(2); //3

        gameController.play(3);
        gameController.play(4); //no spare

        //next roll not allowed
        ThrowingCallable throwable = () -> gameController.play(5);
        assertThatThrownBy(throwable).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("The game should be finished with last regular roll")
    void gameIsFinishedWithLastRoll() {

        gameController.play(1);
        gameController.play(2);
        gameController.play(3);
        gameController.play(4);

        assertThat(gameController.isFinished()).isTrue();
    }

    @Test
    @DisplayName("The game should be finished with bonus roll than a spare has been thrown")
    void gameIsFinishedWithSpare() {

        gameController.play(1);
        gameController.play(2);

        gameController.play(3);
        gameController.play(7);
        gameController.play(4);

        assertThat(gameController.isFinished()).isTrue();
    }

    @Test
    @DisplayName("The game should be finished with bonus roll than a strike has been thrown")
    void gameIsFinishedWithStrike() {

        gameController.play(1);
        gameController.play(2);

        gameController.play(10);
        gameController.play(3);

        assertThat(gameController.isFinished()).isTrue();
    }

    @Test
    @DisplayName("Count the bonus roll than a spare has been thrown in the last frame")
    void spareCountBonusRollInLastFrame() {

        gameController.play(0);
        gameController.play(0); //0

        gameController.play(3);
        gameController.play(7); //10
        gameController.play(4); //extra roll

        checkScoreOfFrame(2, 14, 3, 7, 4);
    }

    @Test
    @DisplayName("Count the bonus roll than a strike has been thrown in the last frame")
    void strikeCountBonusRollInLastFrame() {

        gameController.play(0);
        gameController.play(0); //0

        gameController.play(10);
        gameController.play(1); // bonus roll

        checkScoreOfFrame(2, 11, 10, 1);
    }

    private void checkNoScoreFromFrame(int frameNumber) {

        for (int i = frameNumber; i == maxFrames; i++) {
            assertThat(gameScore.getRollScore(i, 1)).as(gameScore.toString()).isEqualTo(NO_SCORE);
            assertThat(gameScore.getRollScore(i, 2)).as(gameScore.toString()).isEqualTo(NO_SCORE);
            assertThat(gameScore.getFrameScore(i)).as(gameScore.toString()).isEqualTo(NO_SCORE);
        }
    }

    private void checkScoreOfFrame(int frameNumber, int frameScore, int... rollScores) {

        assertThat(gameScore.getFrameScore(frameNumber)).as(gameController.toString()).isEqualTo(frameScore);
        for (int i = 0; i < rollScores.length; i++) {
            assertThat(gameScore.getRollScore(frameNumber, i + 1)).as(gameController.toString()).isEqualTo(rollScores[i]);
        }
    }

}
