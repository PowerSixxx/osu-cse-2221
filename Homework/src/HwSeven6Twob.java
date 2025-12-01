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
public final class HwSeven6Twob {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private HwSeven6Twob() {
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
        int[] a = { 1, 2, 3, 4, 5, 4, 3, 2, 1, 0 };
        int maximum = a[0];
        int minimum = a[0];
        int i = 0;
        while (i < a.length) {
            if (a[i] < minimum) {
                minimum = a[i];
            } else {
                maximum = a[i];
            }
            i++;
        }
        out.println("maximum: " + maximum);
        out.println("minimum: " + minimum);
        /*
         * Put your main program code here
         */
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
