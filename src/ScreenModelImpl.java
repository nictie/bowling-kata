import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class ScreenModelImpl implements ScreenModel {

    private final int[] frameScores;
    private final Map<Integer, int[]> rollsPerFrame;
    private final int numberOfFrames;
    private Integer totalScore;

    public ScreenModelImpl() {

        numberOfFrames = 10;
        totalScore = NO_SCORE;
        frameScores = initFrameScores(numberOfFrames);
        rollsPerFrame = initRollsPerFrameScore(numberOfFrames);
    }

    public Integer getTotalScore() {

        return totalScore;
    }

    @Override
    public void updateModel(ScoreKeeper scoreKeeper) {

        scoreKeeper.writeTo(this);
    }

    private int[] initFrameScores(int numberOfFrames) {

        final int[] frameScore;
        frameScore = new int[numberOfFrames];
        for (int i = 0; i < numberOfFrames; i++) {
            frameScore[i] = NO_SCORE;
        }
        return frameScore;
    }

    private Map<Integer, int[]> initRollsPerFrameScore(int numberOfFrames) {

        Map<Integer, int[]> result = new LinkedHashMap<>();
        for (int i = 0; i < numberOfFrames; i++) {
            result.put(i, new int[] { NO_SCORE, NO_SCORE });
        }
        return result;
    }

    @Override
    public void updateGameScore(int aTotalScore) {

        totalScore = aTotalScore;
    }

    @Override
    public void updateFrameScore(int number, int score) {

        if (number < 0 || number >= frameScores.length) {
            throw new IllegalArgumentException("Frame with given number is not allowed " + number);
        }
        frameScores[number - 1] = score;
    }

    @Override
    public void addScoreRollToFrame(int frameNumber, int rollNumber, int score) {

        rollsPerFrame.get(frameNumber -1)[rollNumber - 1] = score;
    }

    public Integer getFrameScore(int frameNumber) {

        return frameScores[frameNumber - 1];
    }

    public Integer getRollScore(int frameNumber, int rollNumber) {

        return rollsPerFrame.get(frameNumber - 1)[rollNumber - 1];
    }

    @Override
    public String toString() {

        return "ScreenModelImpl{" +
                "totalScore=" + totalScore +
                ", frameScores=" + Arrays.toString(frameScores) +
                ", rollsPerFrame[0]=" + Arrays.toString( rollsPerFrame.get(0)) +
                ", rollsPerFrame[1]=" + Arrays.toString( rollsPerFrame.get(1)) +
                ", rollsPerFrame[2]=" + Arrays.toString( rollsPerFrame.get(2)) +
                ", rollsPerFrame[3]=" + Arrays.toString( rollsPerFrame.get(3)) +
                ", rollsPerFrame[4]=" + Arrays.toString( rollsPerFrame.get(4)) +
                ", rollsPerFrame[5]=" + Arrays.toString( rollsPerFrame.get(5)) +
                ", rollsPerFrame[6]=" + Arrays.toString( rollsPerFrame.get(6)) +
                ", rollsPerFrame[7]=" + Arrays.toString( rollsPerFrame.get(7)) +
                ", rollsPerFrame[8]=" + Arrays.toString( rollsPerFrame.get(8)) +
                ", rollsPerFrame[9]=" + Arrays.toString( rollsPerFrame.get(9)) +
                ", numberOfFrames=" + numberOfFrames +
                '}';
    }
}
