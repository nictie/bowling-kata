package org.niclem.bowling.impl;

public interface RollScoreCalculator extends ScreenUpdater {

    void addRollScoreTo(int[] result, int index);

    void addRollScoreTo(int[] result);

    boolean isSpare();

    boolean isStrike();
}
