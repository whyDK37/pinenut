package log4j2;


import java.util.Random;

public class RandomNumberGenerator {

    private final Random random;

    public RandomNumberGenerator() {
        random = new Random();
    }

    public int nextNumberBetween(int min, int max) {
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        return random.nextInt((max - min) + 1) + min;
    }

}