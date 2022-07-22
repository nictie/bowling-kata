public class Rules {

    final int maxFrames;

    public Rules(int maxFrames) {

        this.maxFrames = maxFrames;
    }

    public FrameRules forFrame(AbstractFrame currentFrame) {

        if (currentFrame.isLastFrame(maxFrames)) {
            if (currentFrame.isSpare()) {
                return new FrameRules(3, true, this);
            } else {
                return new FrameRules(2, true, this);
            }
        } else {
            return new FrameRules(2, false, this);
        }
    }

}
