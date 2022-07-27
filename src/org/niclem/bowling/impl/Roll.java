package org.niclem.bowling.impl;

final class Roll implements GameScoreUpdater {

    private final int frameNumber;
    private final int number;
    private final int score;

    Roll(int frameNumber, int number, int score) {

        this.frameNumber = frameNumber;
        this.number = number;
        this.score = score;
    }

    @Override
    public void updateScore(GameScoreResult gameScoreResult) {

        gameScoreResult.updateRollsOfFrame(frameNumber, number, score);
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
