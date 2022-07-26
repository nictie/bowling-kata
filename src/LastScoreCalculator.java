public class LastScoreCalculator extends AbstractScoreCalculator {

    private final AbstractScoreCalculator previousFrame;

    public LastScoreCalculator(LastRollCounter rollCounter, AbstractScoreCalculator previousFrame) {

        super(rollCounter);
        this.previousFrame = previousFrame;
    }

    @Override
    public int calculateScore() {

        final int[] result = { 0 };
        addRollScoreTo(result);
        previousFrame.addFrameScoreTo(result);
        return result[0];
    }
}
