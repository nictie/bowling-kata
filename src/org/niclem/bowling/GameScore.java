package org.niclem.bowling;

public interface GameScore {

    Integer NO_SCORE = -1;

    Integer getFrameScore(int frameNumber);

    Integer getRollScore(int frameNumber, int rollNumber);
}
