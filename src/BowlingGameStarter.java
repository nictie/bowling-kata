import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class BowlingGameStarter {

    private final BowlingGame bowlingGame;
    private Consumer<Integer> totalScoreConsumer;
    private BiConsumer<Integer, Integer> scoreframeConsumer;

    BowlingGameStarter() {
        bowlingGame = new BowlingGame();
    }

    public void init(Consumer<Integer> totalScoreConsumer, BiConsumer<Integer, Integer> scoreframeConsumer) {
        this.totalScoreConsumer = totalScoreConsumer;
        this.scoreframeConsumer = scoreframeConsumer;
    }

    public void roll(int hitPins) {

        bowlingGame.roll(hitPins);
        bowlingGame.applyTotalResult(totalScoreConsumer);
        bowlingGame.applyCurrentFrameResult(scoreframeConsumer);
    }

    public int getTotalScore() {

        return bowlingGame.getTotalScore();
    }
}
