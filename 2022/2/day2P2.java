import java.nio.file.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.io.IOException;

public class day2P2 {

    public static void main(String[] arg) {
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader("input.txt");
            br = new BufferedReader(fr);

            int count = 0;
            String line;
            while((line = br.readLine()) != null) {
                String[] tokens = line.split("\\s+");
                count += point(tokens[0], tokens[1]);
            }
            System.out.println(count);
        }
        catch(IOException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        finally {
            try {
                if(fr != null) {
                    fr.close();
                }
                if(br != null) {
                    br.close();
                }
            }
            catch(IOException e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    static int symbolNum(String s) {    // what it takes to draw
        Map<String, Integer> d = Map.of(
            "A", 1, 
            "B", 2, 
            "C", 3, 
            "X", 0, 
            "Y", 3, 
            "Z", 6
        );
        return d.get(s);
    }

    static int losesymNum(String s) {   // what it takes to lose
        Map<String, Integer> l = Map.of(
            "A", 3,
            "B", 1,
            "C", 2
        );
        return l.get(s);
    }

    static int winsymNum(String s) {    // what it takes to win
        Map<String, Integer> w = Map.of(
            "A", 2,
            "B", 3,
            "C", 1
        );
        return w.get(s);
    }

    static int point(String a, String b) {
        int round = symbolNum(b);
        if(round == 3) {    // draw
            return round + symbolNum(a);
        }
        else if(round == 0) {   // lose
            return losesymNum(a);
        }
        else {  // win
            return round + winsymNum(a);
        }
    }
}
