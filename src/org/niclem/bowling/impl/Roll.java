package org.niclem.bowling.impl;

public final class Roll implements ScreenUpdater {

    private final int frameNumber;
    private final int number;
    private final int score;

    public Roll(int frameNumber, int number, int score) {

        this.frameNumber = frameNumber;
        this.number = number;
        this.score = score;
    }

    @Override
    public void updateScore(ScreenModelUpdater screenModelUpdater) {

        screenModelUpdater.updateRollsOfFrame(frameNumber, number, score);
    }

    public void addScoreTo(int[] result) {

        if (result == null) {
            return;
        }
        result[0] = result[0] + score;
    }

    @Override
    public String toString() {

        return "\nRoll{" +
                "frameNumber=" + frameNumber +
                ", number=" + number +
                ", score=" + score +
                '}';
    }
}
