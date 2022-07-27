package org.niclem.bowling.impl;

interface RollController {

    boolean addRoll(int hitPins, int frameNumber);

    boolean isFull();
}
