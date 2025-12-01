import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.stack.Stack;
import components.stack.Stack1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Baowen Liu
 *
 */
public final class Hw20 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Hw20() {
    }

    /**
     * Reverses ("flips") {@code this}.
     *
     * @updates this
     * @ensures this = rev(#this)
     */
    public void flip() {
        Stack<T> temp = new Stack1L<>();

        while (this.length() > 0) {
            T x = this.pop();
            temp.puch(x);
        }
        this.transferFrom(temp);
    }

    /**
     * Reverses ("flips") {@code this}.
     *
     * @updates this
     * @ensures this = rev(#this)
     */
    public void flip() {
        int n = this.length();

        // Swap elements from front and back moving towards the center
        for (int i = 0; i < n / 2; i++) {
            T front = this.entry(i);
            T back = this.entry(n - 1 - i);

            this.replaceEntry(i, back);
            this.replaceEntry(n - 1 - i, front);
        }
    }

    public void flip() {
        if (this.length() <= 1) {
            return;
        }
        // Remove the first element and recursively flip the rest
        T first = this.remove(0);
        this.flip();
        // Append the first element to the end
        this.add(this.length(), first);
    }

    /**
     * Returns a map of (letter, #occurrences)
     *
     * for example: <a, b, a, d, c> return: map: a -> 2 b -> 1 c -> 1 d -> 1
     */
    public static Map<String, Integer> countLetters(Queue<String> q) {
        Queue<String> qTemp = q.newInstance();
        qTemp.transferFrom(q);

        Map<String, Integer> m = new Map1L<String, Integer>();

        while (qTemp.length() > 0) {
            String letter = qTemp.dequeue();
            if (m.hasKey(letter)) {
                int newNum = m.value(letter) + 1;
                int old = m.replaceValue(letter, newNum);
            } else {
                m.add(letter, 1);
            }
            qTemp.enqueue(letter);
        }
    }
}
