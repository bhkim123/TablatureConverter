import java.util.ArrayList;
import java.util.regex.Pattern;

public class Validation {
    public static ArrayList<String> validLines(ArrayList<String> allLines){
        ArrayList<String> resultLines = new ArrayList<>();
        String[] valid6lines = new String[6];
        int count = 0;

        for(String str: allLines){
            if(str.contains("-") && str.contains("|") && str.length() >= 3){
                valid6lines[count] = str;
                count++;
            }
            else{
                count = 0;
            }

            if(count == 6){
                for(String s: valid6lines)
                    resultLines.add(s);
            }
        }
        return resultLines;
    }

    public static boolean isValidNotes(ArrayList<String> valindLines){
        return false;
    }
}
