public abstract class AbstractFrame implements ScoreKeeper, ScoreCalculator {

    protected final int number;

    public AbstractFrame(int number) {this.number = number;}

    public abstract AbstractFrame addRoll(int hitPins);

    @Override
    public abstract void writeTo(ScreenModelImpl screenModel);

    @Override
    public abstract int calculateScore();

    protected abstract void addBonus(Frame frame, int[] result);

    public abstract boolean isLastFrame(int maxFrames);

    abstract boolean isSpare();
}
