public class NullFrame extends AbstractFrame {

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
}
