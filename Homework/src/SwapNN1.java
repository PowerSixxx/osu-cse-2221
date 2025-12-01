import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
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
public final class SwapNN1 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private SwapNN1() {
    }

    /**
     * Swaps the two given {@code NaturalNumber}s.
     *
     * @param n1
     *            the first {@code NaturalNumber}
     * @param n2
     *            the second {@code NaturalNumber}
     * @param out
     *            out stream
     * @updates n1
     * @updates n2
     * @ensures n1 = #n2 and n2 = #n1
     */
    private static void swapNN1(NaturalNumber n1, NaturalNumber n2, SimpleWriter out) {
        NaturalNumber temp = n1.newInstance();
        temp.copyFrom(n1);
        n1.copyFrom(n2);
        n2.copyFrom(temp);
        out.println("After n1: " + n1);
        out.println("After n2: " + n2);
        out.println();
    }

    private static void swapNN2(NaturalNumber n1, NaturalNumber n2, SimpleWriter out) {
        NaturalNumber temp = n1.newInstance();
        temp.transferFrom(n1);
        n1.transferFrom(n2);
        n2.transferFrom(temp);
        out.println("After n1: " + n1);
        out.println("After n2: " + n2);
        out.println();
    }

    /**
     * Squares a given {@code NaturalNumber}.
     *
     * @param n
     *            the number to square
     * @updates n
     * @ensures n = #n * #n
     */
    private static void square(NaturalNumber n, SimpleWriter out) {
        NaturalNumber temp = n.newInstance();
        temp.copyFrom(n);
        n.multiply(temp);
        out.println("After n square: " + n);
        out.println();
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
         * Put your main program code here; it may call myMethod as shown
         */
        NaturalNumber n1 = new NaturalNumber2(1);
        NaturalNumber n2 = new NaturalNumber2(2);
        NaturalNumber n = new NaturalNumber2(4);
        out.println("Before n1: " + n1);
        out.println("Before n2: " + n2);
        swapNN1(n1, n2, out);

        out.println("Before n1: " + n1);
        out.println("Before n2: " + n2);
        swapNN2(n1, n2, out);

        out.println("Before n: " + n);
        square(n, out);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
