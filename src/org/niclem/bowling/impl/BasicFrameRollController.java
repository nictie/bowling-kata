package org.niclem.bowling.impl;

import java.util.ArrayList;

final class BasicFrameRollController extends AbstractRollController {

    BasicFrameRollController() {

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
