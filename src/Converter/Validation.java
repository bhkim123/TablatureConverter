package Converter;

import Exceptions.UnvalidNumberOfLinesException;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Validation {
    final static int NUM_OF_GUITAR_STRING = 6;
    public static ArrayList<String> validLines(ArrayList<String> allLines) throws UnvalidNumberOfLinesException {
        if(allLines.size() == 0){
            return null;
        }

        ArrayList<String> resultLines = new ArrayList<>();
        int count = 0;

        String str = allLines.get(count);
        while(count < allLines.size()){
            int tempCount = count;
            while(str.contains("-") && str.contains("|") && !str.contains(" ") && str.length() >= 3){
                tempCount++;
                str = allLines.get(tempCount);
            }

            if(tempCount == NUM_OF_GUITAR_STRING){
                for(int i = tempCount - NUM_OF_GUITAR_STRING; i < tempCount; i++){
                    str = allLines.get(i);
                    resultLines.add(str);
                }
            }

            if(tempCount > count){
                count = tempCount;
            }
            else {
                count++;
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
