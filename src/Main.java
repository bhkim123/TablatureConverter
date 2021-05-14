
import Converter.Validation;
import Exceptions.NotSupportedNoteException;
import Exceptions.UnvalidNumberOfLinesException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Put path of the file to convert: ");
        String path = sc.nextLine();
        ArrayList<String> lines = IOClass.textToStr(path);
        lines = Validation.validLines(lines);
        for(String s: lines)
            System.out.println(s);
        if(lines.size() !=  0){
            if(!Validation.isValidNotes(lines)){
                try{
                    throw new NotSupportedNoteException("This note is not supported");
                }
                catch (NotSupportedNoteException e){
                    e.printStackTrace();
                }
            }
        }
        //extract only supported string information from text file
    }
}
