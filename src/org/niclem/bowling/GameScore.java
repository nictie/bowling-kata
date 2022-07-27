package org.niclem.bowling;

public interface GameScore {

    Integer getFrameScore(int frameNumber);

    Integer getRollScore(int frameNumber, int rollNumber);
}
