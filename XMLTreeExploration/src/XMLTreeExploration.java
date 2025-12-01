import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class XMLTreeExploration {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private XMLTreeExploration() {
    }

    /**
     * Output information about the middle child of the given {@code XMLTree}.
     *
     * @param xt
     *            the {@code XMLTree} whose middle child is to be printed
     * @param out
     *            the output stream
     * @updates out.content
     * @requires [the label of the root of xt is a tag] and [xt has at least one
     *           child] and out.is_open
     * @ensures out.content = #out.content * [the label of the middle child of
     *          xt, whether the root of the middle child is a tag or text, and
     *          if it is a tag, the number of children of the middle child]
     */
    private static void printMiddleNode(XMLTree xt, SimpleWriter out) {
        int middleIndex = xt.numberOfChildren() / 2;
        XMLTree middleChild = xt.child(middleIndex);
        out.println("Middle child label: " + middleChild.label());
        if (middleChild.isTag()) {
            out.println("Middle child's label is Tag.");
            out.println("The number of children is: " + middleChild.numberOfChildren());
        } else {
            out.println("Middle child's label is text.");
        }
    }

    /**
     * Output all attributes names and values for the root of the given
     * {@code XMLTree}.
     *
     * @param xt
     *            the {@code XMLTree} whose root's attributes are to be printed
     * @param out
     *            the output stream
     * @updates out.content
     * @requires [the label of the root of xt is a tag] and out.is_open
     * @ensures out.content = #out.content * [the name and value of each
     *          attribute of the root of xt]
     */
    private static void printRootAttributes(XMLTree xt, SimpleWriter out) {
        for (String x : xt.attributeNames()) {
            out.println(x);
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
         * Put your main program code here
         */
        /*
         * Close input and output streams
         */
        XMLTree xml = new XMLTree1("https://cse22x1.engineering.osu.edu/2221/web-sw1/"
                + "extras/instructions/xmltree-model/columbus-weather.xml");
        out.println(xml.toString());
        xml.display();

        if (xml.isTag()) {
            out.println("The root node is a tag.");
        } else {
            out.println("The root node is a text.");
        }
        out.println("The label of the root node is: " + xml.label());

        XMLTree results = xml.child(0);
        XMLTree channel = results.child(0);
        out.println("Number of children of the 'chennel' node: "
                + channel.numberOfChildren());
        XMLTree title = channel.child(1);
        XMLTree titleText = title.child(0);
        out.println("The label of the 'titleText' is: " + titleText.label());
        out.println("The label of the 'titleText' is(One Statement): "
                + xml.child(0).child(0).child(1).child(0).label());

        XMLTree astronomy = channel.child(10);
        out.println("The root of astronomy has an attribute named 'sunset': "
                + astronomy.hasAttribute("sunset"));
        out.println("The root of astronomy has an attribute named 'midday': "
                + astronomy.hasAttribute("midday"));
        if (astronomy.hasAttribute("sunrise")) {
            out.println("Sunrise: " + astronomy.attributeValue("sunrise"));
        }
        if (astronomy.hasAttribute("sunset")) {
            out.println("Sunset: " + astronomy.attributeValue("sunset"));
        }
        printMiddleNode(channel, out);
        //
        printRootAttributes(xml, out);
        in.close();
        out.close();
    }

}
