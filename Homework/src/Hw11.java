import components.queue.Queue;
import components.queue.Queue1L;
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
public final class Hw11 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Hw11() {
    }

    /**
     * Returns {@code n} to the power {@code p}.
     *
     * @param n
     *            the number to which we want to apply the power
     * @param p
     *            the power
     * @return the number to the power
     * @requires Integer.MIN_VALUE <= n ^ (p) <= Integer.MAX_VALUE and p >= 0
     * @ensures power = n ^ (p)
     */
    private static int power(int n, int p) {
        int a = 1;
        for (int i = 0; i < p; i++) {
            a = a * n;
        }
        return a;
    }

    /**
     * Returns the {@code r}-th root of {@code n}.
     *
     * @param n
     *            the number to which we want to apply the root
     * @param r
     *            the root
     * @return the root of the number
     * @requires n >= 0 and r > 0
     * @ensures root ^ (r) <= n < (root + 1) ^ (r)
     */
    private static int root(int n, int r) {
        int lowEnough = 0;
        int tooHigh = n + 1;
        while (lowEnough + 1 < tooHigh) {
            int mid = (lowEnough + tooHigh) / 2;
            int powerMid = power(mid, r);

            if (powerMid > n) {
                tooHigh = mid;
            } else {
                lowEnough = mid;
            }

        }
        return lowEnough;
    }

    /**
     * @update q
     * @ensures q = rev(#q)
     */
    public static void reverse(Queue<Integer> q) { // 1 2 3 4 5
        if (q.length() > 1) { // true
            int front = q.dequeue(); // front = 1
            reverse(q); // q = 2 3 4 5
            q.enqueue(front);
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
         * Put your main program code here; it may call myMethod as shown
         */
        int n = 11, r = 2, p = 3;
        int low = root(n, r);
        out.println(low);
        int pow = power(n, p);
        out.println(pow);
        Queue<Integer> q = new Queue1L<>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);
        // Before this line, q is now {1 2 3 4 5}
        reverse(q);
        out.println(q);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
