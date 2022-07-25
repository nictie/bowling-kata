import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFrame implements ScreenUpdater {
    protected final int number;
    protected final List<Roll> rolls;

    public AbstractFrame(int frameNumber) {
        number = frameNumber;
        rolls = new ArrayList<>();
    }

    public abstract AbstractFrame roll(int hitPins);

    protected abstract int calculateScore();

    final void addRollScoreTo(int[] result, int index) {

        if(rolls.size() < index + 1) {
            return;
        }
        result[0] = result[0] + rolls.get(index).calculateScore();
    }

    final void addFrameScoreTo(int[] result) {

        result[0] = result[0] + calculateScore();
    }

    void addRollScoreTo(int[] result) {

        rolls.forEach(roll -> result[0] = result[0] + roll.calculateScore());
    }

    public abstract boolean isLastFinished();

}
