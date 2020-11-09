package io.github.therealmone.fireres.core.utils;

import lombok.extern.slf4j.Slf4j;
import java.util.Random;

@Slf4j
public class RandomUtils {

    private static final Random RANDOM = new Random();

    public static Integer generateValueInInterval(Integer lowerBound, Integer upperBound) {
        if (lowerBound.equals(upperBound)) {
            return lowerBound;
        }

        return RANDOM.nextInt(upperBound + 1 - lowerBound) + lowerBound;
    }

    public static boolean rollDice(Double chance) {
        return RANDOM.nextDouble() <= chance;
    }

    public static Double generateValueInInterval(Double min, Double max) {
        return (min + (Math.random() * (max - min)));
    }
}
