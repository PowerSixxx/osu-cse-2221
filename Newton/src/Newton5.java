import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * The program can calculate the k-th root of x.
 *
 * @author Baowen Liu
 *
 */
public final class Newton5 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Newton5() {
    }

    /**
     * Computes estimate of square root of x to within relative error the user
     * input.
     *
     * @param x
     *            positive number to compute k-th root of
     * @param epsilon
     *            positive epsilon to calculate the k-th root of the number
     * @param k
     *            positive k to calculate k-th root
     * @return estimate of square root
     */
    private static double sqrt(double x, double epsilon, int k) {
        if (x == 0) {
            return x; // to give a correct result when user input 0
        }
        double r = x; // initial guess
        while (Math.abs(Math.pow(r, k) - x) / x > epsilon) {
            r = ((k - 1) * r + x / Math.pow(r, k - 1)) / k;
        }
        return r; // Return the result to main method
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.println("Please enter a positive epsilon(tolerance level): ");
        double epsilon = in.nextDouble();

        while (true) { // when it satisfy the condition and start the loop
            out.print("Please enter a positive number");
            out.println("(negative to quit.): ");
            double num = in.nextDouble();
            if (num < 0) {
                out.println("Negative number. Program exiting...");
                out.println("Successfully exited the program.");
                break;
            }
            out.println("Enter a k's value to calculate k-th root(k >= 2): ");
            int k = in.nextInteger();
            while (k < 2) {
                out.println(
                        "Invalid k. Please enter an integer greater than or equal to 2.");
                out.println("Enter a k's value to calculate k-th root(k >= 2): ");
                k = in.nextInteger();
            }
            out.println("...Calculation started, please wait...");
            double result = sqrt(num, epsilon, k);
            out.println(
                    "The square root of " + num + " is approximately " + result + ".");
            out.println();
        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }
}
