import java.util.ArrayList;
import java.util.List;

public class Frame {

    private final int number;
    private List<Roll> rolls;

    public Frame(int number) {

        this.number = number;
        this.rolls = new ArrayList<>();
    }

    public Frame addRoll(int hitPins) {

        rolls.add(new Roll(hitPins));
        return this;
    }

    public int getScore() {

        final int[] result = { 0 };
        rolls.forEach(roll -> result[0] = result[0] + roll.getScore());
        return result[0];
    }

    public Integer getNumber() {

        return number;
    }
}
