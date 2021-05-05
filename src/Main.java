
import Converter.Validation;
import Exceptions.NotSupportedNoteException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Put path of the file to convert: ");
        String path = sc.nextLine();
        ArrayList<String> lines = IOClass.textToStr(path);
        lines = Validation.validLines(lines);
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
        //extract only supported information from text file


    }
}
