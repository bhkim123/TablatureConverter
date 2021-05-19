package Converter;

import java.util.ArrayList;
import java.util.Collections;

public class Notes {
    protected final int BEATS = 4;
    protected final int DIVISION = 4;
    protected int totalDurationPerMeasrue;
    protected int totalMeasureLength;
    protected ArrayList<Notation> notes;
    protected String[][] noteBox;
    protected static int frontRest;
    protected ArrayList<String> completedNoteScript;

    public Notes(ArrayList<String> eachMeasureInfo){
        this.totalDurationPerMeasrue = BEATS * DIVISION;
        this.totalMeasureLength = eachMeasureInfo.get(0).length();
        this.notes = new ArrayList<>();
        this.frontRest = Integer.MAX_VALUE;

        this.noteBox = new String[eachMeasureInfo.size()][];
        for(int i = 0; i < this.noteBox.length; i++){
            this.noteBox[i] = new String[totalDurationPerMeasrue];
        }

        for(int i = 0; i < eachMeasureInfo.size(); i++){
            putNotes(eachMeasureInfo.get(i), i);
        }

        int frontRestDuration = (int) ((double)frontRest / (double)totalMeasureLength + 0.5);
        if(frontRestDuration >= 1){
            Notation restNote = new Notation(null,0, 0);
            notes.add(0, restNote);
        }

        Collections.sort(notes);
        NoteScript noteScrt  = new NoteScript(notes, totalMeasureLength, totalDurationPerMeasrue);
        this.completedNoteScript = noteScrt.getEachNoteScript();
    }

    public ArrayList<String> getCompletedNoteScript(){
        return this.completedNoteScript;
    }

    private void putNotes(String lines, int stringNum){
        String temp = lines;

        String[] splitChar = temp.split("[-]");
        ArrayList<String> notations = new ArrayList<>();
        for(String notation : splitChar){
            if(!notation.equals("") && !notation.equals(" ")){
                notations.add(notation);
            }
        }

        for(int i = 0; i < notations.size(); i++){
            String notation = notations.get(i);
            int index = temp.indexOf(notation);
            if(i == 0){
                if(index < frontRest){
                    frontRest = index;
                }
            }
            Notation newNote = new Notation(notation, index, stringNum);
            notes.add(newNote);

            StringBuilder builder = new StringBuilder(temp);
            for(int j = index; j < index + notation.length(); j++){
                builder.setCharAt(j,'-');
            }
            temp = builder.toString();
        }
    }
}
