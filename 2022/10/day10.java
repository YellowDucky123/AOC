import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class day10 {
    public static void main(String[] args) {
        try (Stream<String> lines = Files.lines(Paths.get("input.txt"))){
            Map<Integer, Integer> values = new HashMap<>();
            int x = 1;
            int cycle = 1;
            for(String line: (Iterable<String>) lines::iterator) {
                line = line.strip();
                if(line.compareTo("noop") == 0) {
                    cycle++;
                    values.put(cycle, x * cycle);
                    continue;
                }
                String[] words = line.split("\\s+");
                if(words[0].compareTo("addx") == 0) {
                    values.put(cycle, x * cycle);
                    
                    values.put(cycle + 1, x * (cycle + 1));
                    x += Integer.parseInt(words[1]);
                    cycle += 2;
                }
                values.put(cycle, x * cycle);
            }
            System.out.println(valuesSum(values));
        }
        catch(IOException e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }

    static int valuesSum(Map<Integer, Integer> values) {
        int sum = 0;
        for(int key = 20; key <= 220; key += 40) {
            sum += values.get(key);
        }
        return sum;
    }
}
