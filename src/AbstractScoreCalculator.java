public abstract class AbstractScoreCalculator implements ScoreCalculator {

    protected final AbstractRollCounter rollCounter;

    public AbstractScoreCalculator(AbstractRollCounter rollCounter) {

        this.rollCounter = rollCounter;
    }

    public abstract int calculateScore();

    final void addFrameScoreTo(int[] result) {

        result[0] = result[0] + calculateScore();
    }

    final void addRollScoreTo(int[] result, int index) {

        rollCounter.addRollScoreTo(result, index);
    }

    final void addRollScoreTo(int[] result) {

        rollCounter.addRollScoreTo(result);
    }
}
