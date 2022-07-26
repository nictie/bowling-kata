public class FrameScoreCalculator extends AbstractScoreCalculator {

    private final AbstractScoreCalculator previousFrame;
    private AbstractScoreCalculator nextFrame;

    public FrameScoreCalculator(RollCounter rollCounter, AbstractScoreCalculator previousFrame) {

        super(rollCounter);

        this.previousFrame = previousFrame;
        this.nextFrame = new NullScoreCalculator();
    }

    public void setNext(AbstractScoreCalculator aFrame) {

        nextFrame = aFrame;
    }


    @Override
    public int calculateScore() {

        final int[] result = { 0 };
        addRollScoreTo(result);
        if (rollCounter.isSpare()) {
            nextFrame.addRollScoreTo(result, 0);
        } else if (rollCounter.isStrike()) {
            nextFrame.addRollScoreTo(result, 0);
            nextFrame.addRollScoreTo(result, 1);
        }
        previousFrame.addFrameScoreTo(result);
        return result[0];
    }
}
