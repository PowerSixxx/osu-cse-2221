import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

public class TestingToStringWithCommasTest {

    /**
     * Calls the method under test.
     *
     * @param n
     *            the number to pass to the method under test
     * @return the {@code String} returned by the method under test
     * @ensures <pre>
     * redirectToMethodUnderTest = [String returned by the method under test]
     * </pre>
     */
    private static String redirectToMethodUnderTest(NaturalNumber n) {
        return NNtoStringWithCommas5.toStringWithCommas(n);
    }

    @Test
    public void testZero() {
        NaturalNumber n = new NaturalNumber2(0);
        String result = redirectToMethodUnderTest(n);
        assertEquals("0", result);
    }

    /**
     * Test case for single-digit number.
     */
    @Test
    public void testSingleDigit() {
        NaturalNumber n = new NaturalNumber2(1);
        String result = redirectToMethodUnderTest(n);
        assertEquals("1", result);
    }

    /**
     * Test case for a two-digit number.
     */
    @Test
    public void testTwoDigits() {
        NaturalNumber n = new NaturalNumber2(10);
        String result = redirectToMethodUnderTest(n);
        assertEquals("10", result);
    }

    /**
     * Test case for a three-digit number.
     */
    @Test
    public void testThreeDigits() {
        NaturalNumber n = new NaturalNumber2(100);
        String result = redirectToMethodUnderTest(n);
        assertEquals("100", result);
    }

    /**
     * Test case for a four-digit number (should include comma).
     */
    @Test
    public void testFourDigits() {
        NaturalNumber n = new NaturalNumber2(1000);
        String result = redirectToMethodUnderTest(n);
        assertEquals("1,000", result);
    }

    /**
     * Test case for a six-digit number.
     */
    @Test
    public void testSixDigits() {
        NaturalNumber n = new NaturalNumber2(123456);
        String result = redirectToMethodUnderTest(n);
        assertEquals("123,456", result);
    }

    /**
     * Test case for a seven-digit number.
     */
    @Test
    public void testSevenDigits() {
        NaturalNumber n = new NaturalNumber2(1000000);
        String result = redirectToMethodUnderTest(n);
        assertEquals("1,000,000", result);
    }

}
