package org.niclem.bowling.impl;

interface RollScoreCalculator extends GameScoreUpdater {

    void addRollScoreTo(int index, int... result);

    void addRollScoreTo(int... result);

    boolean isSpare();

    boolean isStrike();
}
