import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Stream;

public class day8 {
    public static void main(String[] args) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("input.txt"));
            int row = lines.size();
            int col = lines.get(0).length();

            int[][] grid = new int[row][col];
            impGrid(grid, lines, row, col);
            System.out.println("part1: " + visibleCount(grid, row, col));
            System.out.println("part2: " + highestScenicScore(grid, row, col));
        }
        catch(IOException e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }

    static int highestScenicScore(int[][] grid, int row, int col) {
        int highest = 0;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                int[] gridStat = {row, col};
                int[] pos = {i, j};
                int score = scenicScore(grid, gridStat, pos);
                if(highest < score) {
                    highest = score;
                }
            }
        }

        return highest;
    }

    static int scenicScore(int[][] grid, int[] gridStat, int[] pos) {
        int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int positionHeight = grid[pos[0]][pos[1]];

        int score = 1;

        for(int[] d: dir) {
            int row = pos[0];
            int col = pos[1];
            int sideScore = 0;
            while(true) {
                row += d[0];
                col += d[1];
                if(row < 0 || row >= gridStat[0] || col < 0 || col >= gridStat[1]) {
                    break;
                }
                sideScore++;
                if(grid[row][col] >= positionHeight) {
                    break;
                }
            }
            score *= sideScore;
        }

        return score;
    }

    static int visibleCount(int[][] grid, int row, int col) {
        int count = 0;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                int[] gridStat = {row, col};
                int[] pos = {i, j};
                if(visible(grid, gridStat, pos)) {
                    count++;
                }
            }
        }

        return count;
    }

    static boolean visible(int[][] grid, int[] gridStat, int[] pos) {
        int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int positionHeight = grid[pos[0]][pos[1]];

        int notVisible = 0;
        for(int[] d: dir) {
            int row = pos[0];
            int col = pos[1];
            while(true) {
                row += d[0];
                col += d[1];
                if(row < 0 || row >= gridStat[0] || col < 0 || col >= gridStat[1]) {
                    break;
                }
                if(grid[row][col] >= positionHeight) {
                    notVisible++;
                    break;
                }
            }
        }
        if(notVisible == 4) {
            return false;
        }
        return true;
    }

    static void impGrid(int[][] grid, List<String> lines, int row, int col) {
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                grid[i][j] = lines.get(i).charAt(j) - '0';
            }
        }
    }
}
