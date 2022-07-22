import java.util.ArrayList;
import java.util.List;

public class Frame extends AbstractFrame {
    private final int number;
    private final AbstractFrame previousFrame;
    private final List<Roll> rolls;
    private AbstractFrame nextFrame;

    public Frame(int number, AbstractFrame previousFrame) {

        this.number = number;
        this.previousFrame = previousFrame;
        this.nextFrame = new NullFrame();
        this.rolls = new ArrayList<>();
    }

    @Override
    public AbstractFrame addRoll(int hitPins) {

        if (rolls.size() == 2) {
            nextFrame = new Frame(number + 1, this);
            nextFrame.addRoll(hitPins);
            return nextFrame;
        }
        Roll roll = new Roll(number, rolls.size() + 1, hitPins);
        rolls.add(roll);
        return this;
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

    private void addFirstRoll(int[] result) {
        result[0] = result[0] + rolls.get(0).calculateScore();
    }

    private boolean isSpare() {

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
