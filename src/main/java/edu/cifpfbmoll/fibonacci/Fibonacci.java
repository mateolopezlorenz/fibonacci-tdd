package edu.cifpfbmoll.fibonacci;

public class Fibonacci {

    public int calculate(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input must be a non-negative integer.");
        } if (n <= 1) {
            return n;
        }

        int previo = 0;
        int actual = 1;

        for (int i = 2; i <= n; i++) {
            int siguiente = previo + actual;
            previo = actual;
            actual = siguiente;
        }

        return actual;
    }
}
