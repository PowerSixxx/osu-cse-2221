import java.util.Comparator;

import components.queue.Queue;
import components.queue.Queue1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Baowen Liu
 *
 */
public final class Hw18 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Hw18() {
    }

    /**
     * Removes and returns the minimum value from {@code q} according to the
     * ordering provided by the {@code compare} method from {@code order}.
     *
     * @param q
     *            the queue
     * @param order
     *            ordering by which to compare entries
     * @return the minimum value from {@code q}
     * @updates q
     * @requires <pre>
     * q /= empty_string  and
     *  [the relation computed by order.compare is a total preorder]
     * </pre>
     * @ensures <pre>
     * perms(q * <removeMin>, #q)  and
     *  for all x: string of character
     *      where (x is in entries (q))
     *    ([relation computed by order.compare method](removeMin, x))
     * </pre>
     */
    private static String removeMin(Queue<String> q, Comparator<String> order) {
        Queue<String> temp = new Queue1L<>();
        String min = null;

        // First while loop: find the minimum
        while (q.length() > 0) {
            String current = q.dequeue();
            if (min == null || order.compare(current, min) < 0) {
                // Update the minimum
                min = current;
            }
            // Copy Queue to temp
            temp.enqueue(current);
        }

        boolean removed = false;
        // Second while loop: Remove the minimum value
        while (temp.length() > 0) {
            String current = temp.dequeue();
            if (!removed && order.compare(current, min) == 0) {
                removed = true;
            } else {
                // Update the current queue
                q.enqueue(current);
            }
        }
        // Return minimum value
        return min;
    }

    /**
     * Sorts {@code q} according to the ordering provided by the {@code compare}
     * method from {@code order}.
     *
     * @param q
     *            the queue
     * @param order
     *            ordering by which to sort
     * @updates q
     * @requires [the relation computed by order.compare is a total preorder]
     * @ensures q = [#q ordered by the relation computed by order.compare]
     */
    public static void sort(Queue<String> q, Comparator<String> order) {
        // Create a temporary queue called sorted
        Queue<String> sorted = new Queue1L<>();
        // Continuing until the original queue is empty
        while (q.length() > 0) {
            String min = removeMin(q, order);
            sorted.enqueue(min);
        }
        // Copy sorted to original queue
        q.transferFrom(sorted);
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        Queue<String> queue = new Queue1L<>();
        queue.enqueue("apple");
        queue.enqueue("banana");
        queue.enqueue("cherry");
        out.println("Original Queue: " + queue);
        Comparator<String> lexOrder = String::compareTo;
        String min = removeMin(queue, lexOrder);
        out.println("Removed Min: " + min);
        out.println("Queue After removeMin: " + queue);

        queue.enqueue("apple");
        sort(queue, lexOrder);
        out.println("Sorted Queue: " + queue);

        out.close();
    }

}
