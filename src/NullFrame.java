public class NullFrame extends AbstractFrame {

    public NullFrame() {

        super(-1);
    }

    @Override
    public void writeTo(ScreenModelImpl screenModel) {
    }

    @Override
    public int calculateScore() {
        return 0;
    }

    @Override
    public Frame addRoll(int hitPins) {
        return null;
    }

    @Override
    protected void addBonus(Frame frame, int[] result) {
    }

    @Override
    public boolean isLastFrame(int maxFrames) {

        return false;
    }

    @Override
    boolean isSpare() {

        return false;
    }
}
