package guava;

import com.google.common.base.Preconditions;

public class PreconditionsTester {
    public static void main(String args[]) {
        PreconditionsTester guavaTester = new PreconditionsTester();
        try {
            System.out.println(guavaTester.sqrt(-3.0));
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage() + e.getClass().getName());
        }
        try {
            System.out.println(guavaTester.sum(null, 3));
        } catch (NullPointerException e) {
            System.err.println(e.getMessage() + e.getClass().getName());
        }
        try {
            System.out.println(guavaTester.getValue(6));
        } catch (IndexOutOfBoundsException e) {
            System.err.println(e.getMessage() + e.getClass().getName());
        }
    }

    public double sqrt(double input) throws IllegalArgumentException {
        Preconditions.checkArgument(input > 0.0,
                "Illegal Argument passed: Negative value %s.", input);
        return Math.sqrt(input);
    }

    public int sum(Integer a, Integer b) {
        a = Preconditions.checkNotNull(a,
                "Illegal Argument passed: First parameter is Null.");
        b = Preconditions.checkNotNull(b,
                "Illegal Argument passed: Second parameter is Null.");
        return a + b;
    }

    public int getValue(int input) {
        int[] data = {1, 2, 3, 4, 5};
        Preconditions.checkElementIndex(input, data.length,
                "Illegal Argument passed: Invalid index.");
        return 0;
    }
}
