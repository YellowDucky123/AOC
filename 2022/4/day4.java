import java.nio.file.*;
import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class day4 {
    public static void main(String[] arg) {
        try(Stream<String> lines = Files.lines(Paths.get("input.txt"))) {
            int[] count = {0};
            lines.forEach(line -> {
                if(overlapRange(line)) {
                    count[0]++;
                }
            });
            System.out.println(count[0]);
        }
        catch(IOException e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }

    }

    static boolean overlapRange(String s) {
        String[] token = s.split(",");
        List<int[]> ranges = new ArrayList<>();
        for(String t: token) {
            String[] range = t.split("-");
            int start = Integer.parseInt(range[0]);
            int end = Integer.parseInt(range[1]);
            ranges.add(new int[]{start, end});
        }

        return overLap(ranges.get(0), ranges.get(1)) || overLap(ranges.get(1), ranges.get(0));
    }

    // true if range in b fully overlaps in a
    static boolean overLap(int[] a, int[] b) {
        return b[0] >= a[0] && b[1] <= a[1];
    }
}
