import java.io.*;
import java.util.*;

public class day3 {
    public static void main(String[] arg) {
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader("input.txt");
            br = new BufferedReader(fr);

            int count = 0;
            String line;
            while((line = br.readLine()) != null) {
                count += sameEl(line);
            }
            System.out.println(count);
        }
        catch(IOException e) {
            System.out.println("ERROR: " + e.getMessage());
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
                System.out.println("ERROR: " + e.getMessage());
                e.printStackTrace();
            }

        }
    }

    static int sameEl(String line) {
        int len = line.length();
        String comp1 = line.substring(0, len / 2);
        String comp2 = line.substring(len / 2);

        for(int i = 0; i < len / 2; i++) {
            char c = comp1.charAt(i);
            if(comp2.indexOf(c) != -1) {
                if(Character.isLowerCase(c)) {
                    return c - 96;
                }
                else {
                    return c - 38;
                }
            }
        }
        return -1;
    }
}
