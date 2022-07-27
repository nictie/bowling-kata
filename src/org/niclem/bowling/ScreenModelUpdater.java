package org.niclem.bowling;

public interface ScreenModelUpdater {

    Integer NO_SCORE = -1;

    void updateFrameScore(int number, int score);

    void updateRollsOfFrame(int frameNumber, int rollNumber, int score);
}
