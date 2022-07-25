import org.jetbrains.annotations.NotNull;

public class LastFrame extends AbstractFrame {

    private static final int maxRolls = 2;
    private static final int highScore = 10;
    private static final int spareBonusRoll = 1;
    private final AbstractFrame previousFrame;
    private boolean isSpare;

    public LastFrame(int frameNumber, @NotNull AbstractFrame previousFrame) {

        super(frameNumber);
        this.previousFrame = previousFrame;
    }

    @Override
    public AbstractFrame roll(int hitPins) {

        AbstractFrame result;

        if (rolls.size() < maxRolls || isSpare() && rolls.size() < maxRolls + spareBonusRoll) {
            Roll roll = new Roll(number, rolls.size() + 1, hitPins);
            rolls.add(roll);
            result = this;
        } else {
            throw new IllegalStateException("Game is over - no further roll allowed");
        }
        return result;
    }

    @Override
    public void updateScore(ScreenModelUpdater screenModelUpdater) {

        screenModelUpdater.updateFrameScore(number, calculateScore());
        rolls.forEach(roll -> roll.updateScore(screenModelUpdater));
    }

    @Override
    public int calculateScore() {

        final int[] result = { 0 };
        addRollScoreTo(result);
        previousFrame.addFrameScoreTo(result);
        return result[0];
    }

    @Override
    public boolean isLastFinished() {

        return rolls.size() == getMaxRolls();
    }

    private boolean isSpare() {

        boolean result = rolls.size() == maxRolls && getRollScore() == highScore;
        if (result) {
            isSpare = true;
        }
        return isSpare;
    }

    private int getMaxRolls() {

        int result = maxRolls;
        if (isSpare()) {
            result = maxRolls + spareBonusRoll;
        }
        return result;
    }

    private int getRollScore() {

        int[] rollScore = new int[1];
        addRollScoreTo(rollScore);
        return rollScore[0];
    }

    @Override
    public String toString() {

        return "\nLastFrame{" +
                "number=" + number +
                ", rolls=" + rolls +
                '}';
    }
}
