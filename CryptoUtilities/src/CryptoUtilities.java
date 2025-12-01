import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.random.Random;
import components.random.Random1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Utilities that could be used with RSA cryptosystems.
 *
 * @author Baowen Liu
 *
 */
public final class CryptoUtilities {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CryptoUtilities() {
    }

    /**
     * Useful constant, not a magic number: 3.
     */
    private static final int THREE = 3;

    /**
     * Pseudo-random number generator.
     */
    private static final Random GENERATOR = new Random1L();

    /**
     * Returns a random number uniformly distributed in the interval [0, n].
     *
     * @param n
     *            top end of interval
     * @return random number in interval
     * @requires n > 0
     * @ensures <pre>
     * randomNumber = [a random number uniformly distributed in [0, n]]
     * </pre>
     */
    public static NaturalNumber randomNumber(NaturalNumber n) {
        assert !n.isZero() : "Violation of: n > 0";
        final int base = 10;
        NaturalNumber result;
        int d = n.divideBy10();
        if (n.isZero()) {
            /*
             * Incoming n has only one digit and it is d, so generate a random
             * number uniformly distributed in [0, d]
             */
            int x = (int) ((d + 1) * GENERATOR.nextDouble());
            result = new NaturalNumber2(x);
            n.multiplyBy10(d);
        } else {
            /*
             * Incoming n has more than one digit, so generate a random number
             * (NaturalNumber) uniformly distributed in [0, n], and another
             * (int) uniformly distributed in [0, 9] (i.e., a random digit)
             */
            result = randomNumber(n);
            int lastDigit = (int) (base * GENERATOR.nextDouble());
            result.multiplyBy10(lastDigit);
            n.multiplyBy10(d);
            if (result.compareTo(n) > 0) {
                /*
                 * In this case, we need to try again because generated number
                 * is greater than n; the recursive call's argument is not
                 * "smaller" than the incoming value of n, but this recursive
                 * call has no more than a 90% chance of being made (and for
                 * large n, far less than that), so the probability of
                 * termination is 1
                 */
                result = randomNumber(n);
            }
        }
        return result;
    }

    /**
     * Finds the greatest common divisor of n and m.
     *
     * @param n
     *            one number
     * @param m
     *            the other number
     * @updates n
     * @clears m
     * @ensures n = [greatest common divisor of #n and #m]
     */
    public static void reduceToGCD(NaturalNumber n, NaturalNumber m) {

        /*
         * Use Euclid's algorithm; in pseudocode: if m = 0 then GCD(n, m) = n
         * else GCD(n, m) = GCD(m, n mod m)
         */

        // Check if m is zero, and if m is, then GCD has been found
        if (!m.isZero()) {
            // Sets r to the remainder of n/m
            NaturalNumber r = n.divide(m);
            // The code will keep dividing until the m is equal to zero
            reduceToGCD(m, r);
            // Copies n from m, and m is the GCD
            n.copyFrom(m);
            // Clears m
            m.clear();
        }
    }

    /**
     * Reports whether n is even.
     *
     * @param n
     *            the number to be checked
     * @return true iff n is even
     * @ensures isEven = (n mod 2 = 0)
     */
    public static boolean isEven(NaturalNumber n) {
        // set even is false
        boolean even = false;
        // extract the last digit of n(int)
        int lastDigit = n.divideBy10();
        // restore n
        n.multiplyBy10(lastDigit);
        // if last digit is even
        if (lastDigit % 2 == 0) {
            // then even is true
            even = true;
        }
        // return a boolean
        return even;
    }

    /**
     * Updates n to its p-th power modulo m.
     *
     * @param n
     *            number to be raised to a power
     * @param p
     *            the power
     * @param m
     *            the modulus
     * @updates n
     * @requires m > 1
     * @ensures n = #n ^ (p) mod m
     */
    public static void powerMod(NaturalNumber n, NaturalNumber p, NaturalNumber m) {
        assert m.compareTo(new NaturalNumber2(1)) > 0 : "Violation of: m > 1";

        // sets the needed variables
        NaturalNumber one = new NaturalNumber2(1);
        NaturalNumber two = new NaturalNumber2(2);
        NaturalNumber power = new NaturalNumber2();
        NaturalNumber temp = new NaturalNumber2();
        NaturalNumber copy = new NaturalNumber2(n);

        // Base case: checks if the power is zero
        if (p.isZero()) {
            n.copyFrom(one); // return one(since n^0 = 1)
        } else if (p.compareTo(one) > 0) {
            // copies p and then divide by two
            power.copyFrom(p);
            power.divide(two);
            // Use recursive method to calculate and to reach the base case direction
            // compute n^(p/2)
            powerMod(n, power, m);
            // copies temp from n
            temp.copyFrom(n);
            // compute (n^(p/2))^2
            n.multiply(temp);
            // checks to see if power is odd
            if (!isEven(p)) {
                // compute (n^(p/2))^2 * n
                n.multiply(copy);
            }
        }
        // takes the modulo
        temp = n.divide(m);
        // copies that temp number back into n
        n.copyFrom(temp);
    }

