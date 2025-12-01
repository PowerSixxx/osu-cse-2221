import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.queue.Queue;
import components.queue.Queue1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;

/**
 * Put a short phrase describing the program here.
 *
 * @author Baowen Liu
 *
 */
public final class FinalReviewRecursion {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private FinalReviewRecursion() {
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

    public static int count7(NaturalNumber n) { // Like 747 -> 2, 777 -> 3
        int lastDigit = n.divideBy10();
        int result = 0;
        if (lastDigit == 7) {
            result = 1;
        }
        if (!n.isZero()) {
            result += count7(n);
        }
        n.multiplyBy10(lastDigit);
        return result;
    }

//    /**
//     * Recursively flips the given sequence multiple times in an alternating
//     * pattern by first flipping the given Sequence s and then multiFlipping the
//     * subsequence s[1,|s|) in s. For example, if s is a Sequence with Integers
//     * and #s = <1,2,3,4,5,6>, then s = <6,2,4,3,5,1>. If #s = <1,2,3,4,5>, then
//     * s = <5,2,3,4,1> at the end of the method.
//     *
//     * @param <E>
//     */
//    private static <E> void multiFlip(Sequence<E> s) {
//        Sequence<E> temp = s.newInstance();
//        if (s.length() > 2) {
//            s.extract(1, s.length() - 2, temp);
//            multiFlip(temp);
//        }
//        s.flip();
//        s.insert(1, temp);
//    }

    /**
     * Reports the value of n as an int.
     *
     * @requires n <= Integer.MAX_VALUE
     * @ensures toInt = n
     */
    private static int toInt(NaturalNumber n) {
        int result = 0;
        int lastDigit = 0;
        if (!n.isZero()) {
            lastDigit = n.divideBy10();
            result = lastDigit + toInt(n) * 10;
        }
        n.multiplyBy10(lastDigit);
        return result;
    }

    /**
     * Finds the first occurrence of the tag node with the name tagName among
     * the children of the given XMLTree and returns its index, or -1 if not
     * found
     */
    private static int firstTagChildrenIndex(XMLTree xml, String tagName) {
        int i = 0;
        int result = -1;
        while (i < xml.numberOfChildren() && result == -1) {
            if (xml.child(i).isTag() && xml.child(i).label().equals(tagName)) {
                result = i;
            }
            i++;
        }
        return result;
    }

    /**
     * counts the number of times the number 1 appears in n
     *
     * @ensures count1s = count(n, 1)
     */
    public static int count1s(NaturalNumber n) {
        int count = 0;
        int lastDigit = n.divideBy10();
        if (lastDigit == 1) {
            count = 1;
        }
        count = count + count1s(n);
        n.multiplyBy10(lastDigit);
        return count;
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

        Queue<Integer> q = new Queue1L<>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);
        // Before this line, q is now {1 2 3 4 5}
        reverse(q);
        out.println(q);

        NaturalNumber n = new NaturalNumber2(747477676);
        out.println(count7(n));
        out.println(n);

        NaturalNumber a = new NaturalNumber2(798);
        out.println(toInt(a));
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
