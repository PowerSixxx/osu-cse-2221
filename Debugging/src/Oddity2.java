import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple HelloWorld program (clear of Checkstyle and SpotBugs warnings).
 *
 * @author Baowen Liu
 */
public final class Oddity2 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Oddity2() {
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
//        final int microsPerDay = 24 * 60 * 60 * 1000 * 1000;
//        final int millisPerDay = 24 * 60 * 60 * 1000;
//        out.println(microsPerDay / millisPerDay);
//        out.close();
//    } Range is too large!!!
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        final long microsPerDay = 24L * 60 * 60 * 1000 * 1000;
        final long millisPerDay = 24L * 60 * 60 * 1000;
        out.println(microsPerDay / millisPerDay);
        out.close();
    }
}
