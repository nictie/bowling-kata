package org.niclem.bowling.impl;

public abstract class FrameScoreCalculatorAbstract implements ScoreCalculator {

    protected final RollScoreCalculator controller;

    public FrameScoreCalculatorAbstract(RollScoreCalculator controller) {

        this.controller = controller;
    }

    public abstract int calculateScore();

    final void addFrameScoreTo(int[] result) {

        result[0] = result[0] + calculateScore();
    }

    final void addRollScoreTo(int[] result, int index) {

        controller.addRollScoreTo(result, index);
    }
}
