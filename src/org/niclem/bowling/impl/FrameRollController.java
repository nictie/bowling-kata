package org.niclem.bowling.impl;

public interface FrameRollController {

    boolean addRoll(int hitPins, int frameNumber);

    boolean isFull();
}
