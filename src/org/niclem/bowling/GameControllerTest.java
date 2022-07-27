package org.niclem.bowling;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameControllerTest {

    private GameScore gameScore;
    private GameController gameController;

    @BeforeEach
    void setUp() {

        Rules rules = new Rules(2);
        this.gameController = new GameController( rules);
        this.gameScore = gameController.getScore();
    }

    @Test
    @DisplayName("Rules without frames is not allowed")
    void init_rules_with_0_frames() {

        assertThatThrownBy(()-> new Rules(0)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Play bowling game with four frames")
    void play() {

        Rules rules = new Rules(4);
        var gameController = new GameController(rules);
        var screenModel = gameController.getScore();

        gameController.play(10);
        assertThat(screenModel.getFrameScore(1)).as(gameController.toString()).isEqualTo(10);

        gameController.play(0);
        gameController.play(1);
        assertThat(screenModel.getFrameScore(2)).as(gameController.toString()).isEqualTo(12);

        gameController.play(1);
        gameController.play(9);
        assertThat(screenModel.getFrameScore(3)).as(gameController.toString()).isEqualTo(22);

        gameController.play(10);
        gameController.play(1);
        assertThat(screenModel.getFrameScore(4)).as(gameController.toString()).isEqualTo(43);
    }

    @Test
    @DisplayName("The screen model is initialized correctly.")
    void screenModel_initialized() {

        //assertThat(screenModel.getTotalScore()).as(screenModel.toString()).isEqualTo(org.niclem.bowling.ScreenModel.NO_SCORE);
        assertThat(gameScore.getFrameScore(1)).as(gameScore.toString()).isEqualTo(GameScore.NO_SCORE);
        assertThat(gameScore.getRollScore(1, 1)).as(gameScore.toString()).isEqualTo(GameScore.NO_SCORE);
        assertThat(gameScore.getRollScore(1, 2)).as(gameScore.toString()).isEqualTo(GameScore.NO_SCORE);
    }

    @Test
    @DisplayName("Open - first roll.")
    void open_first_roll_is_one() {

        gameController.play(1);

        assertThat(gameScore.getRollScore(1, 1)).as(gameController.toString()).isEqualTo(1);
        assertThat(gameScore.getRollScore(1, 2)).as(gameController.toString()).isEqualTo(GameScore.NO_SCORE);
        assertThat(gameScore.getFrameScore(1)).as(gameController.toString()).isEqualTo(1);
    }

    @Test
    @DisplayName("Open - second roll.")
    void open_second_roll_is_four() {

        gameController.play(1);
        gameController.play(4);

        assertThat(gameScore.getRollScore(1, 1)).as(gameController.toString()).isEqualTo(1);
        assertThat(gameScore.getRollScore(1, 2)).as(gameController.toString()).isEqualTo(4);
        assertThat(gameScore.getFrameScore(1)).as(gameController.toString()).isEqualTo(5);
    }

    @Test
    @DisplayName("Not last frame - spare - add bonus.")
    void spare() {

        gameController.play(1);
        assertThat(gameScore.getRollScore(1, 1)).as(gameController.toString()).isEqualTo(1);
        assertThat(gameScore.getFrameScore(1)).as(gameController.toString()).isEqualTo(1);

        gameController.play(9);
        assertThat(gameScore.getRollScore(1, 2)).as(gameController.toString()).isEqualTo(9);
        assertThat(gameScore.getFrameScore(1)).as(gameController.toString()).isEqualTo(10);

        gameController.play(2);
        assertThat(gameScore.getRollScore(2, 1)).as(gameController.toString()).isEqualTo(2);
        assertThat(gameScore.getFrameScore(1)).as(gameController.toString()).isEqualTo(12);

        gameController.play(3);
        assertThat(gameScore.getRollScore(2, 2)).as(gameController.toString()).isEqualTo(3);
        assertThat(gameScore.getFrameScore(1)).as(gameController.toString()).isEqualTo(12);
        assertThat(gameScore.getFrameScore(2)).as(gameController.toString()).isEqualTo(17);
    }

    @Test
    @DisplayName("Not last frame - strike - add bonus.")
    void strike() {

        gameController.play(10);
        assertThat(gameScore.getFrameScore(1)).as(gameController.toString()).isEqualTo(10);

        gameController.play(1);
        assertThat(gameScore.getFrameScore(1)).as(gameController.toString()).isEqualTo(11);
        assertThat(gameScore.getFrameScore(2)).as(gameController.toString()).isEqualTo(12);

        gameController.play(2);
        assertThat(gameScore.getFrameScore(1)).as(gameController.toString()).isEqualTo(13);
        assertThat(gameScore.getFrameScore(2)).as(gameController.toString()).isEqualTo(16);
    }

    @Test
    @DisplayName("Outside roll - throws exception.")
    void outside_roll_no_spare() {

        gameController.play(1);
        gameController.play(2); //3

        gameController.play(3);
        gameController.play(4); //no spare
        assertThat(gameController.isFinished()).isTrue();

        //next roll not allowed
        ThrowingCallable throwable = () -> gameController.play(5);
        assertThatThrownBy(throwable).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("Last frame - spare - add bonus.")
    void spare_last() {

        gameController.play(0);
        gameController.play(0); //0

        gameController.play(3);
        gameController.play(7); //10
        gameController.play(4); //extra roll
        assertThat(gameController.isFinished()).isTrue();

        assertThat(gameScore.getRollScore(2, 1)).as(gameController.toString()).isEqualTo(3);
        assertThat(gameScore.getRollScore(2, 2)).as(gameController.toString()).isEqualTo(7);
        assertThat(gameScore.getRollScore(2, 3)).as(gameController.toString()).isEqualTo(4);
        assertThat(gameScore.getFrameScore(2)).as(gameController.toString()).isEqualTo(14);
    }

    @Test
    @DisplayName("Last frame - strike - add bonus.")
    void strike_last() {

        gameController.play(0);
        gameController.play(0); //0

        gameController.play(10);
        assertThat(gameController.isFinished()).as(gameController.toString()).isFalse();
        assertThat(gameScore.getFrameScore(2)).as(gameController.toString()).isEqualTo(10);

        gameController.play(1);
        assertThat(gameController.isFinished()).as(gameController.toString()).isTrue();
        assertThat(gameScore.getFrameScore(2)).as(gameController.toString()).isEqualTo(11);
    }

}
