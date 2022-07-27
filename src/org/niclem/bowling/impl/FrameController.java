package org.niclem.bowling.impl;

public interface FrameController {

    boolean addRoll(int hitPins, int frameNumber);

    boolean isFull();
}
