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
public final class Hailstone5 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Hailstone5() {
    }

    /**
     * Generates and outputs the Hailstone series starting with the given
     * integer.
     *
     * @param n
     *            the starting integer
     * @param out
     *            the output stream
     */
    private static void generateSeries(int n, SimpleWriter out) {
        int a = n;
        final int triple = 3;
        int count = 1;
        int max = a;
        out.println("Series: ");
        out.print(a + " ");
        while (a != 1) {
            if (a % 2 == 0) {
                a /= 2;
                out.print(a + " ");
                count++;
            } else {
                a = a * triple + 1;
                if (max < a) {
                    max = a;
                }
                out.print(a + " ");
                count++;
            }
        }
        out.println();
        out.println("The length of series is: " + count);
        out.println("The maximum of series is: " + max);
    }

    /**
     * Repeatedly asks the user for a positive integer until the user enters
     * one. Returns the positive integer.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive integer entered by the user
     */
    private static int getPositiveInteger(SimpleReader in, SimpleWriter out) {
        int number = -1;
        while (number <= 0) {
            out.println("Enter a positive integer: ");
            String input = in.nextLine();
            if (FormatChecker.canParseInt(input)) {
                number = Integer.parseInt(input);
                if (number <= 0) {
                    out.println("The number must be positive, please try again.");
                    out.println();
                }
            } else {
                out.println("Invalid input. Please enter a valid integer.");
                out.println();
            }
        }
        return number;
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
        /*
         * Put your main program code here; it may call generateSeries as shown
         */
        String ans = "y";
        while (ans.equals("y")) {
            int i = getPositiveInteger(in, out);
            generateSeries(i, out);
            out.print("Do you want to continue?");
            out.println("(answer y to continue, any other characters to quit.)");
            ans = in.nextLine();
        }
        /*
         * Close input and output and scanner streams
         */
        in.close();
        out.close();
    }

}
