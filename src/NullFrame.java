public class NullFrame extends AbstractFrame {

    public NullFrame() {

        super(-2);
    }

    @Override
    public void updateScore(ScreenModelUpdater screenModelUpdater) {

    }

    @Override
    public int calculateScore() {

        return 0;
    }

    @Override
    public Frame roll(int hitPins) {

        throw new UnsupportedOperationException("Null frame cannot be rolled.");
    }

    @Override
    public boolean isLastFinished() {

        return false;
    }

}
