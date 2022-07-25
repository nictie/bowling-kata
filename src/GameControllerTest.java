import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameControllerTest {

    private ScreenModel screenModel;
    private GameController gameController;

    @BeforeEach
    void setUp() {

        int maxFrames = 2;
        ScreenModelUpdaterImpl screenModel = new ScreenModelUpdaterImpl(maxFrames);
        this.gameController = new GameController(screenModel, maxFrames);
        this.screenModel = screenModel;
    }

    @Test
    @DisplayName("The screen model is initialized correctly.")
    void screenModel_initialized() {

        //assertThat(screenModel.getTotalScore()).as(screenModel.toString()).isEqualTo(ScreenModel.NO_SCORE);
        assertThat(screenModel.getFrameScore(1)).as(screenModel.toString()).isEqualTo(ScreenModelUpdater.NO_SCORE);
        assertThat(screenModel.getRollScore(1, 1)).as(screenModel.toString()).isEqualTo(ScreenModelUpdater.NO_SCORE);
        assertThat(screenModel.getRollScore(1, 2)).as(screenModel.toString()).isEqualTo(ScreenModelUpdater.NO_SCORE);
    }

    @Test
    @DisplayName("Open - first roll.")
    void open_first_roll_is_one() {

        gameController.registerRoll(1);

        assertThat(screenModel.getRollScore(1, 1)).as(gameController.toString()).isEqualTo(1);
        assertThat(screenModel.getRollScore(1, 2)).as(gameController.toString()).isEqualTo(ScreenModelUpdater.NO_SCORE);
        assertThat(screenModel.getFrameScore(1)).as(gameController.toString()).isEqualTo(1);
    }

    @Test
    @DisplayName("Open - second roll.")
    void open_second_roll_is_four() {

        gameController.registerRoll(1);
        gameController.registerRoll(4);

        assertThat(screenModel.getRollScore(1, 1)).as(gameController.toString()).isEqualTo(1);
        assertThat(screenModel.getRollScore(1, 2)).as(gameController.toString()).isEqualTo(4);
        assertThat(screenModel.getFrameScore(1)).as(gameController.toString()).isEqualTo(5);
    }

    @Test
    @DisplayName("Not last frame - spare - add bonus.")
    void spare() {

        gameController.registerRoll(1);
        gameController.registerRoll(9);

        assertThat(screenModel.getRollScore(1, 1)).as(gameController.toString()).isEqualTo(1);
        assertThat(screenModel.getRollScore(1, 2)).as(gameController.toString()).isEqualTo(9);
        assertThat(screenModel.getFrameScore(1)).as(gameController.toString()).isEqualTo(10);

        gameController.registerRoll(2);
        gameController.registerRoll(3);

        // add score of next frame
        assertThat(screenModel.getRollScore(1, 1)).as(gameController.toString()).isEqualTo(1);
        assertThat(screenModel.getRollScore(1, 2)).as(gameController.toString()).isEqualTo(9);
        assertThat(screenModel.getFrameScore(1)).as(gameController.toString()).isEqualTo(12);

        assertThat(screenModel.getRollScore(2, 1)).as(gameController.toString()).isEqualTo(2);
        assertThat(screenModel.getRollScore(2, 2)).as(gameController.toString()).isEqualTo(3);
        assertThat(screenModel.getFrameScore(2)).as(gameController.toString()).isEqualTo(17);
    }

    @Test
    @DisplayName("Outside roll - throws exception.")
    void outside_roll() {

        gameController.registerRoll(1);
        gameController.registerRoll(2); //3

        gameController.registerRoll(3);
        assertThat(gameController.isFinished()).isFalse();
        gameController.registerRoll(4); //no spare

        assertThat(gameController.isFinished()).isTrue();
        assertThatThrownBy(()-> gameController.registerRoll(5)).isInstanceOf(IllegalStateException.class); //extra roll not allowed
    }

    @Test
    @DisplayName("Last frame - spare - add bonus.")
    void spare_last() {

        gameController.registerRoll(0);
        gameController.registerRoll(0); //0

        gameController.registerRoll(3);
        gameController.registerRoll(7); //10
        gameController.registerRoll(4); //extra roll

        assertThat(screenModel.getRollScore(2, 1)).as(gameController.toString()).isEqualTo(3);
        assertThat(screenModel.getRollScore(2, 2)).as(gameController.toString()).isEqualTo(7);
        assertThat(screenModel.getRollScore(2, 3)).as(gameController.toString()).isEqualTo(4);
        assertThat(screenModel.getFrameScore(2)).as(gameController.toString()).isEqualTo(14);
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
