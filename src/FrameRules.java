import org.jetbrains.annotations.NotNull;

public class FrameRules {

    final int maxRolls;
    final boolean isLastFrame;
    private final Rules rules;

    public FrameRules(int maxRolls, boolean isLastFrame, @NotNull Rules rules) {

        this.maxRolls = maxRolls;
        this.isLastFrame = isLastFrame;
        this.rules = rules;
    }

    public AbstractFrame createNextFrame(AbstractFrame previousFrame) {

        if (isLastFrame) {
            throw new IllegalStateException("Next frame is not allowed");
        }
        return new Frame(previousFrame.number + 1, previousFrame, rules);
    }

    public boolean isRollAllowed(int numberOfRolls) {

        return numberOfRolls < maxRolls;
    }

}
