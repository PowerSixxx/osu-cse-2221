import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Put a short phrase describing the program here.
 *
 * @author Baowen Liu
 *
 */
public final class Hw13 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Hw13() {
    }

    /**
     * Returns the product of the digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to multiply
     * @return the product of the digits of {@code n}
     * @clears n
     * @ensures productOfDigits1 = [product of the digits of n]
     */
    private static NaturalNumber productOfDigits1(NaturalNumber n) {
        if (n.isZero()) {
            return new NaturalNumber2(1);
        }

        int lastDigit = n.divideBy10();
        NaturalNumber result = productOfDigits1(n);

        if (lastDigit == 0) {
            return new NaturalNumber2(0);
        } else {
            result.multiply(new NaturalNumber2(lastDigit));
        }

        return result;
    }

    /**
     * Returns the product of the digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to multiply
     * @return the product of the digits of {@code n}
     * @ensures productOfDigits2 = [product of the digits of n]
     */
    private static NaturalNumber productOfDigits2(NaturalNumber n) {
        NaturalNumber copy = new NaturalNumber2(n);
        return productOfDigits1(copy);
    }

    /**
     * Reports the value of {@code n} as an {@code int}, when {@code n} is small
     * enough.
     *
     * @param n
     *            the given {@code NaturalNumber}
     * @return the value
     * @requires n <= Integer.MAX_VALUE
     * @ensures toInt = n
     */
    private static int toInt(NaturalNumber n) {
        if (n.isZero()) {
            return 0;
        }
        int lastDigit = n.divideBy10();
        int result = toInt(n) * 10 + lastDigit;
        n.multiply(new NaturalNumber2(lastDigit));
        return result;
    }
//        if (n.compareTo(new NaturalNumber2(Integer.MAX_VALUE)) > 0) {
//            return Integer.MAX_VALUE;
//        }
//        return n.toInt();

    /**
     * Reports whether the given tag appears in the given {@code XMLTree}.
     *
     * @param xml
     *            the {@code XMLTree}
     * @param tag
     *            the tag name
     * @return true if the given tag appears in the given {@code XMLTree}, false
     *         otherwise
     * @ensures <pre>
     * findTag =
     *    [true if the given tag appears in the given {@code XMLTree}, false otherwise]
     * </pre>
     */
    private static boolean findTag(XMLTree xml, String tag) {
        boolean result = false;
        if (xml.label().equals(tag)) {
            result = true;
        }
        for (int i = 0; i < xml.numberOfChildren(); i++) {
            if (xml.isTag() && findTag(xml.child(i), tag)) {
                result = true;
            }
        }
        return result;
    }

//    NaturalNumber[] array = new NaturalNumber[5];
//    NaturalNumber count = new NaturalNumber2(1);
//    for (int i = 0; i < array.length; i++) {
//        array[i] = new NaturalNumber2(count);
//        count.increment();
//    }

    public static int count1s(NaturalNumber n) {
        int count = 0;
        int lastDigit = n.divideBy10();
        if (lastDigit == 1) {
            count = 1;
        }
        if (!n.isZero()) {
            count += count1s(n);
        }
        n.multiplyBy10(lastDigit);
        return count;
    }

    public static String reversedString(String s) {
        if (s.length() == 0) {
            return s;
        }
        String rev = s.substring(1);
        String result = reversedString(rev) + s.charAt(0);
        return result;
    }

    /**
     * True if the number contains a 5.
     *
     * @ensures contains5 = [true if contains(x,5), false otherwise]
     *
     */
    public static boolean contains5(NaturalNumber x) {
        boolean containsFive = false;
        if (!x.isZero()) {
            int lastDigit = x.divideBy10();
            if (lastDigit == 5) {
                containsFive = true;
            }
            containsFive = containsFive || contains5(x);
            x.multiplyBy10(lastDigit);
        }
        return containsFive;
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
        NaturalNumber n = new NaturalNumber2(234);
        out.println("Test 1: ");
        out.println("Before: n is: " + n);
        out.println("Result is: " + productOfDigits1(n));
        out.println("After: n is: " + n);
        out.println();

        NaturalNumber m = new NaturalNumber2(321);
        out.println("Test 2: ");
        out.println("Before: m is: " + m);
        out.println("Result is: " + productOfDigits2(m));
        out.println("After: m is: " + m);
        out.println();

        NaturalNumber p = new NaturalNumber2(432);
        out.println("Test 3: ");
        out.println("NaturalNumber p is: " + p);
        out.println("After execute int value is: " + toInt(p));
        out.println();

        String s = "abcde";
        out.println(reversedString(s));
        out.println();

        NaturalNumber x = new NaturalNumber2(5);
        out.println("contains 5: " + contains5(x));

        String url = "https://cse22x1.engineering.osu.edu/2221/web-sw1/extras/instructions/xmltree-model/columbus-weather.xml";
        XMLTree urlXML = new XMLTree1(url);
        out.println("Test 4: ");
        out.println();
        for (int i = 0; i < 3; i++) {
            out.println("---------");
        }
        out.println();
        out.println("<height>: " + findTag(urlXML, "height"));
        out.println("<width>: " + findTag(urlXML, "width"));
        out.println("<item>: " + findTag(urlXML, "item"));

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }
}
