// day 7 version 1 (this one) not finished
// this version attempts it with stack

import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.nio.file.Paths;

public class day7 {
    public static void main(String[] args) {
        try(Stream<String> lines = Files.lines(Paths.get("input.txt"))) {   // read the lines into a stream
            System.out.println(k(lines));
        }
        catch(IOException e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }


    // going through the commands
    static Long k(Stream<String> lines) {
        Map<String, Long> memDir = new HashMap<>();  // store bytes of each directory
        Stack<String> directories = new Stack<>();  // store where we are currently in the directories
        TreeNode<String> = new TreeNode<>("/");

        String root = "/";

        Pattern fileMatch = Pattern.compile("\\d+ \\w+(.\\w+)?");
        Pattern dirMatch = Pattern.compile("dir \\w+");
        lines.forEach(line -> {
            if(line.contains("$ cd")) {
                String[] parts = line.split("\\s+");
                if(parts[2].equals("..")) {
                    directories.pop();
                }
                else {
                    if(parts[2].equals("jssnn")) System.out.println("jssnn");
                    directories.push(parts[2]);
                }
            }
            else if(fileMatch.matcher(line).matches()) {
                String[] fileData = line.split("\\s+");  // splits the line into file size and file name
                String currDir = directories.peek();

                if(memDir.containsKey(currDir)) {
                    Long tmpValue = memDir.get(currDir); // get the current value
                    tmpValue += Integer.parseInt(fileData[0]);  // update the value
                    memDir.put(currDir, tmpValue);  // put the value back
                }
                else {
                    memDir.put(currDir, Long.parseLong(fileData[0])); // add the value and key
                }
            }
            else if(dirMatch.matcher(line).matches()) {
                String parent = directories.peek();
                String[] l = line.split("\\s+");

                dirEntry.insertDir(parent, l[1]);
            }
        });
        dirEntry.updateRecord(memDir, root);

        // dirEntry.printList();
        return sumOfpart1(memDir);
    }

    static Long sumOfpart1(Map<String, Long> memDir) {
        Long count = 0L;
        for(Long value: memDir.values()) {
            if(value <= 100000) {
                count += value;
            }
        }
        return count;
    }
}
