package org.niclem.bowling.impl;

interface RollScoreCalculator extends ScreenUpdater {

    void addRollScoreTo(int[] result, int index);

    void addRollScoreTo(int[] result);

    boolean isSpare();

    boolean isStrike();
}
