package org.niclem.bowling.impl;

import org.niclem.bowling.Rules;

public interface Frame extends GameScoreUpdater {

    static Frame create(Rules rules) {

        return new InitialFrame(rules);
    }

    Frame roll(int hitPins);

    boolean isLastFinished();
}
