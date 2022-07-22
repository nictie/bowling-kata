public final class Roll implements ScoreKeeper, ScoreCalculator {

    private final int frameNumber;
    private final int number;
    private final int score;

    public Roll(int frameNumber, int number, int score) {

        this.frameNumber = frameNumber;
        this.number = number;
        this.score = score;
    }

    @Override
    public void writeTo(ScreenModelImpl screenModel) {

        screenModel.addScoreRollToFrame(frameNumber, number, score);
    }

    @Override
    public int calculateScore() {

        return score;
    }

    @Override
    public String toString() {

        return "\nRoll{" +
                "frameNumber=" + frameNumber +
                ", number=" + number +
                ", score=" + score +
                '}';
    }
}
