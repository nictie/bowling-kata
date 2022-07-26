public class NullFrame extends AbstractFrame {

    public NullFrame() {

        super(-2, new NullRollCounter(), new NullScoreCalculator());
    }

    @Override
    public Frame roll(int hitPins) {

        throw new UnsupportedOperationException("Null frame cannot be rolled.");
    }

    @Override
    public void updateScore(ScreenModelUpdater screenModelUpdater) {

    }

    @Override
    public boolean isLastFinished() {

        return false;
    }

}
