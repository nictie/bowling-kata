import org.jetbrains.annotations.NotNull;

public abstract class AbstractFrame implements ScreenUpdater {
    protected final int number;
    protected final AbstractRollCounter rollCounter;
    protected final ScoreCalculator scoreCalculator;

    public AbstractFrame(int frameNumber, @NotNull AbstractRollCounter rollCounter, @NotNull ScoreCalculator scoreCalculator) {
        this.number = frameNumber;
        this.rollCounter = rollCounter;
        this.scoreCalculator = scoreCalculator;
    }

    public abstract AbstractFrame roll(int hitPins);

    public abstract boolean isLastFinished();

}
