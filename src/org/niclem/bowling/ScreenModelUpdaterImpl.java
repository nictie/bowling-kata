package org.niclem.bowling;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class ScreenModelUpdaterImpl implements ScreenModelUpdater, ScreenModel {

    private final int[] frameScores;
    private final Map<Integer, int[]> rollsPerFrame;
    private final int numberOfFrames;

    public ScreenModelUpdaterImpl(int maxFrames) {

        this.numberOfFrames = maxFrames;
        frameScores = initFrameScores(numberOfFrames);
        rollsPerFrame = initRollsPerFrameScore(numberOfFrames);
    }

    @Override
    public void updateModel(ScreenUpdater screenUpdater) {

        screenUpdater.updateScore(this);
    }

    @Override
    public void updateFrameScore(int number, int score) {

        final int frameIndex = number -1;
        if (frameIndex < 0 || frameIndex >= frameScores.length) {
            throw new IllegalArgumentException("org.niclem.bowling.Frame with given number is not allowed " + number);
        }
        frameScores[frameIndex] = score;
    }

    @Override
    public void updateRollsOfFrame(int frameNumber, int rollNumber, int score) {

        rollsPerFrame.get(frameNumber - 1)[rollNumber - 1] = score;
    }

    @Override
    public Integer getFrameScore(int frameNumber) {

        return frameScores[frameNumber - 1];
    }

    @Override
    public Integer getRollScore(int frameNumber, int rollNumber) {

        return rollsPerFrame.get(frameNumber - 1)[rollNumber - 1];
    }

    private Map<Integer, int[]> initRollsPerFrameScore(int numberOfFrames) {

        Map<Integer, int[]> result = new LinkedHashMap<>();
        for (int i = 0; i < numberOfFrames; i++) {
            int[] rollScore = new int[] { NO_SCORE, NO_SCORE };
            if (i == numberOfFrames - 1) {
                rollScore = new int[] { NO_SCORE, NO_SCORE, NO_SCORE };
                result.put(i, rollScore);
            }
            result.put(i, rollScore);
        }
        return result;
    }

    private int[] initFrameScores(int numberOfFrames) {

        final int[] frameScore;
        frameScore = new int[numberOfFrames];
        for (int i = 0; i < numberOfFrames; i++) {
            frameScore[i] = NO_SCORE;
        }
        return frameScore;
    }

    @Override
    public String toString() {

        return "ScreenModelImpl{" +
                "\nframeScores=" + Arrays.toString(frameScores) +
                "\n, rollsPerFrame[0]=" + Arrays.toString(rollsPerFrame.get(0)) +
                "\n, rollsPerFrame[1]=" + Arrays.toString(rollsPerFrame.get(1)) +
                "\n, rollsPerFrame[2]=" + Arrays.toString(rollsPerFrame.get(2)) +
                "\n, rollsPerFrame[3]=" + Arrays.toString(rollsPerFrame.get(3)) +
                "\n, rollsPerFrame[4]=" + Arrays.toString(rollsPerFrame.get(4)) +
                "\n, rollsPerFrame[5]=" + Arrays.toString(rollsPerFrame.get(5)) +
                "\n, rollsPerFrame[6]=" + Arrays.toString(rollsPerFrame.get(6)) +
                "\n, rollsPerFrame[7]=" + Arrays.toString(rollsPerFrame.get(7)) +
                "\n, rollsPerFrame[8]=" + Arrays.toString(rollsPerFrame.get(8)) +
                "\n, rollsPerFrame[9]=" + Arrays.toString(rollsPerFrame.get(9)) +
                "\n, numberOfFrames=" + numberOfFrames +
                '}';
    }
}
