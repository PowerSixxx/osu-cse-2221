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
public final class CoinChange1 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private CoinChange1() {
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
        final int centsToDollar = 100, centsToHalfDollar = 50, centsToQuarter = 25;
        final int centsToDime = 10, centsToNickel = 5;
        int dollar = n / centsToDollar;
        n = n % centsToDollar;
        int halfDollar = n / centsToHalfDollar;
        n = n % centsToHalfDollar;
        int quarter = n / centsToQuarter;
        n = n % centsToQuarter;
        int dime = n / centsToDime;
        n = n % centsToDime;
        int nickel = n / centsToNickel;
        int penny = n % centsToNickel;
        out.println("The numbers of coins of dollar back is " + dollar);
        out.println("The numbers of coins of half-dollar back is " + halfDollar);
        out.println("The numbers of coins of quarter back is " + quarter);
        out.println("The numbers of coins of dime back is " + dime);
        out.println("The numbers of coins of nickel back is " + nickel);
        out.println("The numbers of coins of penny back is " + penny);
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
