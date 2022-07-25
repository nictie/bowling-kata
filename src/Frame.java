import org.jetbrains.annotations.NotNull;

public class Frame extends AbstractFrame {
    private final int maxFrames;
    private final AbstractFrame previousFrame;
    private AbstractFrame nextFrame;

    public Frame(int number, @NotNull AbstractFrame previousFrame, int maxFrames) {

        super(number, new RollCounter());
        this.maxFrames = maxFrames;
        this.previousFrame = previousFrame;
        this.nextFrame = new NullFrame();
    }

    @Override
    public AbstractFrame roll(int hitPins) {

        AbstractFrame result;

        if (rollCounter.addRoll(hitPins, number)) {
            result = this;
        } else {
            if (number < maxFrames - 1) {
                nextFrame = new Frame(number + 1, this, maxFrames);
                nextFrame.roll(hitPins);
                result = nextFrame;
            } else if (number == maxFrames - 1) {
                nextFrame = new LastFrame(number + 1, this);
                nextFrame.roll(hitPins);
                result = nextFrame;
            } else {
                throw new IllegalStateException("Frame is over - no further roll allowed");
            }
        }
        return result;
    }

    @Override
    public void updateScore(ScreenModelUpdater screenModelUpdater) {

        screenModelUpdater.updateFrameScore(number, calculateScore());
        rollCounter.updateScore(screenModelUpdater);
        nextFrame.updateScore(screenModelUpdater);
    }

    @Override
    public int calculateScore() {

        final int[] result = { 0 };
        rollCounter.addRollScoreTo(result);
        if (rollCounter.isSpare()) {
            nextFrame.addRollScoreTo(result, 0);
        } else if (rollCounter.isStrike()) {
            nextFrame.addRollScoreTo(result, 0);
            nextFrame.addRollScoreTo(result, 1);
        }
        previousFrame.addFrameScoreTo(result);
        return result[0];
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
