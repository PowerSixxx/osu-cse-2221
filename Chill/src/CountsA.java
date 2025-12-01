import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.queue.Queue;
import components.queue.Queue1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple HelloWorld program (clear of Checkstyle and SpotBugs warnings).
 *
 * @author Baowen
 */
public final class CountsA {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private CountsA() {
        // no code needed here
    }

    /**
     * This method is to calculate how many a are there in the user's input
     *
     * @param String
     *            ans
     * @param SimpleWriter
     *            out
     * @requires in.open()
     * @ensures Reflect the amount of a in the user's input
     */
    public static void countsA(String ans, SimpleWriter out) {
        int count = 0;
        if (ans.length() >= 0) {
            for (int i = 0; i < ans.length(); i++) {
                if (ans.charAt(i) == 'a') {
                    count++;
                }
            }
        }
        out.println("There are " + count + " a in the user's input.");
    }

    /**
     * Convert Natural Number to Integer format
     *
     * @param NatualNumber
     *            n
     *
     */
    public static int toInt(NaturalNumber n) {
        int result = 0;
        int lastDigit = 0;
        if (!n.isZero()) { // Base case is when n = 0
            lastDigit = n.divideBy10(); // let n consistently close to base case 0
            result = toInt(n) * 10 + lastDigit;
        }
        n.multiplyBy10(lastDigit); // Restore n
        return result;
    }
    /*
     * You can also think a case for this method, for example, the user input
     * 121 1. n = 121 lastDigit = 1, n = 12 result = toInt(12) * 10 + 1 return
     * result
     *
     * 2. n = 12 lastDigit = 2. n = 1 result = toInt(1) * 10 + 2 return result
     *
     * 3. n = 1 lastDigit = 1, n = 0 result = toInt(0) * 10 + 1 return result
     *
     * 4. n = 0 return result 0
     *
     * 4 -> 3 result = 1 3 -> 2 result = 1 * 10 + 2 = 12 2 -> 1 result = 12 * 10
     * + 1 = 121 return result 121
     */

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        SimpleReader in = new SimpleReader1L();
        String ans = "";
        do {
            out.println("Please input whatever you want: ");
            ans = in.nextLine();
            countsA(ans, out);
            out.println();
        } while (!ans.equals("N"));
        out.println("Test 2: Convert Natural Number to Int format");
        out.println("Please enter an integer: ");
        int a = in.nextInteger();
        NaturalNumber n = new NaturalNumber2(a);
        int r = toInt(n);
        out.println(r);
        Queue q = new Queue1L();
    }

}