    /**
     * Reports whether w is a "witness" that n is composite, in the sense that
     * either it is a square root of 1 (mod n), or it fails to satisfy the
     * criterion for primality from Fermat's theorem.
     *
     * @param w
     *            witness candidate
     * @param n
     *            number being checked
     * @return true iff w is a "witness" that n is composite
     * @requires n > 2 and 1 < w < n - 1
     * @ensures <pre>
     * isWitnessToCompositeness =
     *     (w ^ 2 mod n = 1)  or  (w ^ (n-1) mod n /= 1)
     * </pre>
     */
    public static boolean isWitnessToCompositeness(NaturalNumber w, NaturalNumber n) {
        assert n.compareTo(new NaturalNumber2(2)) > 0 : "Violation of: n > 2";
        assert (new NaturalNumber2(1)).compareTo(w) < 0 : "Violation of: 1 < w";
        n.decrement();
        assert w.compareTo(n) < 0 : "Violation of: w < n - 1";
        n.increment();

        // sets all of the needed variables
        NaturalNumber one = new NaturalNumber2(1);
        NaturalNumber two = new NaturalNumber2(2);
        NaturalNumber wSquaredModN = new NaturalNumber2();
        NaturalNumber wToPowerNMinus1ModN = new NaturalNumber2();
        NaturalNumber nMinus1 = new NaturalNumber2();

        // copies the parameters so the number can be changed without everything changing
        wSquaredModN.copyFrom(w);
        wToPowerNMinus1ModN.copyFrom(w);
        nMinus1.copyFrom(n);
        nMinus1.decrement();

        // compute w ^ 2 mod n
        powerMod(wSquaredModN, two, n);
        // compute w ^ (n-1) mod n
        powerMod(wToPowerNMinus1ModN, nMinus1, n);

        // A witness proves compositeness if:
        // (1) witness^2 mod number = 1, or
        // (2) witness^(number-1) mod number != 1
        return (wSquaredModN.equals(one)) || (!wToPowerNMinus1ModN.equals(one));
    }

    /**
     * Reports whether n is a prime; may be wrong with "low" probability.
     *
     * @param n
     *            number to be checked
     * @return true means n is very likely prime; false means n is definitely
     *         composite
     * @requires n > 1
     * @ensures <pre>
     * isPrime1 = [n is a prime number, with small probability of error
     *         if it is reported to be prime, and no chance of error if it is
     *         reported to be composite]
     * </pre>
     */
    public static boolean isPrime1(NaturalNumber n) {
        assert n.compareTo(new NaturalNumber2(1)) > 0 : "Violation of: n > 1";
        boolean isPrime;
        if (n.compareTo(new NaturalNumber2(THREE)) <= 0) {
            /*
             * 2 and 3 are primes
             */
            isPrime = true;
        } else if (isEven(n)) {
            /*
             * evens are composite
             */
            isPrime = false;
        } else {
            /*
             * odd n >= 5: simply check whether 2 is a witness that n is
             * composite (which works surprisingly well :-)
             */
            isPrime = !isWitnessToCompositeness(new NaturalNumber2(2), n);
        }
        return isPrime;
    }

