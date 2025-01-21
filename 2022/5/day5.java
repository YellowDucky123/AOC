import java.io.FileNotFoundException;
import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class day5 {
    public static void main(String[] arg) {
        try(Scanner sc = new Scanner(new File("input.txt"))) {
            List<String> lines = new ArrayList<>();
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                if(line.trim().isEmpty()) {
                    break;
                }

                lines.add(line);
            }

            List<Stack<String>> stacks = insertData(lines);
            
            while(sc.hasNextLine()) {
                String line = sc.nextLine();

                Pattern p = Pattern.compile("\\d+");
                Matcher m = p.matcher(line);

                int instance = 1;
                int amount = 0, from = 0, to = 0;
                while(m.find()) {
                    if(instance == 1) {
                        amount = Integer.parseInt(m.group());
                    }
                    else if(instance == 2) {
                        from = Integer.parseInt(m.group());
                    }
                    else {
                        to = Integer.parseInt(m.group());
                    }
                    instance++;
                }
                moveCrates(stacks, amount, from ,to);
            }

            String res = "";
            for(Stack s: stacks) {
                res = res + s.peek();
            }
            System.out.println(res);
        }
        catch(FileNotFoundException e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }

    static void moveCrates(List<Stack<String>> stacks, int amount, int from, int to) {
        int Fidx = from - 1;
        int Tidx = to - 1;
        
        for(int i = 0; i < amount; i++) {
            String crate = stacks.get(Fidx)
                                 .pop();
            stacks.get(Tidx)
                  .push(crate);
        }
    }

    static List<Stack<String>> insertData(List<String> lines) {
        List<Stack<String>> ret = new ArrayList<>();

        Pattern p = Pattern.compile("\\d+");
        int numIdx = lines.size() - 1;
        Matcher m = p.matcher(lines.get(numIdx));
        while(m.find()) {
            Stack<String> currS = new Stack<>();
            for(int i = numIdx - 1; i >= 0; i--) {
                String s = lines.get(i);
                String temp = String.valueOf(s.charAt(m.start()));
                if(!temp.trim().isEmpty()) {
                    currS.push(temp);
                }
            }
            ret.add(currS);
        }
        return ret;
    }
}
