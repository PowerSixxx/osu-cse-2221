import java.util.Stack;

/**
 * Put a short phrase describing the program here.
 *
 * @author Baowen Liu
 *
 */
// Stack 就像是摞书，LIFO (Last-In-First-Out)，最后添加的就是在最上方
// Stack index is from bottom to top
public final class StackLearning {
    public static void main(String[] args) {
        Stack<String> games = new Stack<>();
        games.add("Call of Duty");
        games.add("Guitar Hero");
        games.add("Super Monkey Ball");

        // pop()直接移除最上方的stack，而peek()是return最上方的但不移除
        System.out.println(games);
        System.out.println(games.peek());
        System.out.println(games);
    }

}
