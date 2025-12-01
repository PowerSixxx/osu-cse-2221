import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
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
public final class XMLTreeNNExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeNNExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires * [exp is a subtree of a well-formed XML arithmetic expression]
     *           and [the label of the root of exp is not "expression"]
     *
     * @ensures evaluate = [the value of the expression]
     */
    private static NaturalNumber evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";

        // Create a NaturalNumber instance to store the result
        NaturalNumber result = new NaturalNumber2();
        // If the node is not a number, it should be an operator node
        if (!exp.label().equals("number")) {
            // Recursively evaluate the left and right operands
            NaturalNumber leftValue = new NaturalNumber2(evaluate(exp.child(0)));
            NaturalNumber rightValue = new NaturalNumber2(evaluate(exp.child(1)));
            // Copy left operand to result
            result.copyFrom(leftValue);

            // Perform arithmetic operation
            if (exp.label().equals("plus")) {
                result.add(rightValue); // Addition
            } else if (exp.label().equals("minus")) { // Subtraction
                // Ensure non-negative result since NaturalNumber should be positive
                if (leftValue.compareTo(rightValue) >= 0) {
                    result.subtract(rightValue);
                } else {
                    // If the result is negative, then output the following argument
                    Reporter.fatalErrorToConsole("Natural Number cannot less than zero.");
                }
            } else if (exp.label().equals("times")) {
                result.multiply(rightValue); // Multiplication
            } else if (exp.label().equals("divide")) {
                // Prevent divide by 0
                if (rightValue.isZero()) {
                    Reporter.fatalErrorToConsole("You caanot divide by 0");
                }
                result.divide(rightValue); // Division
            }
        } else {
            // If the node is a number, parse its value from the "value" attribute
            result = new NaturalNumber2((exp.attributeValue("value")));
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
