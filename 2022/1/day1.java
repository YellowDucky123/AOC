import java.io.File;
import java.nio.file.*;
import java.util.List;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;



public class day1 {
    public static void main(String[] arg) {
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader("input.txt");
            br = new BufferedReader(fr);
            
            List<List<String>> groups = new ArrayList<>();
            List<String> g = new ArrayList<>();
            String line;
            while((line = br.readLine()) != null) {
                if(line.trim().isEmpty()) {
                    groups.add(g);
                    g = new ArrayList<>();
                }
                else {
                    g.add(line);
                }
            }
            if(!g.isEmpty()) {
                groups.add(g);
            }

            System.out.println(most(groups));
            System.out.println(mostTopThree(groups));
        }
        catch(IOException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing resources: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    static int most(List<List<String>> groups) {
        int most = 0;

        for(List<String> g: groups) {
            int tmp = 0;
            for(String s: g) {
                int x = Integer.parseInt(s);
                tmp += x;
            }
            if(tmp > most) {
                most = tmp;
            }
        }

        return most;
    }

    static int mostTopThree(List<List<String>> groups) {
        int count = 0;

        for(int i = 0; i < 3; i++) {
            int most = 0;
            int idx = -1;
            for(int k = 0; k < groups.size(); k++) {
                int tmp = 0;
                for(String s: groups.get(k)) {
                    int x = Integer.parseInt(s);
                    tmp += x;
                }
                if(tmp > most) {
                    most = tmp;
                    idx = k;
                }
            }   

            if(idx != -1) {
                count += most;
                groups.remove(idx);
            }
        }

        return count;
    }
}