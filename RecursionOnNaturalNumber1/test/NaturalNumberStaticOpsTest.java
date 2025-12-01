import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

public class NaturalNumberStaticOpsTest {

    /**
     * Test case for when the input is 0.
     */
    @Test
    public void testZero() {
        NaturalNumber n = new NaturalNumber2(0);
        assertEquals("0", NaturalNumberStaticOps.toStringWithCommas(n));
    }

    /**
     * Test case for single-digit number.
     */
    @Test
    public void testSingleDigit() {
        NaturalNumber n = new NaturalNumber2(1);
        assertEquals("1", NaturalNumberStaticOps.toStringWithCommas(n));
    }

    /**
     * Test case for a two-digit number.
     */
    @Test
    public void testTwoDigits() {
        NaturalNumber n = new NaturalNumber2(10);
        assertEquals("10", NaturalNumberStaticOps.toStringWithCommas(n));
    }

    /**
     * Test case for a three-digit number.
     */
    @Test
    public void testThreeDigits() {
        NaturalNumber n = new NaturalNumber2(100);
        assertEquals("100", NaturalNumberStaticOps.toStringWithCommas(n));
    }

    /**
     * Test case for a four-digit number (should include comma).
     */
    @Test
    public void testFourDigits() {
        NaturalNumber n = new NaturalNumber2(1000);
        assertEquals("1,000", NaturalNumberStaticOps.toStringWithCommas(n));
    }

    /**
     * Test case for a six-digit number.
     */
    @Test
    public void testSixDigits() {
        NaturalNumber n = new NaturalNumber2(123456);
        assertEquals("123,456", NaturalNumberStaticOps.toStringWithCommas(n));
    }

    /**
     * Test case for a seven-digit number.
     */
    @Test
    public void testSevenDigits() {
        NaturalNumber n = new NaturalNumber2(1000000);
        assertEquals("1,000,000", NaturalNumberStaticOps.toStringWithCommas(n));
    }
}
