package br.sergio.utils.math;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

public class MathUtilsTest {

    @Test
    @DisplayName("Method MathUtils.inverseSqrt() must return a result with tolerance of 1%" +
            " with respect to that produced by traditional 1 / Math.sqrt(value)")
    public void inverseSqrt() {
        double value = ThreadLocalRandom.current().nextDouble(2, Double.MAX_VALUE);
        double traditionalInverseSqrt = 1 / Math.sqrt(value);
        double fastInverseSqrt = MathUtils.inverseSqrt(value);

//        double diff = fastInverseSqrt - traditionalInverseSqrt;
//        double signum = diff == 0 ? 0 : diff / Math.abs(diff);
//        BigDecimal inaccuracy = BigDecimal.valueOf(signum == 0 ? 0 : signum
//                * (1 - fastInverseSqrt / traditionalInverseSqrt)).multiply(new BigDecimal("100"),
//                new MathContext(1, RoundingMode.HALF_UP));
//
//        System.out.println("Traditional inverse square root: " + traditionalInverseSqrt);
//        System.out.println("Fast inverse square root: " + fastInverseSqrt);
//        System.out.println("Difference: " + diff);
//        System.out.println("Inaccuracy: " + inaccuracy.toPlainString() + "%");

        assertEquals(traditionalInverseSqrt, fastInverseSqrt, 0.01); // Tolerance of 1%
    }


    @Test
    @DisplayName("Method MathUtils.inverseSqrt() is made to boost performance")
    public void speedInverseSqrt() {
        /*
         * Defining a very large amount of loops because it's only
         * for so much operations that this algorithm really makes
         * the significant difference in performance.
         */
        int loops = 100_000_000;
        double firstResult, secondResult = firstResult = 0;
        Instant start, end;

        start = Instant.now();
        for(int i = 1; i <= loops; i++) {
            firstResult += 1 / Math.sqrt(i);
        }
        end = Instant.now();

        Duration firstDuration = Duration.between(start, end);

        start = Instant.now();
        for(int i = 1; i <= loops; i++) {
            secondResult += MathUtils.inverseSqrt(i);
        }
        end = Instant.now();
        Duration secondDuration = Duration.between(start, end);

//        System.out.println("First duration: " + durationToBigDecimal(firstDuration).toPlainString() + " s");
//        System.out.println("Second duration: " + durationToBigDecimal(secondDuration).toPlainString() + " s");
//        System.out.println("First result: " + firstResult);
//        System.out.println("Second result: " + secondResult);
//        System.out.println("Difference: " + Math.abs(firstResult - secondResult));
//        System.out.println("Second was " + BigDecimal.ONE.subtract(divideDurations(secondDuration,
//                firstDuration)).multiply(new BigDecimal("100")).stripTrailingZeros()
//                .toPlainString() + "% faster than first");

        assertTrue(secondDuration.compareTo(firstDuration) < 0);
    }

//    private BigDecimal divideDurations(Duration dividend, Duration divisor) {
//        BigDecimal num = durationToBigDecimal(dividend);
//        BigDecimal denom = durationToBigDecimal(divisor);
//        return num.divide(denom, new MathContext(3, RoundingMode.HALF_UP));
//    }
//
//    private BigDecimal durationToBigDecimal(Duration duration) {
//        return BigDecimal.valueOf(duration.getSeconds()).add(BigDecimal
//                .valueOf(duration.getNano(), 9));
//    }



}
