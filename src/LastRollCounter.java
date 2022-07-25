import java.util.ArrayList;

public class LastRollCounter extends AbstractRollCounter {

    private boolean isSpare = false;

    public LastRollCounter() {

        super(new ArrayList<>());
    }

    @Override
    protected boolean isSpare() {

        boolean result = rolls.size() == maxRolls && getRollScore() == highScore;
        if (result) {
            isSpare = true;
        }
        return isSpare;
    }

    @Override
    protected int getMaxRolls() {

        int result = maxRolls;
        if (isSpare()) {
            result = maxRolls + spareBonusRoll;
        }
        return result;
    }
}
