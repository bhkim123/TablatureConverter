
import Converter.Measures;
import Converter.NoteUtility;
import Converter.Validation;
import Exceptions.NotSupportedNoteException;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Put path of the file to convert: ");
        String path = sc.nextLine();
        ArrayList<String> lines = IOClass.textToStr(path);
        //Extract string information from text file.

        lines = Validation.validLines(lines);
        //Check if there is note form that is not supported.

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
        //Extract only supported string information from text file

        String allScripts = "";

        int i = 0;
        while(i < lines.size()){
            String eachMeasure = "";
            int numOfGuitar = 6;
            int j =0;
            while(j < numOfGuitar){
                eachMeasure += lines.get(i)+"\n";
                i++;
                j++;
            }
            //Extract each 6 lines to make the whole score

            Measures converter = new Measures(eachMeasure);
            String noteScripts = "";
            for(String noteScript : converter.getScriptsPerMeasrue()){
                noteScripts += noteScript;
            }
            allScripts += noteScripts;
            //Extract note scripts from each measure and add all.
        }

        allScripts = NoteUtility.getMXML(allScripts);
        // Wrap note scripts with basic xml information.

        boolean isValid = false;
        //To check if the file's saved.

        while(!isValid){
            System.out.println("Put file name that you want to save: ");
            String fileName = sc.nextLine();

            System.out.println("Put path where you want to save file (without file name)" +
                    "\n(e.g : C:\\Users\\Desktop\\myFolder ) :");
            String filePath = sc.nextLine();

            String outputPathName = filePath + "\\" + fileName + ".musicxml";
            isValid = IOClass.isFileStored(outputPathName, allScripts);
        }
    }
}
