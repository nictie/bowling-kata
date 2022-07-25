public class InitialFrame extends AbstractFrame {

    private Frame nextFrame;
    private final int maxFrames;

    public InitialFrame(int maxFrames) {

        super(-1);
        this.maxFrames = maxFrames;
    }

    @Override
    public void updateScore(ScreenModelUpdater screenModelUpdater) {

        nextFrame.updateScore(screenModelUpdater);
    }

    @Override
    public int calculateScore() {

        return 0;
    }

    @Override
    public Frame roll(int hitPins) {

        nextFrame = new Frame(1, this, maxFrames);
        nextFrame.roll(hitPins);
        return nextFrame;
    }

    @Override
    public boolean isLastFinished() {

        return false;
    }
}
