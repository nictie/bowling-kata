public class Rules {

    final int maxFrames;
    private final int maxRolls;
    private final int minRolls;

    private Rules(int maxFrames, int minRolls, int maxRolls) {

        this.maxFrames = maxFrames;
        this.minRolls = minRolls;
        this.maxRolls = maxRolls;
    }

    public static Rules createRules(int maxFrames) {

        return new Rules(maxFrames, 2, 3);
    }

    public FrameRules forFrame(AbstractFrame currentFrame) {

        if (currentFrame.isLastFrame(maxFrames)) {
            if (currentFrame.isSpare()) {
                return new FrameRules(maxRolls, true, this);
            } else {
                return new FrameRules(minRolls, true, this);
            }
        } else {
            return new FrameRules(minRolls, false, this);
        }
    }
}
