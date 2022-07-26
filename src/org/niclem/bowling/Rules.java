package org.niclem.bowling;

public record Rules(int maxFrames) {

    public Rules {

        if (maxFrames <= 0) {
            throw new IllegalArgumentException("Number of frames must not be zero or smaller");
        }
    }

    public boolean isNextFrame(int number) {

        return number < maxFrames - 1;
    }

    public boolean isLastFrame(int number) {

        return number == maxFrames - 1;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Rules) obj;
        return this.maxFrames == that.maxFrames;
    }

    @Override
    public String toString() {

        return "Rules[" +
                "maxFrames=" + maxFrames + ']';
    }

}
