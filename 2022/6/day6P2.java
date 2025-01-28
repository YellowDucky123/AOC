import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.List;

public class day6P2 {
    public static void main(String[] args) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("input.txt"));
            int marker = getMarker(lines.get(0));
            System.out.println(marker);
        }
        catch(IOException e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }

    static int getMarker(String l) {
        for(int idx = 14; idx < l.length(); idx++) {
            String fString = l.substring(idx - 14, idx);

            List<Character> check = new ArrayList<>();
            boolean flag = false;
            for(char c: fString.toCharArray()) {
                if(check.indexOf(c) != -1) {
                    flag = true;
                    break;
                }
                check.add(c);
            }

            if(!flag) {
                return idx;
            }
        }
        return -1;
    }
}
