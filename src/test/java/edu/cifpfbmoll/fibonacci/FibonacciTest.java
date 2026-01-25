package edu.cifpfbmoll.fibonacci;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class FibonacciTest {

    private Fibonacci fibonacci = new Fibonacci();

    @Test
    void fibonacciOfZeroIsZero() {
        assertEquals(0, fibonacci.calculate(0));
    }

    @Test
    void fibonacciOfOneIsOne() {
        assertEquals(1, fibonacci.calculate(1));
    }

    @Test
    void fibonacciOfTwoIsOne() {
        assertEquals(1, fibonacci.calculate(2));
    }

    @Test
    void fibonacciOfThreeIsTwo() {
        assertEquals(2, fibonacci.calculate(3));
    }

    @Test
    void fibonacciOfFourIsThree() {
        assertEquals(3, fibonacci.calculate(4));
    }

    @Test
    void fibonacciOfFiveIsFive() {
        assertEquals(5, fibonacci.calculate(5));
    }

    @Test
    void fibonacciOfSixIsEight() {
        assertEquals(8, fibonacci.calculate(6));
    }

    @Test
    void fibonacciOfSevenIsThirteen() {
        assertEquals(13, fibonacci.calculate(7));
    }

    @Test
    void fibonacciOfEightIsTwentyOne() {
        assertEquals(21, fibonacci.calculate(8));
    }

    @Test
    void fibonacciNegativeNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            fibonacci.calculate(-1);
        });
    }

    @Test
    void fibonacciResultIsAlwaysNonNegative() {
        int result = fibonacci.calculate(10);
        assertTrue(result >= 0);
    }
}
