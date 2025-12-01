import java.util.HashMap;
import java.util.Map;

/**
 * Put a short phrase describing the program here.
 *
 * @author Baowen Liu
 *
 */
public final class MapLearning {
    public static void main(String[] args) {
        Map<String, Integer> empIds = new HashMap<>();
        empIds.put("John", 12345);
        empIds.put("Carl", 54321);
        empIds.put("Baowen", 66666);
        System.out.println(empIds);
        System.out.println();
        System.out.println(empIds.get("Baowen"));
    }

}
