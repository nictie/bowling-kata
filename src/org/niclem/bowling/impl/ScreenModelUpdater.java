package org.niclem.bowling.impl;

public interface ScreenModelUpdater {
    Integer NO_SCORE = -1;

    void updateModel(ScreenUpdater screenUpdater);

    void updateFrameScore(int number, int score);

    void updateRollsOfFrame(int frameNumber, int rollNumber, int score);
}
