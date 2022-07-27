package org.niclem.bowling.impl;

import java.util.ArrayList;

final class LastFrameRollController extends AbstractRollController {

    private boolean isSpare = false;

    LastFrameRollController() {

        super(new ArrayList<>());
    }

    @Override
    protected int calculateMaxRolls() {

        int result = maxRolls;
        if (isSpare()) {
            result = maxRolls + spareBonusRoll;
        }
        return result;
    }

    @Override
    public boolean isSpare() {

        if (super.isSpare()) {
            isSpare = true;
        }
        return isSpare;
    }
}
