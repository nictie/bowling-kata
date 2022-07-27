package org.niclem.bowling.impl;

interface GameScoreResult {

    void updateFrameScore(int number, int score);

    void updateRollsOfFrame(int frameNumber, int rollNumber, int score);
}
