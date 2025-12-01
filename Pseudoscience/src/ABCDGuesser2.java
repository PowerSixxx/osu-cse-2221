import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Put a short phrase describing the program here.
 *
 * @author Baowen Liu
 *
 */
public final class ABCDGuesser2 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private ABCDGuesser2() {
    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        // Use boolean to create a loop and get user's input until the number is positive
        boolean positive = false;
        double n = -1;
        while (!positive) {
            out.println("Please enter a positive value: ");
            // The input can be anything so we read it as a String
            String nStr = in.nextLine();
            // Use FormatChecker.canParseDouble method
            // to check if the input is real number
            if (FormatChecker.canParseDouble(nStr)) {
                n = Double.parseDouble(nStr);
                if (n > 0) { // Check the input's sign(+ or -)
                    positive = true; // If positive, then quit the loop
                } else {
                    out.println("Error: Number must be positive.");
                }
            } else {
                out.println("Error: Please enter a valid number.");
            }
        }
        return n; // Return mu
    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in, SimpleWriter out) {
        // Use boolean to make a loop until user's input satisfied criteria
        // namely, positive and not equal to 1
        boolean personalNum = false;
        double wxyz = -1;
        // Continue run the while loop until satisfying the criteria
        while (!personalNum) {
            out.println(
                    "Please enter a positive real number has personal meaning to you: ");
            String personalNumStr = in.nextLine(); // Read user's input as a String
            // Check whether the input is a real number
            if (FormatChecker.canParseDouble(personalNumStr)) {
                wxyz = Double.parseDouble(personalNumStr);
                if (wxyz > 0 && wxyz != 1) {
                    personalNum = true;
                } else {
                    out.println("Error: Invalid number (Not accecpt negative and 1).");
                }
            } else {
                out.println("Error: Invalid. Please enter a number!");
            }

        }
        return wxyz; // Return wxyz values
    }

    /**
     * Finds the best approximation of the target constant using the de Jager
     * formula.
     *
     * @param mu
     *            The target constant to approximate.
     * @param w
     *            User's first chosen number.
     * @param x
     *            User's second chosen number.
     * @param y
     *            User's third chosen number.
     * @param z
     *            User's fourth chosen number.
     * @return An array containing the best exponents {a, b, c, d}, the best
     *         approximation, and the relative error.
     */
    private static double[] findBestApprx(double mu, double w, double x, double y,
            double z) {
        // Series of abcd
        final double[] abcd = { -5, -4, -3, -2, -1, -1.0 / 2, -1.0 / 3, -1.0 / 4, 0,
                1.0 / 4, 1.0 / 3, 1.0 / 2, 1, 2, 3, 4, 5 };
        // Initialize abcd
        double a = abcd[0], b = abcd[0], c = abcd[0], d = abcd[0];

        // Initialize a best mu appx that close to real mu
        double bestMuAppx = Math.pow(w, a) * Math.pow(x, b) * Math.pow(y, c)
                * Math.pow(z, d); // abcd exponents are -5
        // Initialize the bestA, bestB, bestC, bestD and assign them
        double bestA = a, bestB = b, bestC = c, bestD = d;
        double minError = Math.abs((bestMuAppx - mu) / mu);
        // Use for loop four times so that read all of the exponents possibilities
        // Find the best abcd, mu, error
        final int length = abcd.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                for (int k = 0; k < length; k++) {
                    for (int m = 0; m < length; m++) {
                        a = abcd[i];
                        b = abcd[j];
                        c = abcd[k];
                        d = abcd[m];
                        // Calculate the appx mu
                        double muAppx = Math.pow(w, a) * Math.pow(x, b) * Math.pow(y, c)
                                * Math.pow(z, d);
                        // Evaluate current error
                        double error = Math.abs((muAppx - mu) / mu);
                        if (error <= minError) {
                            // Update data(error, a, b, c, d, muAppx)
                            minError = error;
                            bestA = a;
                            bestB = b;
                            bestC = c;
                            bestD = d;
                            bestMuAppx = muAppx;
                        }
                    }
                }
            }
        }
        // Return an array(ans)
        return new double[] { bestA, bestB, bestC, bestD, bestMuAppx, minError };
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

        // Use getPositiveDouble method to prompt user input a mu
        double mu = getPositiveDouble(in, out);
        // Create a double array and let the size to be 4
        double[] fourPersonalNum = new double[4];
        // Use while loop in the main method to get four personal number
        for (int i = 0; i < fourPersonalNum.length; i++) { // Read all 4 numbers
            // Use getPositiveDoubleNotOne method to get four values
            fourPersonalNum[i] = getPositiveDoubleNotOne(in, out);
        }
        // According to array, and assign wxyz
        double w = fourPersonalNum[0];
        double x = fourPersonalNum[1];
        double y = fourPersonalNum[2];
        double z = fourPersonalNum[3];
        // Now, we got mu wxyz.
        // Use findBestApprx Method to find best exponents, appx and relative error
        double[] ans = findBestApprx(mu, w, x, y, z); // Create an ans array

        // Calculate in the format percentage(multiply 100)
        double resultError = ans[5] * 100.0;

        // Output the results
        out.println("Best Exponents: a = " + ans[0] + ", b = " + ans[1] + ", c = "
                + ans[2] + ", d = " + ans[3]);
        out.println("The best approximation is: " + ans[4]);
        out.println("Relative Error: " + resultError + "%");
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
