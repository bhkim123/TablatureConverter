
import Converter.Measures;
import Converter.NoteUtility;
import Converter.Validation;
import Exceptions.NotSupportedNoteException;
import Exceptions.UnvalidNumberOfLinesException;

import java.io.BufferedWriter;
import java.io.FileWriter;
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
            Measures converter = new Measures(eachMeasure);
            String noteScripts = "";
            for(String noteScript : converter.getScriptsPerMeasrue()){
                noteScripts += noteScript;
            }
            allScripts += noteScripts;
        }

        allScripts = NoteUtility.getMXML(allScripts);

        System.out.println("Put path of folder where you want to save file (without file name): ");
        String filePath = sc.nextLine();

        System.out.println("Put file name that you want to save: ");
        String fileName = sc.nextLine();

        String outputPathName = filePath + fileName + ".musicxml";

        try{
            BufferedWriter fw = new BufferedWriter(new FileWriter(outputPathName, true));

            fw.write(allScripts);
            fw.flush();

            fw.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
