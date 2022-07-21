public interface ScreenModel {
    Integer NO_SCORE = -1;

    void updateModel(ScoreKeeper scoreKeeper);

    void updateGameScore(int aTotalScore);

    void updateFrameScore(int number, int score);

    void addScoreRollToFrame(int frameNumber, int rollNumber, int score);
}
