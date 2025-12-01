import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Compute the square roots of specific value and it also can change the
 * different epsilon(tolerance level)to get the result.
 *
 * @author Baowen Liu
 *
 */
public final class Newton3 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Newton3() {
    }

    /**
     * Computes estimate of square root of x to within relative error the user
     * input.
     *
     * @param x
     *            positive number to compute square root of
     * @param epsilon
     *            positive epsilon to calculate the square root of the number
     * @return estimate of square root
     */
    private static double sqrt(double x, double epsilon) {
        if (x == 0) {
            return x; // to give a correct result when user input 0
        }
        double r = x; // initial guess
        while (Math.abs(Math.pow(r, 2) - x) / x > epsilon) {
            r = (r + x / r) / 2;
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
        boolean continueCal = true; // Use boolean to define when to continue
        while (continueCal) { // when it satisfy the condition and start the loop
            out.print("Do you want to calculate a number's square root?");
            out.print("(y to start, anything else to quit.)");
            String ans = in.nextLine();
            if (ans.equals("y")) {
                out.println("Please enter a positive number: ");
                double num = in.nextDouble();
                double result = sqrt(num, epsilon);
                out.println("The square root of " + num + " is approximately " + result
                        + ".");
                out.println();
            } else {
                continueCal = false;
            }
        }
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
