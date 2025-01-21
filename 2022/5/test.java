import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {
    public static void main(String[] arg) {
        String s = "hek9jkal11nlsaklj8";
        Pattern pat = Pattern.compile("\\d+");
        Matcher match = pat.matcher(s);

        while(match.find()) {
            System.out.println(match.start());
            System.out.println(match.end());
            System.out.println();
        }
    }
}
