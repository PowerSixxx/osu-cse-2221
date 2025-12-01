import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
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
public final class Hw12 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Hw12() {
    }

    /**
     * Returns the number of digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to count
     * @return the number of digits of {@code n}
     * @ensures numberOfDigits = [number of digits of n]
     */
    private static int numberOfDigits(NaturalNumber n) {
        int count = 0; // n = 150
        int digit = n.divideBy10(); // n =15
        count++; // count =1
        if (!n.isZero()) {
            count += numberOfDigits(n);
        } // count = 1+ 2
        n.multiplyBy10(digit);
        return count;
    }

    /**
     * Returns the sum of the digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to add
     * @return the sum of the digits of {@code n}
     * @ensures sumOfDigits = [sum of the digits of n]
     */
    private static int sumOfDigits(NaturalNumber n) {
        if (n.isZero()) {
            return 0;
        }
        int lastDigit = n.divideBy10();
        int sum = lastDigit + sumOfDigits(n);
        n.multiplyBy10(lastDigit);
        return sum;
    }

    /**
     * Returns the sum of the digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to add
     * @return the sum of the digits of {@code n}
     * @ensures sumOfDigits = [sum of the digits of n]
     */
    private static NaturalNumber sumOfDigitsNatural(NaturalNumber n) {
        if (n.isZero()) {
            return new NaturalNumber2(0);
        }
        int lastDigit = n.divideBy10();
        NaturalNumber copy = new NaturalNumber2(n);
        NaturalNumber sum = sumOfDigitsNatural(copy);
        sum.add(new NaturalNumber2(lastDigit));
        n.multiplyBy10(lastDigit);
        return sum;
    }

    /**
     * Divides {@code n} by 2.
     *
     * @param n
     *            {@code NaturalNumber} to be divided
     * @updates n
     * @ensures 2 * n <= #n < 2 * (n + 1)
     */
    private static void divideBy2(NaturalNumber n) {
        int remaindar = n.divideBy10();
        if (remaindar != 1) {
            remaindar /= 2;
            if (!n.isZero()) {
                //n = 1 and remaindar = 4
                divideBy2(n);
                //remainder = 4 n = 5
            }

            int temp = n.divideBy10();
            n.multiplyBy10(temp); //restore n
            if (temp == 5) {
                n.divideBy10();
                n.multiplyBy10(remaindar + 5);
            } else {
                n.multiplyBy10(remaindar);
            }

        } else {
            n.multiplyBy10(5);
        }
    }

    /**
     * Checks whether a {@code String} is a palindrome.
     *
     * @param s
     *            {@code String} to be checked
     * @return true if {@code s} is a palindrome, false otherwise
     * @ensures isPalindrome = (s = rev(s))
     */
    private static boolean isPalindrome(String s) {
        if (s.length() <= 1) {
            return true;
        }
        if (s.charAt(0) != s.charAt(s.length() - 1)) {
            return false;
        }
        return isPalindrome(s.substring(1, s.length() - 1));
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
        NaturalNumber n = new NaturalNumber2(185);
        out.println(numberOfDigits(n));
        out.println(n);
        divideBy2(n);
        out.println(n);
        out.println(sumOfDigits(n));
        out.println(sumOfDigitsNatural(n));
        String s = "abcca";
        out.println(isPalindrome(s));
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
