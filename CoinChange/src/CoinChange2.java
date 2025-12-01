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
public final class CoinChange2 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private CoinChange2() {
    }

    /**
     * changeBack method.
     *
     * @param totalCents
     *            the user's input change
     * @param out
     *            a writer stream
     */
    public static void changeBack(int totalCents, SimpleWriter out) {
        int n = totalCents;
        int[] denominations = { 100, 50, 25, 10, 5, 1 };
        int[] count = new int[6];
        String[] letters = { "dollar", "half-dollar", "quarter", "dime", "nickel",
                "penny" };
        for (int i = 0; i < denominations.length; i++) {
            count[i] = n / denominations[i];
            n %= denominations[i];
            out.println("The numbers of coins of " + letters[i] + " back is " + count[i]);
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

        out.println("Enter a n amount(number of cents) to make change for: ");
        int totalCents = in.nextInteger();
        changeBack(totalCents, out);
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
