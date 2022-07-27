package org.niclem.bowling.impl;

interface FrameRollController {

    boolean addRoll(int hitPins, int frameNumber);

    boolean isFull();
}
