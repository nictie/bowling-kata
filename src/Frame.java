import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.NotNull;

public class Frame extends AbstractFrame {
    private final int maxNumberOfRolls = 2;
    private final AbstractFrame previousFrame;
    private final List<Roll> rolls;
    private final Rules rules;
    private AbstractFrame nextFrame;

    public Frame(int number, @NotNull AbstractFrame previousFrame, @NotNull Rules rules) {

        super(number);
        this.rules = rules;
        this.previousFrame = previousFrame;
        this.nextFrame = new NullFrame();
        this.rolls = new ArrayList<>();
    }

    @Override
    public AbstractFrame addRoll(int hitPins) {

        AbstractFrame result;

        FrameRules frameRules = rules.forFrame(this);
        if (rolls.size() < frameRules.maxRolls) {
            Roll roll = new Roll(number, rolls.size() + 1, hitPins);
            rolls.add(roll);
            result = this;
        } else {
            nextFrame = frameRules.createNextFrame(this);
            nextFrame.addRoll(hitPins);
            result = nextFrame;
        }
        return result;

    }

    @Override
    public void writeTo(ScreenModelImpl screenModel) {

        screenModel.updateFrameScore(number, calculateScore());
        rolls.forEach(roll -> roll.writeTo(screenModel));
    }

    @Override
    public int calculateScore() {

        final int[] result = { 0 };
        addRollScore(result);
        nextFrame.addBonus(this, result);
        result[0] = result[0] + previousFrame.calculateScore();
        return result[0];
    }

    private void addRollScore(int[] result) {

        rolls.forEach(roll -> result[0] = result[0] + roll.calculateScore());
    }

    @Override
    protected void addBonus(Frame frame, int[] result) {

        if (frame.isSpare()) {
            addFirstRoll(result);
        }
    }

    @Override
    public boolean isLastFrame(int maxFrames) {

        return number == maxFrames;
    }

    private void addFirstRoll(int[] result) {

        result[0] = result[0] + rolls.get(0).calculateScore();
    }

    boolean isSpare() {

        return rolls.size() == 2 && getRollScore() == 10;
    }

    private int getRollScore() {

        int[] rollScore = new int[1];
        addRollScore(rollScore);
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
