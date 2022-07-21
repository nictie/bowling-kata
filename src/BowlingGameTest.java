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

        assertThat(screenModel.getTotalScore()).as(screenModel.toString()).isEqualTo(ScreenModel.NO_SCORE);
        assertThat(screenModel.getFrameScore(1)).as(screenModel.toString()).isEqualTo(ScreenModel.NO_SCORE);
        assertThat(screenModel.getRollScore(1, 1)).as(screenModel.toString()).isEqualTo(ScreenModel.NO_SCORE);
        assertThat(screenModel.getRollScore(1, 2)).as(screenModel.toString()).isEqualTo(ScreenModel.NO_SCORE);
    }

    @Test
    @DisplayName("The first roll of the game is 1. The score of the frame and the total score should be 1.")
    void open_first_roll_is_one() {

        bowlingGame.roll(1);

        assertThat(screenModel.getTotalScore()).as(screenModel.toString()).isEqualTo(1);
        assertThat(screenModel.getFrameScore(1)).as(screenModel.toString()).isEqualTo(1);
        assertThat(screenModel.getRollScore(1, 1)).as(screenModel.toString()).isEqualTo(1);
        assertThat(screenModel.getRollScore(1, 2)).as(screenModel.toString()).isEqualTo(ScreenModel.NO_SCORE);
    }

    @Test
    @DisplayName("The first roll of the game is 1 the second roll is 4. The score of the frame and the total score should be 5.")
    void open_second_roll_is_four() {

        bowlingGame.roll(1);
        bowlingGame.roll(4);

        assertThat(screenModel.getTotalScore()).as(screenModel.toString()).isEqualTo(5);
        assertThat(screenModel.getFrameScore(1)).as(screenModel.toString()).isEqualTo(5);
        assertThat(screenModel.getRollScore(1, 1)).as(screenModel.toString()).isEqualTo(1);
        assertThat(screenModel.getRollScore(1, 2)).as(screenModel.toString()).isEqualTo(4);
    }

    @Test
    @DisplayName(""
            + "The first frame score is (1,9) per roll. "
            + "The next frame score is (1/2) per roll"
            + "The score of the frame and the total score should be 5.")
    void spare_second_roll_is_four() {

        bowlingGame.roll(1);
        bowlingGame.roll(9);

        assertThat(screenModel.getTotalScore()).as(screenModel.toString()).isEqualTo(10);
        assertThat(screenModel.getFrameScore(1)).as(screenModel.toString()).isEqualTo(10);
        assertThat(screenModel.getRollScore(1, 1)).as(screenModel.toString()).isEqualTo(1);
        assertThat(screenModel.getRollScore(1, 2)).as(screenModel.toString()).isEqualTo(9);

        bowlingGame.roll(1);
        bowlingGame.roll(2);

        // add score of next frame
        assertThat(screenModel.getTotalScore()).as(screenModel.toString()).isEqualTo(13);
        assertThat(screenModel.getFrameScore(1)).as(screenModel.toString()).isEqualTo(13);
        assertThat(screenModel.getRollScore(1, 1)).as(screenModel.toString()).isEqualTo(1);
        assertThat(screenModel.getRollScore(1, 2)).as(screenModel.toString()).isEqualTo(9);

    }

}
