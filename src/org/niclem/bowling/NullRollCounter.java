package org.niclem.bowling;

import java.util.Collections;

public class NullRollCounter extends AbstractRollCounter {

    public NullRollCounter() {

        super(Collections.emptyList());
    }

    @Override
    protected boolean isSpare() {

        return false;
    }

    @Override
    protected int getMaxRolls() {

        return 0;
    }

    @Override
    public void updateScore(ScreenModelUpdater screenModelUpdater) {

    }

    @Override
    boolean addRoll(int hitPins, int frameNumber) {
        return false;
    }

    @Override
    int getRollScore() {

        return 0;
    }

    @Override
    void addRollScoreTo(int[] result, int index) {

    }

    @Override
    void addRollScoreTo(int[] result) {

    }

    @Override
    boolean isStrike() {

        return false;
    }

    @Override
    boolean isFull() {

        return false;
    }

}
