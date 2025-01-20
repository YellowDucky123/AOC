import java.nio.file.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.io.IOException;

public class day2 {

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
                int a = shapeNum(tokens[0]);
                int b = shapeNum(tokens[1]);
                count += winLose(a, b) + b;
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

    static int shapeNum(String s) {
        Map<String, Integer> d = Map.of(
            "A", 1, 
            "B", 2, 
            "C", 3, 
            "X", 1, 
            "Y", 2, 
            "Z", 3
        );
        return d.get(s);
    }

    static int winLose(int a, int b) {
        if(b == a) {
            return 3;
        }
        else if(
            (b == 1 && a == 3) ||
            (b == 2 && a == 1) ||
            (b == 3 && a == 2)
        ) {
            return 6;
        }
        else {
            return 0;
        }
    }
}
