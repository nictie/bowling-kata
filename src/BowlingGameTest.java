import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BowlingGameTest {

    private ScreenModelImpl screenModel;
    private BowlingGame bowlingGame;

    @BeforeEach
    void setUp() {

        screenModel = new ScreenModelImpl();
        bowlingGame = new BowlingGame(screenModel);
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
    @DisplayName("Open: 1 - 1,x = 1")
    void open_first_roll_is_one() {

        bowlingGame.roll(1);

        assertThat(screenModel.getRollScore(1, 1)).as(bowlingGame.toString()).isEqualTo(1);
        assertThat(screenModel.getRollScore(1, 2)).as(bowlingGame.toString()).isEqualTo(ScreenModel.NO_SCORE);
        assertThat(screenModel.getFrameScore(1)).as(bowlingGame.toString()).isEqualTo(1);
    }

    @Test
    @DisplayName("Open: 1 - 1,4 = 5")
    void open_second_roll_is_four() {

        bowlingGame.roll(1);
        bowlingGame.roll(4);

        assertThat(screenModel.getRollScore(1, 1)).as(bowlingGame.toString()).isEqualTo(1);
        assertThat(screenModel.getRollScore(1, 2)).as(bowlingGame.toString()).isEqualTo(4);
        assertThat(screenModel.getFrameScore(1)).as(bowlingGame.toString()).isEqualTo(5);
    }

    @Test
    @DisplayName("Spare - Add bonus. "
            + "1 - (1,9) = 11"
            + "2 - (1,2) = 14")
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

}
