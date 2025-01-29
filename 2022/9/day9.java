import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Stream;
import java.util.List;

public class day9 {
    public static void main(String[] args) {
        try(Stream<String> lines = Files.lines(Paths.get("input.txt"))) {
            int[] headPosition = {0, 0};
            int[] tailPosition = {0, 0};
            HashSet<List<Integer>> visited = new HashSet<>(); // store all the visited places

            visited.add(Arrays.asList(0, 0));
            for(String line: (Iterable<String>) lines::iterator) {
                String[] movement = line.split("\\s+");
                int amountOfMovement = Integer.parseInt(movement[1]);
                for(int i = 0; i < amountOfMovement; i++) {
                    int[] move = new int[2];
                    if(movement[0].equals("R")) {
                        move[0] = 0;
                        move[1] = 1;
                    }
                    else if(movement[0].equals("L")) {
                        move[0] = 0;
                        move[1] = -1;
                    }
                    else if(movement[0].equals("U")) {
                        move[0] = 1;
                        move[1] = 0;
                    }
                    else {
                        move[0] = -1;
                        move[1] = 0;
                    }
                    updatePosition(headPosition, tailPosition, move, visited);
                }
            }
            System.out.println("Part1: " + visited.size());

        }
        catch(IOException e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }

    static void updatePosition(int[] headPosition, int[] tailPosition, int[] movement, HashSet<List<Integer>> visited) {
        // new head position
        headPosition[0] += movement[0];
        headPosition[1] += movement[1];

        if(adjacent(headPosition, tailPosition)) {    // check if they're still adjacent
            return;
        }

        if(headPosition[0] == tailPosition[0] || headPosition[1] == tailPosition[1]) {  // if they're not diagonal
            tailPosition[0] += movement[0];
            tailPosition[1] += movement[1];
            visited.add(Arrays.asList(tailPosition[0], tailPosition[1]));
            return;
        }

        if(Math.abs(headPosition[0] - tailPosition[0]) > 1) {
            tailPosition[0] += movement[0];
            if(headPosition[1] - tailPosition[1] < 0) {
                tailPosition[1] -= 1;
            }
            else {
                tailPosition[1] += 1;
            }
        }
        else if(Math.abs(headPosition[1] - tailPosition[1]) > 1) {
            tailPosition[1] += movement[1];
            if(headPosition[0] - tailPosition[0] < 0) {
                tailPosition[0] -= 1;
            }
            else {
                tailPosition[0] += 1;
            }
        }
        visited.add(Arrays.asList(tailPosition[0], tailPosition[1]));
    }

    static boolean adjacent(int[] headPosition, int[] tailPosition) {
        if(Math.abs(headPosition[0] - tailPosition[0]) <= 1 && Math.abs(headPosition[1] - tailPosition[1]) <= 1) {
            return true;
        }
        return false;
    }
}
