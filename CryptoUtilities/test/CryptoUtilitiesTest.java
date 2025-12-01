import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * @author Baowen Liu
 *
 */
public class CryptoUtilitiesTest {

    /*
     * Tests of reduceToGCD
     */

    // Small number 0: this is the lowest boundary
    @Test
    public void testReduceToGCD_0_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(0);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    // Tests 30 and 21 as routine cases
    @Test
    public void testReduceToGCD_30_21() {
        NaturalNumber n = new NaturalNumber2(30);
        NaturalNumber nExpected = new NaturalNumber2(3);
        NaturalNumber m = new NaturalNumber2(21);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    // This is a routine and challenge
    @Test
    public void testReduceToGCD_5_3() {
        NaturalNumber n = new NaturalNumber2(5);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber m = new NaturalNumber2(3);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    // Slightly larger number: This is a challenge
    @Test
    public void testReduceToGCD_40_7() {
        NaturalNumber n = new NaturalNumber2(40);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber m = new NaturalNumber2(7);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    // Large number: challenge and a boundary
    @Test
    public void testReduceToGCD_1024_256() {
        NaturalNumber n = new NaturalNumber2(1024);
        NaturalNumber nExpected = new NaturalNumber2(256);
        NaturalNumber m = new NaturalNumber2(256);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isEven
     */

    // Checks to see if the lowest number zero is even. This is a boundary case
    @Test
    public void testIsEven_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    // Checks to see if the lowest odd number is even. This is a boundary case
    @Test
    public void testIsEven_1() {
        NaturalNumber n = new NaturalNumber2(1);
        NaturalNumber nExpected = new NaturalNumber2(1);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    // Routine case
    @Test
    public void testIsEven_58() {
        NaturalNumber n = new NaturalNumber2(58);
        NaturalNumber nExpected = new NaturalNumber2(58);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    // Large number: Boundary case
    @Test
    public void testIsEven_98765() {
        NaturalNumber n = new NaturalNumber2(98765);
        NaturalNumber nExpected = new NaturalNumber2(98765);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    /*
     * Tests of powerMod
     */

    // Boundary case
    @Test
    public void testPowerMod_0_0_2() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber pExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(2);
        NaturalNumber mExpected = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    // Routine case
    @Test
    public void testPowerMod_17_18_19() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(18);
        NaturalNumber pExpected = new NaturalNumber2(18);
        NaturalNumber m = new NaturalNumber2(19);
        NaturalNumber mExpected = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    /*
     * Test of isWitnessToCompositeness
     */

    // Routine and boundary case
    @Test
    public void isWitnessToCompositeness_5_7() {
        NaturalNumber w = new NaturalNumber2(5);
        NaturalNumber wExpected = new NaturalNumber2(5);
        NaturalNumber n = new NaturalNumber2(7);
        NaturalNumber nExpected = new NaturalNumber2(7);
        boolean result = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(wExpected, w);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    // This is a routine case
    public void isWitnessToCompositeness_8_9() {
        NaturalNumber w = new NaturalNumber2(8);
        NaturalNumber wExpected = new NaturalNumber2(8);
        NaturalNumber n = new NaturalNumber2(9);
        NaturalNumber nExpected = new NaturalNumber2(9);
        boolean result = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(wExpected, w);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    // Two larger numbers are composite.
    // This is a Challenge case
    public void isWitnessToCompositeness_48_79() {
        NaturalNumber w = new NaturalNumber2(48);
        NaturalNumber wExpected = new NaturalNumber2(48);
        NaturalNumber n = new NaturalNumber2(79);
        NaturalNumber nExpected = new NaturalNumber2(79);
        boolean result = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(wExpected, w);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    /*
     * Test of isPrime1
     */

    // Small Prime: Boundary case
    @Test
    public void isPrime1_5_() {
        NaturalNumber w = new NaturalNumber2(5);
        NaturalNumber wExpected = new NaturalNumber2(5);
        boolean result = CryptoUtilities.isPrime1(w);
        assertEquals(wExpected, w);
        assertEquals(true, result);
    }

    // Routine case
    @Test
    public void isPrime1_8_() {
        NaturalNumber w = new NaturalNumber2(8);
        NaturalNumber wExpected = new NaturalNumber2(8);
        boolean result = CryptoUtilities.isPrime1(w);
        assertEquals(wExpected, w);
        assertEquals(false, result);
    }

    // Large number: Boundary and challenge case
    @Test
    public void isPrime1_2025_() {
        NaturalNumber w = new NaturalNumber2(2025);
        NaturalNumber wExpected = new NaturalNumber2(2025);
        boolean result = CryptoUtilities.isPrime1(w);
        assertEquals(wExpected, w);
        assertEquals(false, result);
    }

    /*
     * Tests of isPrime2
     */

    //  Small number: Boundary case
    @Test
    public void isPrime2_5_() {
        NaturalNumber w = new NaturalNumber2(5);
        NaturalNumber wExpected = new NaturalNumber2(5);
        boolean result = CryptoUtilities.isPrime2(w);
        assertEquals(wExpected, w);
        assertEquals(true, result);
    }

    // Routine case
    @Test
    public void isPrime2_8_() {
        NaturalNumber w = new NaturalNumber2(8);
        NaturalNumber wExpected = new NaturalNumber2(8);
        boolean result = CryptoUtilities.isPrime2(w);
        assertEquals(wExpected, w);
        assertEquals(false, result);
    }

    // Boundary and challenge case
    @Test
    public void isPrime2_2025_() {
        NaturalNumber w = new NaturalNumber2(2025);
        NaturalNumber wExpected = new NaturalNumber2(2025);
        boolean result = CryptoUtilities.isPrime2(w);
        assertEquals(wExpected, w);
        assertEquals(false, result);
    }

    // Large number: boundary and challenge case
    @Test
    public void isPrime2_20060116_() {
        NaturalNumber w = new NaturalNumber2(20060116);
        NaturalNumber wExpected = new NaturalNumber2(20060116);
        boolean result = CryptoUtilities.isPrime2(w);
        assertEquals(wExpected, w);
        assertEquals(false, result);
    }

    /*
     * Tests of generateNextLikelyPrime
     */

    // Small number: this is a boundary and a routine
    @Test
    public void generateNextLikelyPrime_7_() {
        NaturalNumber w = new NaturalNumber2(7);
        NaturalNumber wExpected = new NaturalNumber2(7);
        CryptoUtilities.generateNextLikelyPrime(w);
        assertEquals(wExpected, w);
    }

    // This is routine
    @Test
    public void generateNextLikelyPrime_20_() {
        NaturalNumber w = new NaturalNumber2(20);
        NaturalNumber wExpected = new NaturalNumber2(23);
        CryptoUtilities.generateNextLikelyPrime(w);
        assertEquals(wExpected, w);
    }

    // Slightly Larger Number: this is a routine case
    @Test
    public void generateNextLikelyPrime_180_() {
        NaturalNumber w = new NaturalNumber2(180);
        NaturalNumber wExpected = new NaturalNumber2(181);
        CryptoUtilities.generateNextLikelyPrime(w);
        assertEquals(wExpected, w);
    }

    // Large number: this is routine and challenge case
    @Test
    public void generateNextLikelyPrime_20060116_() {
        NaturalNumber w = new NaturalNumber2(20060116);
        NaturalNumber wExpected = new NaturalNumber2(20060147);
        CryptoUtilities.generateNextLikelyPrime(w);
        assertEquals(wExpected, w);
    }

}
