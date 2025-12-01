import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;

/**
 * @author Baowen Liu
 *
 */
public class GlossaryTest {

    /*
     * Test of getDef
     */
    @Test
    public void testGetDef() {
        String input = "Java\nA programming language.";
        SimpleReader in = new SimpleReader1L(input);
        Map<String, String> wordMap = new Map1L<>();
        Glossary.getDef(wordMap, in);
        assertEquals("A programming language.", wordMap.value("Java"));
    }

    /*
     * Test of definitionLink
     */
    @Test
    public void testDefinitionLinkWithTerm() {
        Map<String, String> wordMap = new Map1L<>();
        wordMap.add("Java", "Java is like Python.");
        wordMap.add("Python", "A language.");
        Queue<String> terms = new Queue1L<>();
        terms.enqueue("Java");
        terms.enqueue("Python");

        Glossary.definitionLink(wordMap, terms, "output");

        String expected = "<a href=\"Java.html\">Java</a> is like <a href=\"Python.html\">Python</a>.";
        assertEquals(expected, wordMap.value("Java"));
    }

}
