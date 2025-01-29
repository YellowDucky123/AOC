import java.util.*;

public class test {
    public static void main(String[] args) {
        HashSet<List<Integer>> visited = new HashSet<>();
        int[] ar = {0, 1};
        visited.add(Arrays.asList(ar[0], ar[1]));
        visited.add(Arrays.asList(0, 1));
        System.out.println(visited);
    }
}
