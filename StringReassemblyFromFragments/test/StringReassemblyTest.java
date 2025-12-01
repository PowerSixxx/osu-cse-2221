import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * @author Baowen Liu
 *
 */
public class StringReassemblyTest {

    /*
     * Tests of overlap
     */

    // This is the lowest boundary
    @Test
    public void testOverlap_1() {
        String str1 = "theohiostate";
        String str2 = "stateuniversity";
        int result = 5;
        int testRes = StringReassembly.overlap(str1, str2);
        assertEquals(result, testRes);
    }

    // Test a larger number, this is a routine challenge test
    @Test
    public void testOverlap_2() {
        String str1 = "theboyisharrypotter";
        String str2 = "harrypottertheboywhoneverdie";
        int result = 11;
        int testRes = StringReassembly.overlap(str1, str2);
        assertEquals(result, testRes);
    }

    /**
     * Tests of combination.
     */

    // Test for the return value of 2 from the overlap
    @Test
    public void testCombination_1() {
        String str1 = "Mango";
        String str2 = "gotothepark";
        int overlap = 2;
        String result = StringReassembly.combination(str1, str2, overlap);
        assertEquals("Mangotothepark", result);
    }

    // Test for the return value of 5 from the overlap
    @Test
    public void testCombination_2() {
        String str1 = "removewords";
        String str2 = "wordsshouldberemoved";
        int overlap = 5;
        String result = StringReassembly.combination(str1, str2, overlap);
        assertEquals("removewordsshouldberemoved", result);
    }

    // Test to see if it works for one letter which makes
    // the test a routine boundary
    @Test
    public void testCombination_3() {
        String str1 = "oneletter";
        String str2 = "rtest";
        int overlap = 1;
        String result = StringReassembly.combination(str1, str2, overlap);
        assertEquals("onelettertest", result);
    }

    // Test to see if it works for totally overlap words
    @Test
    public void testCombination_4() {
        String str1 = "totaloverlap";
        String str2 = "totaloverlap";
        int overlap = 12;
        String result = StringReassembly.combination(str1, str2, overlap);
        assertEquals("totaloverlap", result);
    }

    /*
     * Tests of addToSetAvoidingSubstrings
     */

    // This is a routine test
    @Test
    public void testaddToSetAvoidingSubstrings_1() {
        Set<String> strSet = new Set1L<String>();
        strSet.add("hello");
        strSet.add("he");
        strSet.add("sunny");

        String str = "hello world";

        Set<String> expected = new Set1L<String>();
        expected.add("sunny");
        expected.add("hello world");

        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        assertEquals(expected, strSet);
    }

    // Boundary test: add to an empty set
    @Test
    public void testaddToSetAvoidingSubstrings_2() {
        Set<String> strSet = new Set1L<String>();

        String str = "hello";

        Set<String> expected = new Set1L<String>();
        expected.add("hello");

        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        assertEquals(expected, strSet);
    }

    // Challenge test: Add a string that is a superstring of
    // multiple elements in the set, which should be removed.
    @Test
    public void testaddToSetAvoidingSubstrings_3() {
        Set<String> strSet = new Set1L<String>();
        strSet.add("ga");
        strSet.add("game");
        strSet.add("g");

        String str = "gamer";

        Set<String> expected = new Set1L<String>();
        expected.add("gamer");

        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        assertEquals(expected, strSet);
    }

    /*
     * Test of printWithLineSeparators
     */

    @Test
    // Challenging and routine test: with multiple '~'
    // Check to make sure all of the ~ are replaced by line separator
    public void testprintWithLineSeparators_1() {
        SimpleWriter out = new SimpleWriter1L();
        String text = "Ohio ~ State ~ University";

        StringReassembly.printWithLineSeparators(text, out);

        out.close();
    }

    @Test
    // Boundary and routine test with only one '~'
    public void testprintWithLineSeparators_2() {
        SimpleWriter out = new SimpleWriter1L();
        String text = "hello ~ world";

        StringReassembly.printWithLineSeparators(text, out);

        out.close();
    }

    /*
     * Test of linesFromInput
     */

    @Test
    // Tests linesFromInput with a file containing substrings.
    // Verifies only maximal strings are returned.
    public void testlinesFromInput_1() {
        Set<String> expected = new Set1L<>();
        expected.add("Bucks -- Beat");
        expected.add("Go Bucks");
        expected.add("o Bucks -- B");
        expected.add("Beat Mich");
        expected.add("Michigan~");

        SimpleReader input = new SimpleReader1L("data/cheer-8-2.txt");
        Set<String> strSet = StringReassembly.linesFromInput(input);

        input.close();
        assertEquals(expected, strSet);
    }

}
