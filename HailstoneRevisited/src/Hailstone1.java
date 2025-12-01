import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
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
     * {@code NaturalNumber}.
     *
     * @param n
     *            the starting natural number
     * @param out
     *            the output stream
     * @updates out.content
     * @requires n > 0 and out.is_open
     * @ensures out.content = #out.content * [the Hailstone series starting with
     *          n]
     */
    private static void generateSeries(NaturalNumber n, SimpleWriter out) {
        NaturalNumber zero = new NaturalNumber1L(0);
        NaturalNumber one = new NaturalNumber1L(1);
        NaturalNumber two = new NaturalNumber1L(2);
        NaturalNumber three = new NaturalNumber1L(3);
        NaturalNumber max = new NaturalNumber1L(0);
        out.print(n + " ");
        while (n.compareTo(one) != 0) {// n = 9
            NaturalNumber remainder = n.divide(two); //n=4,  remainder = 1
            n.multiply(two); // n=8
            n.add(remainder); // n=9
            max.copyFrom(n);
            if (remainder.isZero()) {
                //It is even
                max.divide(two);
            } else {
                //Not even

            }
            out.print(n + " ");
        }
    } //n1.copyFrom(n2)

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
        out.println("Enter a start number: ");
        int num = in.nextInteger();

        if (num > 0) {
            NaturalNumber n = new NaturalNumber1L(num);
            generateSeries(n, out);
            out.println("Ending n: " + n);
        } else {
            out.println("The start number should greater than 0");
        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
