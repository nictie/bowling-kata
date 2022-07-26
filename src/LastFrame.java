import org.jetbrains.annotations.NotNull;

public class LastFrame extends AbstractFrame {

    public LastFrame(int frameNumber, @NotNull LastRollCounter rollCounter, @NotNull LastScoreCalculator scoreCalculator) {

        super(frameNumber, rollCounter, scoreCalculator);
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

        screenModelUpdater.updateFrameScore(number, scoreCalculator.calculateScore());
        rollCounter.updateScore(screenModelUpdater);
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
