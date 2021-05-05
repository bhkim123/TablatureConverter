package Converter;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Validation {
    final static int NUM_OF_GUITAR_STRING = 6;
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

            if(count == NUM_OF_GUITAR_STRING){
                for(String s: valid6lines)
                    resultLines.add(s);
            }
        }
        return resultLines;
    }

    public static boolean isValidNotes(ArrayList<String> valindLines){
        int index = 0;
        for(String s: valindLines){
            while(index < s.length()){
                while(s.charAt(index) != '-' && s.charAt(index) != '|'){
                    int start = index;
                    while(s.charAt(index) != '-' && s.charAt(index) != '|'){
                        index++;
                    }
                    String note = s.substring(start, index);
                    if(!Validation.noteValidation(note)){
                        return false;
                    }
                }
                index++;
            }
            index = 0;
        }
        return true;
    }

    public static boolean noteValidation(String note){
        if(Pattern.matches("[0-9]", note))
            return true;
        else if(Pattern.matches("^[12][0-9]&", note))
            return true;
        else if(Pattern.matches("[(][12]?[0-9]?[)]", note))
            return true;
        else if(Pattern.matches("[xX]", note))
            return true;
        else
            return false;
        // **need more note regex
    }

}
