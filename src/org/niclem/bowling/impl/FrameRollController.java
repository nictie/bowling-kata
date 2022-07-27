package org.niclem.bowling.impl;

import java.util.ArrayList;

final class FrameRollController extends RollControllerAbstract {

    FrameRollController() {

        super(new ArrayList<>());
    }

    @Override
    protected int calculateMaxRolls() {

        int result = maxRolls;
        if (isStrike()) {
            result = 1;
        }
        return result;
    }
}
