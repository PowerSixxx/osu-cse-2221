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
public final class MidtermOne3 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private MidtermOne3() {
    }

    /**
     * hasDuplicates method.
     *
     * @param numbers[]
     *            the int array
     * @ensures hasDuplicates = true if at least two entries in numbers are
     *          equal, false otherwise
     * @return isEqual
     *
     */
    public static boolean hasDuplicates(int[] numbers) {
        boolean isEqual = false;
        for (int i = 0; i < numbers.length - 1 && !isEqual; i++) {
            for (int j = i + 1; j < numbers.length && !isEqual; j++) {
                if (numbers[i] == numbers[j]) {
                    isEqual = true;
                }
            }
        }
        return isEqual;
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
        int[] numbers = { 1, 2, 3, 4, 5, 2, 1 };
        out.println("The array has at least two entries are equal: "
                + hasDuplicates(numbers));
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
