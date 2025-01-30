// day 9 part2

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
            int[][] nodePositions = {
                {0, 0},     // (0) head
                {0, 0},     // (1) node 1
                {0, 0},     // (2) node 2
                {0, 0},     // (3) node 3
                {0, 0},     // (4) node 4
                {0, 0},     // (5) node 5
                {0, 0},     // (6) node 6
                {0, 0},     // (7) node 7
                {0, 0},     // (8) node 8
                {0, 0}      // (9) tail
            };
            HashSet<List<Integer>> visited = new HashSet<>(); // store all the visited places

            visited.add(Arrays.asList(0, 0));
            for(String line: (Iterable<String>) lines::iterator) {
                String[] movement = line.split("\\s+");
                int amountOfMovement = Integer.parseInt(movement[1]);

                // movement direction
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
                
                // move said times
                for(int i = 0; i < amountOfMovement; i++) {
                    // move the nodes in pairs of two
                    // e.g. head, and node1 - node1 and node2 - etc. 'till tail
                    int[] tail = new int[2];
                    for(int k = 0; k < 9; k++) {
                        tail = updatePosition(nodePositions[k], nodePositions[k + 1], move, k);
                    }
                    visited.add(Arrays.asList(tail[0], tail[1]));
                }  
            }
            // System.out.println(visited);
            System.out.println("Part2: " + visited.size());

        }
        catch(IOException e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // print all the positions of the nodes
    static void print(int[][] nodePositions) {
        for(int[] i: nodePositions) {
            System.out.println(i[0] + "," + i[1]);
        }
        System.out.println();
    }

    static int[] updatePosition(int[] headPosition, int[] tailPosition, int[] movement, int k) {
        if(k == 0) {    // k == 0 means the head is being updated, if not then it's just the rest of the body
            // new head position
            headPosition[0] += movement[0];
            headPosition[1] += movement[1];
        }

        if(adjacent(headPosition, tailPosition)) {    // check if they're still adjacent
            return tailPosition;
        }

        int rowDiff = headPosition[0] - tailPosition[0];
        int colDiff = headPosition[1] - tailPosition[1];
        if(headPosition[0] == tailPosition[0]) {
            if(colDiff > 0) {
                tailPosition[1] += 1;
            }
            else {
                tailPosition[1] -= 1;
            }
        }
        else if(headPosition[1] == tailPosition[1]) {
            if(rowDiff > 0) {
                tailPosition[0] += 1;
            }
            else {
                tailPosition[0] -= 1;
            }
        }
        else if(Math.abs(rowDiff) > 1) {
            if(rowDiff > 0) {
                tailPosition[0] += 1;
            }
            else {
                tailPosition[0] -= 1;
            }

            if(colDiff < 0) {
                tailPosition[1] -= 1;
            }
            else {
                tailPosition[1] += 1;
            }
        }
        else if(Math.abs(colDiff) > 1) {
            if(colDiff > 0) {
                tailPosition[1] += 1;
            }
            else {
                tailPosition[1] -= 1;
            }

            if(rowDiff < 0) {
                tailPosition[0] -= 1;
            }
            else {
                tailPosition[0] += 1;
            }
        }
        return tailPosition;
    }

    static boolean adjacent(int[] headPosition, int[] tailPosition) {
        if(Math.abs(headPosition[0] - tailPosition[0]) <= 1 && Math.abs(headPosition[1] - tailPosition[1]) <= 1) {
            return true;
        }
        return false;
    }
}
