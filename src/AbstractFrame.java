import org.jetbrains.annotations.NotNull;

public abstract class AbstractFrame implements ScreenUpdater {
    protected final int number;
    protected final AbstractRollCounter rollCounter;

    public AbstractFrame(int frameNumber, @NotNull AbstractRollCounter rollCounter) {
        number = frameNumber;
        this.rollCounter = rollCounter;
    }

    public abstract AbstractFrame roll(int hitPins);

    protected abstract int calculateScore();

    final void addRollScoreTo(int[] result, int index) {

       rollCounter.addRollScoreTo(result, index);
    }

    final void addFrameScoreTo(int[] result) {

        result[0] = result[0] + calculateScore();
    }

    public abstract boolean isLastFinished();

}
