import components.map.Map;
import components.map.Map1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple pizza order manager: inputs orders from a file and computes and
 * displays the total price for each order.
 *
 * @author Baowen Liu
 *
 */
public final class PizzaOrderManager {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private PizzaOrderManager() {
    }

    /**
     * Inputs a "menu" of words (items) and their prices from the given file and
     * stores them in the given {@code Map}.
     *
     * @param fileName
     *            the name of the input file
     * @param priceMap
     *            the word -> price map
     * @replaces priceMap
     * @requires <pre>
     * [file named fileName exists but is not open, and has the
     *  format of one "word" (unique in the file) and one price (in cents)
     *  per line, with word and price separated by ','; the "word" may
     *  contain whitespace but not ',']
     * </pre>
     * @ensures [priceMap contains word -> price mapping from file fileName]
     */
    private static void getPriceMap(String fileName, Map<String, Integer> priceMap) {
        assert fileName != null : "Violation of: fileName is not null";
        assert priceMap != null : "Violation of: priceMap is not null";

        // Create a SimpleReader to read from the file
        SimpleReader file = new SimpleReader1L(fileName);

        // Stop until file reach to end
        while (!file.atEOS()) {
            // Read the next line from the file
            String str = file.nextLine();
            // Initialize two strings:
            // 'first' would hold the items name
            // 'second' would hold the price string
            String first = "";
            String second = "";

            // Loop through each character to find the comma that
            // separates the item and price
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == ',') {
                    // Extract the item name
                    first = str.substring(0, i);
                    // Extract the price string
                    second = str.substring(i + 1);
                }
            }
            // Convert the price string to an integer
            int price = Integer.parseInt(second);
            // Add the item and price to the Map
            priceMap.add(first, price);
        }
        // Close the SimpleReader stream
        file.close();
    }

    /**
     * Input one pizza order and compute and return the total price.
     *
     * @param input
     *            the input stream
     * @param sizePriceMap
     *            the size -> price map
     * @param toppingPriceMap
     *            the topping -> price map
     * @return the total price (in cents)
     * @updates input
     * @requires <pre>
     * input.is_open and
     * [input.content begins with a pizza order consisting of a size
     *  (something defined in sizePriceMap) on the first line, followed
     *  by zero or more toppings (something defined in toppingPriceMap)
     *  each on a separate line, followed by an empty line]
     * </pre>
     * @ensures <pre>
     * input.is_open and
     * #input.content = [one pizza order (as described
     *              in the requires clause)] * input.content and
     * getOneOrder = [total price (in cents) of that pizza order]
     * </pre>
     */
    private static int getOneOrder(SimpleReader input, Map<String, Integer> sizePriceMap,
            Map<String, Integer> toppingPriceMap) {
        assert input != null : "Violation of: input is not null";
        assert sizePriceMap != null : "Violation of: sizePriceMap is not null";
        assert toppingPriceMap != null : "Violation of: toppingPriceMap is not null";
        assert input.isOpen() : "Violation of: input.is_open";

        // Initialize the total price
        int totalPrice = 0;

        // Read the first line: the pizza size
        String size = input.nextLine();

        // Check if the size exists in the map
        // If it is, add its price to the total
        if (sizePriceMap.hasKey(size)) {
            totalPrice += sizePriceMap.value(size);
        }

        // Read the next line: topping or empty
        String topping = input.nextLine();

        // Continue reading toppings until an empty line is reached
        while (topping.length() > 0) {
            // If the topping exists in the map, add its price to the total
            if (toppingPriceMap.hasKey(topping)) {
                totalPrice += toppingPriceMap.value(topping);
            }
            // Read the next topping line
            topping = input.nextLine();
        }
        // Return the computed total price
        return totalPrice;
    }

    /**
     * Output the given price formatted in dollars and cents.
     *
     * @param output
     *            the output stream
     * @param price
     *            the price to output
     * @updates output
     * @requires output.is_open = true and 0 <= price
     * @ensures <pre>
     * output.is_open and
     * output.content = #output.content *
     *  [display of price, where price is in cents but
     *   display is formatted in dollars and cents]
     * </pre>
     */
    private static void putPrice(SimpleWriter output, int price) {
        assert output != null : "Violation of: output is not null";
        assert output.isOpen() : "Violation of: output.is_open";
        assert 0 <= price : "Violation of: 0 <= price";

        // Convert cents to dollars and cents
        int cents = price % 100;
        int dollars = price / 100;

        // Format and print as $dollars.cents (with 2 digits for cents)
        output.print("$" + dollars + ".");
        if (cents < 10) {
            output.print("0");
        }
        output.println(cents);
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L("data/orders.txt");
        SimpleWriter out = new SimpleWriter1L();
        Map<String, Integer> sizeMenu = new Map1L<String, Integer>();
        Map<String, Integer> toppingMenu = new Map1L<String, Integer>();
        int orderNumber = 1;
        /*
         * Get menus of sizes with prices and toppings with prices
         */
        getPriceMap("data/sizes.txt", sizeMenu);
        getPriceMap("data/toppings.txt", toppingMenu);
        /*
         * Output heading for report of pizza orders
         */
        out.println();
        out.println("Order");
        out.println("Number Price");
        out.println("------ ------");
        /*
         * Process orders, one at a time, from input file
         */
        while (!in.atEOS()) {
            int price = getOneOrder(in, sizeMenu, toppingMenu);
            out.print(orderNumber + "      ");
            putPrice(out, price);
            out.println();
            orderNumber++;
        }
        out.println();
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
