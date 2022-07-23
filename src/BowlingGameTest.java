import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BowlingGameTest {

    private ScreenModelImpl screenModel;
    private BowlingGame bowlingGame;

    @BeforeEach
    void setUp() {

        Rules rules = Rules.createRules(2);
        screenModel = new ScreenModelImpl(rules.maxFrames);
        bowlingGame = new BowlingGame(screenModel, rules);
    }

    @Test
    @DisplayName("The screen model is initialized correctly.")
    void screenModel_initialized() {

        //assertThat(screenModel.getTotalScore()).as(screenModel.toString()).isEqualTo(ScreenModel.NO_SCORE);
        assertThat(screenModel.getFrameScore(1)).as(screenModel.toString()).isEqualTo(ScreenModel.NO_SCORE);
        assertThat(screenModel.getRollScore(1, 1)).as(screenModel.toString()).isEqualTo(ScreenModel.NO_SCORE);
        assertThat(screenModel.getRollScore(1, 2)).as(screenModel.toString()).isEqualTo(ScreenModel.NO_SCORE);
    }

    @Test
    @DisplayName("Open - first roll.")
    void open_first_roll_is_one() {

        bowlingGame.roll(1);

        assertThat(screenModel.getRollScore(1, 1)).as(bowlingGame.toString()).isEqualTo(1);
        assertThat(screenModel.getRollScore(1, 2)).as(bowlingGame.toString()).isEqualTo(ScreenModel.NO_SCORE);
        assertThat(screenModel.getFrameScore(1)).as(bowlingGame.toString()).isEqualTo(1);
    }

    @Test
    @DisplayName("Open - second roll.")
    void open_second_roll_is_four() {

        bowlingGame.roll(1);
        bowlingGame.roll(4);

        assertThat(screenModel.getRollScore(1, 1)).as(bowlingGame.toString()).isEqualTo(1);
        assertThat(screenModel.getRollScore(1, 2)).as(bowlingGame.toString()).isEqualTo(4);
        assertThat(screenModel.getFrameScore(1)).as(bowlingGame.toString()).isEqualTo(5);
    }

    @Test
    @DisplayName("Not last frame - spare - add bonus.")
    void spare() {

        bowlingGame.roll(1);
        bowlingGame.roll(9);

        assertThat(screenModel.getRollScore(1, 1)).as(bowlingGame.toString()).isEqualTo(1);
        assertThat(screenModel.getRollScore(1, 2)).as(bowlingGame.toString()).isEqualTo(9);
        assertThat(screenModel.getFrameScore(1)).as(bowlingGame.toString()).isEqualTo(10);

        bowlingGame.roll(2);
        bowlingGame.roll(3);

        // add score of next frame
        assertThat(screenModel.getRollScore(1, 1)).as(bowlingGame.toString()).isEqualTo(1);
        assertThat(screenModel.getRollScore(1, 2)).as(bowlingGame.toString()).isEqualTo(9);
        assertThat(screenModel.getFrameScore(1)).as(bowlingGame.toString()).isEqualTo(12);

        assertThat(screenModel.getRollScore(2, 1)).as(bowlingGame.toString()).isEqualTo(2);
        assertThat(screenModel.getRollScore(2, 2)).as(bowlingGame.toString()).isEqualTo(3);
        assertThat(screenModel.getFrameScore(2)).as(bowlingGame.toString()).isEqualTo(17);
    }

    @Test
    @DisplayName("Outside roll - throws exception.")
    void outside_roll() {

        bowlingGame.roll(1);
        bowlingGame.roll(2); //3

        bowlingGame.roll(3);
        bowlingGame.roll(4); //no spare

        assertThat(bowlingGame.isFinished()).isTrue();
        assertThatThrownBy(()-> bowlingGame.roll(5)).isInstanceOf(IllegalStateException.class); //extra roll not allowed
    }

    @Test
    @DisplayName("Last frame - spare - add bonus.")
    void spare_last() {

        bowlingGame.roll(0);
        bowlingGame.roll(0); //0

        bowlingGame.roll(3);
        bowlingGame.roll(7); //10
        bowlingGame.roll(4); //extra roll

        assertThat(screenModel.getRollScore(2, 1)).as(bowlingGame.toString()).isEqualTo(3);
        assertThat(screenModel.getRollScore(2, 2)).as(bowlingGame.toString()).isEqualTo(7);
        assertThat(screenModel.getRollScore(2, 3)).as(bowlingGame.toString()).isEqualTo(4);
        assertThat(screenModel.getFrameScore(2)).as(bowlingGame.toString()).isEqualTo(14);
    }

    @Test
    @DisplayName("Not last frame - strike - add bonus.")
    void strike() {

        assertThat(true).isFalse();
    }

    @Test
    @DisplayName("Last frame - strike - add bonus.")
    void strike_last() {

        assertThat(true).isFalse();
    }

}
