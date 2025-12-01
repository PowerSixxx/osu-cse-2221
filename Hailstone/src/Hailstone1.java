import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Baowen Liu
 *
 */
public final class Hailstone1 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Hailstone1() {
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
        out.println("Series: ");
        while (a != 1) {
            if (a % 2 == 0) {
                a /= 2;
                out.print(a + " ");
            } else {
                a = a * triple + 1;
                out.print(a + " ");
            }
        }
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
        out.println("Enter an positive integer: ");
        int i = in.nextInteger();
        generateSeries(i, out);
        /*
         * Close input and output and scanner streams
         */
        in.close();
        out.close();
    }

}
