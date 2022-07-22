public abstract class AbstractFrame implements ScoreKeeper, ScoreCalculator {

    public abstract AbstractFrame addRoll(int hitPins);

    @Override
    public abstract void writeTo(ScreenModelImpl screenModel);

    @Override
    public abstract int calculateScore();

    protected abstract void addBonus(Frame frame, int[] result);
}
