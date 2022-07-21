import java.util.ArrayList;
import java.util.List;

public class Frame implements ScoreKeeper, ScoreCalculator {

    private final int number;
    private List<Roll> rolls;

    public Frame(int number) {

        this.number = number;
        this.rolls = new ArrayList<>();
    }

    public Frame addRoll(int hitPins) {

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
        rolls.forEach(roll -> result[0] = result[0] + roll.calculateScore());
        return result[0];
    }

}
