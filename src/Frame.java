import org.jetbrains.annotations.NotNull;

public class Frame extends AbstractFrame {
    private final int maxFrames;
    private final FrameScoreCalculator scoreCalculator;
    private AbstractFrame nextFrame;

    public Frame(int number, int maxFrames, @NotNull RollCounter rollCounter, @NotNull FrameScoreCalculator scoreCalculator) {

        super(number, rollCounter, scoreCalculator);
        this.maxFrames = maxFrames;
        this.scoreCalculator = scoreCalculator;
        this.nextFrame = new NullFrame();
    }

    @Override
    public AbstractFrame roll(int hitPins) {

        AbstractFrame result;

        if (rollCounter.addRoll(hitPins, number)) {
            result = this;
        } else {
            if (number < maxFrames - 1) {
                var nextRollCounter = new RollCounter();
                var nextScoreCalculator = new FrameScoreCalculator(nextRollCounter, scoreCalculator);
                nextFrame = new Frame(number + 1, maxFrames, nextRollCounter, nextScoreCalculator);
                nextFrame.roll(hitPins);
                scoreCalculator.setNext(nextScoreCalculator);
                result = nextFrame;
            } else if (number == maxFrames - 1) {
                var nextRollCounter = new LastRollCounter();
                var nextScoreCalculator = new LastScoreCalculator(nextRollCounter, this.scoreCalculator);
                nextFrame = new LastFrame(number + 1, nextRollCounter, nextScoreCalculator);
                nextFrame.roll(hitPins);
                scoreCalculator.setNext(nextScoreCalculator);
                result = nextFrame;
            } else {
                throw new IllegalStateException("Frame is over - no further roll allowed");
            }
        }
        return result;
    }

    @Override
    public void updateScore(ScreenModelUpdater screenModelUpdater) {

        screenModelUpdater.updateFrameScore(number, scoreCalculator.calculateScore());
        rollCounter.updateScore(screenModelUpdater);
        nextFrame.updateScore(screenModelUpdater);
    }

    @Override
    public boolean isLastFinished() {

        return false;
    }

    @Override
    public String toString() {

        return "\nFrame{" +
                "number=" + number +
                ", rollsCounter=" + rollCounter +
                '}';
    }
}
