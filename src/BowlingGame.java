public class BowlingGame implements ScoreKeeper, ScoreCalculator {
    private final ScreenModel screenModel;
    private Frame currentFrame;

    public BowlingGame(ScreenModel screenModel) {

        this.screenModel = screenModel;
    }

    public void roll(int hitPins) {

        if (currentFrame == null) {
            currentFrame = new Frame(1);
        }
        currentFrame = currentFrame.addRoll(hitPins);
        screenModel.updateModel(this);
    }

    @Override
    public void writeTo(ScreenModelImpl screenModel) {
        screenModel.updateGameScore(calculateScore());
        currentFrame.writeTo(screenModel);
    }

    public int calculateScore() {
        return currentFrame.calculateScore();
    }
}
