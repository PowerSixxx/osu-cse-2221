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
public final class Hw17 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Hw17() {
    }

    /**
     * Reverses ("flips") {@code this}.
     *
     * @param <T>
     *            The type of elements in the queue
     * @param queue
     *            The queue to reverse
     * @updates queue
     * @ensures queue = rev(#queue)
     */
    public static <T> void flip(Queue<T> queue) {
        if (queue.length() > 0) {
            T front = queue.dequeue();
            flip(queue);
            queue.enqueue(front);
        }
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
        Queue<Integer> emptyQueue = new Queue1L<>();
        Queue<Character> singleElementQueue = new Queue1L<>();
        singleElementQueue.enqueue('X');

        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        queue.enqueue("D");

        out.println("Original Queue: " + queue);
        flip(queue);
        out.println("Flipped Queue: " + queue);

        out.println();

        out.println("Original Empty Queue: " + emptyQueue);
        flip(queue);
        out.println("Flipped Empty Queue: " + emptyQueue);

        out.println();

        out.println("Original Single Queue: " + singleElementQueue);
        flip(queue);
        out.println("Flipped Single Queue: " + singleElementQueue);

        out.close();
    }

}
