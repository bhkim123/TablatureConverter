package Converter;

import Exceptions.NotSupportedNoteException;
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

        for(int i = 0; i < allLines.size(); i++){
            String str = allLines.get(i);
            if(str.contains("-") && str.contains("|") && !str.contains(" ") && str.length() >= 3 && str != null){
                resultLines.add(str);
            }
        }

        if(resultLines.size() % NUM_OF_GUITAR_STRING == 0){
            return resultLines;
        }
        else{
            throw new UnvalidNumberOfLinesException("Guitar string Number Error");
        }
    }

    public static boolean isValidNotes(ArrayList<String> valindLines) throws Exception {
        for(String s: valindLines){
            String[] splittedStrs = s.split("[-]");
            for(String notes: splittedStrs){
                if(!notes.equals("") && !notes.equals("|") && !notes.equals(":") && !Validation.noteValidation(notes)){
                    throw new NotSupportedNoteException("Unrecognizable notes");
                }
            }
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
