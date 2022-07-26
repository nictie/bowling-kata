package org.niclem.bowling;

public interface ScreenModel {

    Integer getFrameScore(int frameNumber);

    Integer getRollScore(int frameNumber, int rollNumber);
}
