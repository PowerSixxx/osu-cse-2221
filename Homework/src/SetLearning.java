import java.util.HashSet;
import java.util.Set;

/**
 * Put a short phrase describing the program here.
 *
 * @author Baowen Liu
 *
 */
// HashSet (Random order), TreeSet (alphabet order), LinkedSet (Insertion ordered)
public final class SetLearning {
    public static void main(String[] args) {
        Set<String> names = new HashSet<>();
        names.add("Amy");
        names.add("Bob");
        names.add("Carter");
        names.add("Delven");
        names.add("Elon");
        System.out.println(names);
        System.out.println(names.contains("Amy"));
        names.remove("Amy");
        System.out.println(names);
        System.out.println(names.contains("Amy"));
        System.out.println();
        for (String name : names) {
            System.out.println(name);
        }
    }

}