    /**
     * Reports whether n is a prime; may be wrong with "low" probability.
     *
     * @param n
     *            number to be checked
     * @return true means n is very likely prime; false means n is definitely
     *         composite
     * @requires n > 1
     * @ensures <pre>
     * isPrime2 = [n is a prime number, with small probability of error
     *         if it is reported to be prime, and no chance of error if it is
     *         reported to be composite]
     * </pre>
     */
    public static boolean isPrime2(NaturalNumber n) {
        assert n.compareTo(new NaturalNumber2(1)) > 0 : "Violation of: n > 1";

        /*
         * Use the ability to generate random numbers (provided by the
         * randomNumber method above) to generate several witness candidates --
         * say, 10 to 50 candidates -- guessing that n is prime only if none of
         * these candidates is a witness to n being composite (based on fact #3
         * as described in the project description); use the code for isPrime1
         * as a guide for how to do this, and pay attention to the requires
         * clause of isWitnessToCompositeness
         */

        // Initialize the needed variables
        NaturalNumber two = new NaturalNumber2(2);
        NaturalNumber four = new NaturalNumber2(4);
        NaturalNumber upperLimit = new NaturalNumber2();
        NaturalNumber random = new NaturalNumber2();

        // Set the upper limit for random number generation (n - 4)
        upperLimit.copyFrom(n);
        upperLimit.subtract(four);

        // Number of random trials
        int count = 30;

        // Flag to track if the number is prime
        boolean isPrime = true;
        // Loops to check if a number is prime for 30 times
        for (int i = 0; i < count; i++) {
            // the random number generator starts at zero
            random = randomNumber(upperLimit);
            random.add(two);

            if (n.compareTo(new NaturalNumber2(THREE)) <= 0) {
                /*
                 * 2 and 3 are primes
                 */
                isPrime = true;
            } else if (isEven(n)) {
                /*
                 * evens are composite
                 */
                isPrime = false;
            } else {
                /*
                 * odd n >= 5: simply check whether 2 is a witness that n is
                 * composite (which works surprisingly well :-)
                 */
                //checks to see if the number is prime in comparison to a
                //random number
                isPrime = !isWitnessToCompositeness(random, n);
            }
        }
        // Return isPrime
        return isPrime;
    }

    /**
     * Generates a likely prime number at least as large as some given number.
     *
     * @param n
     *            minimum value of likely prime
     * @updates n
     * @requires n > 1
     * @ensures n >= #n and [n is very likely a prime number]
     */
    public static void generateNextLikelyPrime(NaturalNumber n) {
        assert n.compareTo(new NaturalNumber2(1)) > 0 : "Violation of: n > 1";

        /*
         * Use isPrime2 to check numbers, starting at n and increasing through
         * the odd numbers only (why?), until n is likely prime
         */

        // Initialize two to 2
        NaturalNumber two = new NaturalNumber2(2);
        // Check if the number is even, if it is, then add 1 to make it odd
        // since even number can never be to prime
        if (isEven(n)) {
            n.increment();
        }
        // Runs continue until n is prime
        while (!isPrime2(n)) {
            n.add(two);
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
         * Sanity check of randomNumber method -- just so everyone can see how
         * it might be "tested"
         */
        final int testValue = 17;
        final int testSamples = 100000;
        NaturalNumber test = new NaturalNumber2(testValue);
        int[] count = new int[testValue + 1];
        for (int i = 0; i < count.length; i++) {
            count[i] = 0;
        }
        for (int i = 0; i < testSamples; i++) {
            NaturalNumber rn = randomNumber(test);
            assert rn.compareTo(test) <= 0 : "Help!";
            count[rn.toInt()]++;
        }
        for (int i = 0; i < count.length; i++) {
            out.println("count[" + i + "] = " + count[i]);
        }
        out.println(
                "  expected value = " + (double) testSamples / (double) (testValue + 1));

        /*
         * Check user-supplied numbers for primality, and if a number is not
         * prime, find the next likely prime after it
         */
        while (true) {
            out.print("n = ");
            NaturalNumber n = new NaturalNumber2(in.nextLine());
            if (n.compareTo(new NaturalNumber2(2)) < 0) {
                out.println("Bye!");
                break;
            } else {
                if (isPrime1(n)) {
                    out.println(n + " is probably a prime number"
                            + " according to isPrime1.");
                } else {
                    out.println(n + " is a composite number" + " according to isPrime1.");
                }
                if (isPrime2(n)) {
                    out.println(n + " is probably a prime number"
                            + " according to isPrime2.");
                } else {
                    out.println(n + " is a composite number" + " according to isPrime2.");
                    generateNextLikelyPrime(n);
                    out.println("  next likely prime is " + n);
                }
            }
        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
