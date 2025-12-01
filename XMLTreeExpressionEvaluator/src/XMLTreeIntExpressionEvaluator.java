import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.Reporter;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Baowen Liu
 *
 */
public final class XMLTreeIntExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeIntExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static int evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";
        // Initialize result
        int result = 0;

        // If the node is not a number, it should be an operator node
        if (!exp.label().equals("number")) {
            // Recursively evaluate the left and right children
            int leftValue = evaluate(exp.child(0));
            int rightValue = evaluate(exp.child(1));

            // Perform arithmetic operation
            if (exp.label().equals("plus")) {
                result = leftValue + rightValue;
            } else if (exp.label().equals("minus")) {
                result = leftValue - rightValue;
            } else if (exp.label().equals("times")) {
                result = leftValue * rightValue;
            } else if (exp.label().equals("divide")) {
                if (rightValue == 0) {
                    // Prevent divide by 0 and let the client know
                    Reporter.fatalErrorToConsole("You cannot divide by 0");
                }
                result = leftValue / rightValue;
            }
        } else {
            // If the node is a number, then convert String to Int
            result = Integer.parseInt(exp.attributeValue("value"));
        }

        return result;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        // Create input and output streams
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        // Prompt the user to enter the name of an XML file
        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        // Stop when empty string encountered
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            // Print the result
            out.println(evaluate(exp.child(0)));
            // Prompt for another file
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        // Close input and output streams
        in.close();
        out.close();
    }

}
