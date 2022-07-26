public class NullScoreCalculator extends AbstractScoreCalculator {

    public NullScoreCalculator() {

        super(new NullRollCounter());
    }

    @Override
    public int calculateScore() {

        return 0;
    }
}
