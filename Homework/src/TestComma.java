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
public final class TestComma {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private TestComma() {
    }

    public static void printComma(SimpleReader in, SimpleWriter out, String str) {
        if (str.length() > 0) {
            for (int i = 0; i < str.length(); i++) {
                out.print(str.charAt(i));
                if (i < str.length() - 1) {
                    out.print(",");
                }
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
         * Put your main program code here; it may call myMethod as shown
         */
        out.println("Adding comma test: ");
        String str = in.nextLine();
        boolean testContinue = true;
        while (testContinue) {
            printComma(in, out, str);
            str = in.nextLine();
            if (str.length() == 0) {
                testContinue = false;
            }
        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
