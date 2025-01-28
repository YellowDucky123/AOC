import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class day7V2 {
    static long fileAvailable = 70000000;
    static long reqSpace = 30000000;
    public static void main(String[] args) {
        try(Stream<String> lines = Files.lines(Paths.get("input.txt"))) {
            long[] sum = k(lines);
            System.out.println("part1: " + sum[0]);
            System.out.println("part2: " + sum[1]);
        }
        catch(IOException e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }

    static long[] k(Stream<String> lines) {
        treeWrapper<String> tree = new treeWrapper<>("/");

        Pattern match = Pattern.compile(".*");
        Pattern fileMatch = Pattern.compile("\\d+ \\w+(.\\w+)?");
        Pattern dirMatch = Pattern.compile("dir \\w+");

        for (String line : (Iterable<String>) lines::iterator) {
            String[] tokens = line.split("\\s+");
            if(tokens[0].equals("$") && tokens[1].equals("cd")) {
                // tokens[0] = '$'
                // tokens[1] = "cd"
                // tokens[2] = name of directory to change to
                if(tokens[2].equals("..")) {
                    if(tree.node.parent == null) {
                        System.err.println("Invalid input");
                        System.exit(1);
                    }
                    tree.moveToParent();
                }
                else if(match.matcher(tokens[2]).matches()) {
                    if(!tokens[2].equals("/")) {
                        tree.moveToChild(tokens[2]);
                    }
                }
                else {
                    System.err.println("Invalid input");
                    System.exit(1);
                }
            }
            else {
                if(fileMatch.matcher(line).matches()) {
                    long mem = Long.parseLong(tokens[0]);
                    
                    addMem(tree.node, mem);
                }
                else if(dirMatch.matcher(line).matches()) {
                    tree.addChild(tokens[1]);
                }
            }
        }
        long p1 = sumPart1(tree.root);
        long p2 = Part2(tree.root);
        long[] ret = {p1, p2};
        return ret;
    } 

    static void addMem(TreeNode<String> node, long mem) {
        if(node == null) {
            return;
        }
        node.size += mem;
        addMem(node.parent, mem);
    }

    static long sumPart1(TreeNode<String> root) {
        long sum = 0;
        if(root.size <= 100000) {
            sum += root.size;
        }
        for(TreeNode<String> children: root.children.values()) {
            sum += sumPart1(children);
        }
        return sum;
    }

    static long Part2(TreeNode<String> root) {
        long min = Long.MAX_VALUE;
        long unusedSpace = fileAvailable - root.size;
        return rec(root, min, unusedSpace);
    }   

    static long rec(TreeNode<String> root, long min, long unusedSpace) {
        if((unusedSpace + root.size) >= reqSpace && root.size < min) {
            min = root.size;
        }

        for(TreeNode<String> children: root.children.values()) {
            long tmp = rec(children, min, unusedSpace);
            if(tmp < min) {
                min = tmp;
            }
        }
        return min;
    }
}
