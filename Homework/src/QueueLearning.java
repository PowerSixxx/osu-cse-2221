import java.util.LinkedList;
import java.util.Queue;

/**
 * Put a short phrase describing the program here.
 *
 * @author Baowen Liu
 *
 */
public final class QueueLearning {
    public static void main(String[] args) {
        Queue<String> bbqLine = new LinkedList<>();

        bbqLine.add("Aaowen");
        bbqLine.add("Baowen");
        bbqLine.add("Caowen");

        System.out.println(bbqLine);

        System.out.println(bbqLine.poll());
        System.out.println(bbqLine);
    }

}
