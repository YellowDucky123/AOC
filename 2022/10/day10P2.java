import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class day10P2 {
    public static void main(String[] args) {
        try (Stream<String> lines = Files.lines(Paths.get("input.txt"))){
            int x = 1;
            int cycle = 1;
            for(String line: (Iterable<String>) lines::iterator) {
                line = line.strip();
                printPixel(cycle, x);
                cycle++;
                String[] words = line.split("\\s+");
                if(words[0].compareTo("addx") == 0) {
                    printPixel(cycle, x);
                    x += Integer.parseInt(words[1]);
                    cycle++;
                }
            }
        }
        catch(IOException e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }

    static void printPixel(int cycle, int sprite) {
        int pixel = (cycle - 1) % 40;

        if(pixel >= sprite - 1 && pixel <= sprite + 1) {
            System.out.print("██");
        }
        else {
            System.out.print("░░");
        }

        if(pixel == 39) {
            System.out.println();
        }
    }
}
