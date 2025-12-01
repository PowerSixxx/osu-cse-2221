import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple HelloWorld program (clear of Checkstyle and SpotBugs warnings).
 *
 * @author P. Bucci
 */
public final class FirstClass {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private FirstClass() {
        // no code needed here
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        int a = 5;
        int b, c = a, d = 10 / 2;
        out.println(a);
        out.println(c);
        out.println(d);
        out.close();
    }

}
