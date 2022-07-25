import org.jetbrains.annotations.NotNull;

public class Frame extends AbstractFrame {
    private static final int maxRolls = 2;
    private static final int highScore = 10;

    private final int maxFrames;
    private final AbstractFrame previousFrame;
    private AbstractFrame nextFrame;

    public Frame(int number, @NotNull AbstractFrame previousFrame, int maxFrames) {

        super(number);
        this.maxFrames = maxFrames;
        this.previousFrame = previousFrame;
        this.nextFrame = new NullFrame();
    }

    @Override
    public AbstractFrame roll(int hitPins) {

        AbstractFrame result;

        if (rolls.size() < getMaxRolls()) {
            Roll roll = new Roll(number, rolls.size() + 1, hitPins);
            rolls.add(roll);
            result = this;
        } else if (number < maxFrames - 1) {
            nextFrame = new Frame(number + 1, this, maxFrames);
            nextFrame.roll(hitPins);
            result = nextFrame;
        } else if (number == maxFrames - 1) {
            nextFrame = new LastFrame(number + 1, this);
            nextFrame.roll(hitPins);
            result = nextFrame;
        } else {
            throw new IllegalStateException("Game is over - no further roll allowed");
        }
        return result;
    }

    @Override
    public void updateScore(ScreenModelUpdater screenModelUpdater) {

        screenModelUpdater.updateFrameScore(number, calculateScore());
        rolls.forEach(roll -> roll.updateScore(screenModelUpdater));
        nextFrame.updateScore(screenModelUpdater);
    }

    @Override
    public int calculateScore() {

        final int[] result = { 0 };
        addRollScoreTo(result);
        if (isSpare()) {
            nextFrame.addRollScoreTo(result, 0);
        } else if(isStrike()) {
            nextFrame.addRollScoreTo(result, 0);
            nextFrame.addRollScoreTo(result, 1);
        }
        previousFrame.addFrameScoreTo(result);
        return result[0];
    }

    private boolean isSpare() {

        return rolls.size() == maxRolls && getRollScore() == highScore;
    }

    private int getMaxRolls() {

        int result = maxRolls;
        if (isStrike()) {
            result = maxRolls -1;
        }
        return result;
    }

    private boolean isStrike() {

        return rolls.size() < maxRolls && getRollScore() == highScore;
    }

    @Override
    public boolean isLastFinished() {

        return false;
    }

    private int getRollScore() {

        int[] rollScore = new int[1];
        addRollScoreTo(rollScore);
        return rollScore[0];
    }

    @Override
    public String toString() {

        return "\nFrame{" +
                "number=" + number +
                ", rolls=" + rolls +
                '}';
    }
}
