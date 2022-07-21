import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class BowlingGame {
    private Frame currentFrame;

    public void roll(int hitPins) {

        if (currentFrame == null) {
            currentFrame = new Frame(1);
        }
        currentFrame = currentFrame.addRoll(hitPins);
    }

    public void applyTotalResult(Consumer<Integer> totalScoreConsumer) {
        totalScoreConsumer.accept(currentFrame.getScore());
    }

    public void applyCurrentFrameResult(BiConsumer<Integer, Integer> scoreframeConsumer) {
        scoreframeConsumer.accept(currentFrame.getNumber(), currentFrame.getScore());
    }

    public int getTotalScore() {
        return currentFrame.getScore();
    }
}
