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
public final class ReverseString {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private ReverseString() {
    }

    private static String reversedString(String s) {
        if (s.length() == 0) {
            return s;
        } else {
            String sub = s.substring(1);
            String revSub = reversedString(sub);
            String result = revSub + s.charAt(0);
            return result;
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
        out.println("Enter a string that you want to reverse: ");
        String str = in.nextLine();
        String revStr = reversedString(str);
        out.println(revStr);
    }

}
