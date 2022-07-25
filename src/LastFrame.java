import org.jetbrains.annotations.NotNull;

public class LastFrame extends AbstractFrame {

    private final AbstractFrame previousFrame;

    public LastFrame(int frameNumber, @NotNull AbstractFrame previousFrame) {

        super(frameNumber, new LastRollCounter());
        this.previousFrame = previousFrame;
    }

    @Override
    public AbstractFrame roll(int hitPins) {

        AbstractFrame result;

        if (rollCounter.addRoll(hitPins, number)) {
            result = this;
        } else {
            throw new IllegalStateException("Game is over - no further roll allowed");
        }
        return result;
    }

    @Override
    public void updateScore(ScreenModelUpdater screenModelUpdater) {

        screenModelUpdater.updateFrameScore(number, calculateScore());
        rollCounter.updateScore(screenModelUpdater);
    }

    @Override
    public int calculateScore() {

        final int[] result = { 0 };
        rollCounter.addRollScoreTo(result);
        previousFrame.addFrameScoreTo(result);
        return result[0];
    }

    @Override
    public boolean isLastFinished() {

        return rollCounter.isFull();
    }

    @Override
    public String toString() {

        return "\nLastFrame{" +
                "number=" + number +
                ", rollCouunter=" + rollCounter +
                '}';
    }
}
