import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.junit.jupiter.api.Test;
 import org.junit.jupiter.api.Assertions;

public class Main {

    public static void main(String[] args) {


    }

    @Test
    void open() {

        System.out.println();

        Consumer<Integer> scoreGame = (score) -> System.out.printf("game total score: %s%n", score);
        BiConsumer<Integer, Integer> scoreframe = (numberOfFrame, score) -> System.out.printf("%sth frame score: %s%n", numberOfFrame, score);

        BowlingGameStarter bow = new BowlingGameStarter();
        bow.init(scoreGame, scoreframe);

        bow.roll(1);
        assertEquals(1, bow.getTotalScore());

        bow.roll(4);
        assertEquals(1, bow.getTotalScore());

    }

}
