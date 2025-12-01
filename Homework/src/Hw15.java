import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Baowen Liu
 *
 */
public final class Hw15 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Hw15() {
    }

    /**
     * Reports the smallest integer in the given {@code Queue<Integer>}.
     *
     * @param q
     *            the queue of integer
     * @return the smallest integer in the given queue
     * @requires q /= empty_string
     * @ensures <pre>
     * min is in entries(q) and
     * for all x: integer
     *     where (x is in entries(q))
     *   (min <= x)
     * </pre>
     */
    private static int min(Queue<Integer> q) {
        int size = q.length();
        int min = q.dequeue();
        q.enqueue(min); // put it back

        for (int i = 1; i < size; i++) {
            int temp = q.dequeue();
            if (temp < min) {
                min = temp;
            }
            q.enqueue(temp);
        }

        // Restore original queue order
        for (int i = 0; i < size; i++) {
            q.enqueue(q.dequeue());
        }

        return min;
    }

    /**
     * Reports an array of two {@code int}s with the smallest and the largest
     * integer in the given {@code Queue<Integer>}.
     *
     * @param q
     *            the queue of integer
     * @return an array of two {@code int}s with the smallest and the largest
     *         integer in the given queue
     * @requires q /= empty_string
     * @ensures <pre>
     * { minAndMax[0], minAndMax[1] } is subset of entries(q) and
     * for all x: integer
     *     where (x in in entries(q))
     *   (minAndMax[0] <= x <= minAndMax[1])
     * </pre>
     */
    private static int[] minAndMax1(Queue<Integer> q) {
        int size = q.length();
        int min = q.dequeue();
        int max = min;
        q.enqueue(min);

        for (int i = 1; i < size; i++) {
            int x = q.dequeue();
            if (x < min) {
                min = x;
            }
            if (x > max) {
                max = x;
            }
            q.enqueue(x);
        }

        for (int i = 0; i < size; i++) {
            q.enqueue(q.dequeue());
        }

        return new int[] { min, max };
    }

    /**
     * Reports an array of two {@code int}s with the smallest and the largest
     * integer in the given {@code Queue<Integer>}.
     *
     * @param q
     *            the queue of integer
     * @return an array of two {@code int}s with the smallest and the largest
     *         integer in the given queue
     * @requires q /= empty_string
     * @ensures <pre>
     * { minAndMax[0], minAndMax[1] } is subset of entries(q) and
     * for all x: integer
     *     where (x in in entries(q))
     *   (minAndMax[0] <= x <= minAndMax[1])
     * </pre>
     */
    private static int[] minAndMax2(Queue<Integer> q) {
        int size = q.length();
        int min, max;

        if (size % 2 != 0) {
            int x = q.dequeue();
            min = max = x;
            q.enqueue(x);
            size--;
        } else {
            int a = q.dequeue();
            int b = q.dequeue();
            if (a < b) {
                min = a;
                max = b;
            } else {
                min = b;
                max = a;
            }
            q.enqueue(a);
            q.enqueue(b);
            size -= 2;
        }

        for (int i = 0; i < size / 2; i++) {
            int a = q.dequeue();
            int b = q.dequeue();

            if (a < b) {
                if (a < min) {
                    min = a;
                }
                if (b > max) {
                    max = b;
                }
            } else {
                if (b < min) {
                    min = b;
                }
                if (a > max) {
                    max = a;
                }
            }

            q.enqueue(a);
            q.enqueue(b);
        }

        int n = q.length();
        for (int i = 0; i < n; i++) {
            q.enqueue(q.dequeue());
        }

        return new int[] { min, max };
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
         * Put your main program code here; it may call myMethod as shown
         */

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }
}
