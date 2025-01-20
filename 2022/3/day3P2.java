import java.io.*;
import java.util.*;

public class day3P2 {
    public static void main(String[] arg) {
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader("input.txt");
            br = new BufferedReader(fr);

            int count = 0;
            String line;
            List<String> storage = new ArrayList<>();
            while((line = br.readLine()) != null) {
                storage.add(line);
                for(int i = 0; i < 2; i++) {
                    line = br.readLine();
                    storage.add(line);
                }
                count += badge(storage);
                storage = new ArrayList<>();
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

    static int badge(List<String> storage) {
        String e1 = storage.get(0);
        String e2 = storage.get(1);
        String e3 = storage.get(2);

        for(int i = 0; i < e1.length(); i++) {
            char c = e1.charAt(i);
            if(e2.indexOf(c) != -1 && e3.indexOf(c) != -1) {
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
