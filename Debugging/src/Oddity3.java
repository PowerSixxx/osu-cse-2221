import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple HelloWorld program (clear of Checkstyle and SpotBugs warnings).
 *
 * @author Baowen Liu
 */
public final class Oddity3 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Oddity3() {
        // no code needed here
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
//    public static void main(String[] args) {
//        SimpleWriter out = new SimpleWriter1L();
//        double x = 456.0;
//        double y = 100.0 * 4.56;
//        if (x == y) {
//            out.println("equal");
//        } else {
//            out.println("not equal");
//        }
//        out.close();
//    }  Double Error!!!
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        double x = 456.0;
        double y = 100.0 * 4.56;
        double epsilon = 1E-10; // Small tolerance value
        if (Math.abs(x - y) < epsilon) {
            out.println("equal");
        } else {
            out.println("not equal");
        }
        out.close();
    }
}
