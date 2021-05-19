package Converter;

import java.util.ArrayList;

public class NoteScript {
    private ArrayList<String> eachNoteScript;
    private int totalMeasureLength;
    private int totalDurationPerMeasure;
    public NoteScript(ArrayList<Notation> notes, int totalMeasureLength, int totalDurationPerMeasure){
        this.eachNoteScript = new ArrayList<>();
        this.totalMeasureLength = totalMeasureLength;
        this.totalDurationPerMeasure = totalDurationPerMeasure;

        for(int i = 0; i < notes.size(); i++){
            ArrayList<Notation> tempNotes = new ArrayList<>();
            tempNotes.add(notes.get(i));
            int index = notes.get(i).getIndex();
            int j = index;
            while(i + 1 < notes.size() && index == notes.get(i + 1).getIndex()){
                tempNotes.add(notes.get(i + 1));
                i++;
            }
            int durationLength = 0;
            if(i + 1 < notes.size()){
                durationLength = notes.get(i + 1).getIndex() - notes.get(i).getIndex();
            }
            else {
                durationLength = totalMeasureLength - notes.get(i).getIndex();
            }
            makeNote(tempNotes, durationLength);
        }

    }
    public ArrayList<String> getEachNoteScript(){
        return null;
    }

    private void makeNote(ArrayList<Notation> tempNotes, int durationLen){
        if(tempNotes.size() == 1){
            putScripts(tempNotes.get(0), durationLen);
        }
        else{
            for(int i = 0; i < tempNotes.size(); i++){
                putChordScripts(tempNotes.get(i), durationLen, i);
            }
        }
    }

    private void putChordScripts(Notation note , int durationLen, int index){
        int duration = (int) (((double)durationLen / (double)this.totalMeasureLength) * (double)totalDurationPerMeasure + 0.5);
        if(index == 0){
            StringBuilder result = new StringBuilder("");
            int singleRegularNote = Integer.valueOf(note.getNotation());
            int strNum = note.getStringNum() + 1;
            result.append("<note>\n");
            String pitch = NoteUtility.pitch(NoteUtility.octave(strNum, singleRegularNote), NoteUtility.key(strNum, singleRegularNote));
            String type = typeScript(duration);
            result.append(pitch);
            result.append("<duration>" + duration + "</duration>\n" +
                    "<voice>1</voice>\n" +
                    type +
                    "<notations>\n" +
                    "<technical>\n" +
                    "<string>" + strNum + "</string>\n" +
                    "<fret>" + singleRegularNote + "</fret>\n" +
                    "</technical>\n" +
                    "</notations>\n" +
                    "</note>\n");
            eachNoteScript.add(result.toString());
        }
        else{
            StringBuilder result = new StringBuilder("");
            int singleRegularNote = Integer.valueOf(note.getNotation());
            int strNum = note.getStringNum() + 1;
            result.append("<note>\n");
            result.append("<chord/>\n");
            String pitch = NoteUtility.pitch(NoteUtility.octave(strNum, singleRegularNote), NoteUtility.key(strNum, singleRegularNote));
            String type = typeScript(duration);
            result.append(pitch);
            result.append("<duration>" + duration + "</duration>\n" +
                    "<voice>1</voice>\n" +
                    type +
                    "<notations>\n" +
                    "<technical>\n" +
                    "<string>" + strNum + "</string>\n" +
                    "<fret>" + singleRegularNote + "</fret>\n" +
                    "</technical>\n" +
                    "</notations>\n" +
                    "</note>\n");
            eachNoteScript.add(result.toString());
        }
    }

    private void putScripts(Notation note , int durationLen){
        int duration = (int) (((double)durationLen / (double)this.totalMeasureLength) * (double)totalDurationPerMeasure + 0.5);
        if(note.getNotation() == null){
            String type = typeScript(duration);
            String restNote ="<note>\n" +
                    "<rest/>\n" +
                    "<duration>" + duration + "</duration>\n" +
                    "<voice>1</voice>\n" +
                    type +
                    "</note>\n";
            eachNoteScript.add(restNote);
        }
        else if(note.getNotation().matches("[1-2]?[0-9]")){
            StringBuilder result = new StringBuilder("");
            int singleRegularNote = Integer.valueOf(note.getNotation());
            int strNum = note.getStringNum() + 1;
            result.append("<note>\n");
            String pitch = NoteUtility.pitch(NoteUtility.octave(strNum, singleRegularNote), NoteUtility.key(strNum, singleRegularNote));
            String type = typeScript(duration);
            result.append(pitch);
            result.append("<duration>" + duration + "</duration>\n" +
                    "<voice>1</voice>\n" +
                    type +
                    "<notations>\n" +
                    "<technical>\n" +
                    "<string>" + strNum + "</string>\n" +
                    "<fret>" + singleRegularNote + "</fret>\n" +
                    "</technical>\n" +
                    "</notations>\n" +
                    "</note>\n");
            eachNoteScript.add(result.toString());
        }
        else if(note.getNotation().matches("^[[0-9]*[phPH][0-9]*]*")){
            String[] connectedNotes = note.getNotation().split("[hHpP]");
            int midDuration = 1;
            duration = duration - connectedNotes.length - 1;
            if(duration < 1) {
                duration = 1;
            }
            for(int i = 0; i < connectedNotes.length; i++){
                StringBuilder result = new StringBuilder("");
                int eachNote = Integer.valueOf(connectedNotes[i]);
                int strNum = note.getStringNum() + 1;
                result.append("<note>\n");
                String pitch = NoteUtility.pitch(NoteUtility.octave(strNum, eachNote), NoteUtility.key(strNum, eachNote));
                result.append(pitch);
                if(i == 0){
                    String type = typeScript(midDuration);
                    result.append("<duration>" + midDuration + "</duration>\n" +
                            "<voice>1</voice>\n" +
                            type +
                            "<notations>\n" +
                            "<technical>\n" +
                            "<string>" + strNum + "</string>\n" +
                            "<fret>" + eachNote + "</fret>\n" +
                            "</technical>\n" +
                            "<slur number=\"1\" placement=\"above\" type=\"start\"/>\n" +
                            "</notations>\n" +
                            "</note>\n");
                    eachNoteScript.add(result.toString());
                }
                else if(i == connectedNotes.length - 1){
                    String type = typeScript(duration);
                    result.append("<duration>" + duration + "</duration>\n" +
                            "<voice>1</voice>\n" +
                            type +
                            "<notations>\n" +
                            "<technical>\n" +
                            "<string>" + strNum + "</string>\n" +
                            "<fret>" + eachNote + "</fret>\n" +
                            "</technical>\n" +
                            "<slur number=\"1\" type=\"stop\"/>\n" +
                            "</notations>\n" +
                            "</note>\n");
                    eachNoteScript.add(result.toString());
                }
                else{
                    String type = typeScript(midDuration);
                    result.append("<duration>" + midDuration + "</duration>\n" +
                            "<voice>1</voice>\n" +
                            type +
                            "<notations>\n" +
                            "<technical>\n" +
                            "<string>" + strNum + "</string>\n" +
                            "<fret>" + eachNote + "</fret>\n" +
                            "</technical>\n" +
                            "</notations>\n" +
                            "</note>\n");
                    eachNoteScript.add(result.toString());
                }
            }
        }

        else if(note.getNotation().matches("^[[0-9]*[/\\\\][0-9]*]*")){
            String[] slideNotes = note.getNotation().split("[/\\\\]");
            int midDuration = 1;
            duration = duration - slideNotes.length - 1;
            if(duration < 1) {
                duration = 1;
            }
            for(int i = 0; i < slideNotes.length; i++){
                StringBuilder result = new StringBuilder("");
                int eachNote = Integer.valueOf(slideNotes[i]);
                int strNum = note.getStringNum() + 1;
                result.append("<note>\n");
                String pitch = NoteUtility.pitch(NoteUtility.octave(strNum, eachNote), NoteUtility.key(strNum, eachNote));
                result.append(pitch);
                if(i == 0){
                    String type = typeScript(midDuration);
                    result.append("<duration>" + midDuration + "</duration>\n" +
                            "<voice>1</voice>\n" +
                            type +
                            "<notations>\n" +
                            "<technical>\n" +
                            "<string>" + strNum + "</string>\n" +
                            "<fret>" + eachNote + "</fret>\n" +
                            "</technical>\n" +
                            "<slide line-type=\"solid\" number=\"1\" type=\"start\"/>\n" +
                            "</notations>\n" +
                            "</note>\n");
                    eachNoteScript.add(result.toString());
                }
                else if(i == slideNotes.length - 1){
                    String type = typeScript(duration);
                    result.append("<duration>" + duration + "</duration>\n" +
                            "<voice>1</voice>\n" +
                            type +
                            "<notations>\n" +
                            "<technical>\n" +
                            "<string>" + strNum + "</string>\n" +
                            "<fret>" + eachNote + "</fret>\n" +
                            "</technical>\n" +
                            "<slide line-type=\"solid\" number=\"1\" type=\"stop\"/>\n" +
                            "</notations>\n" +
                            "</note>\n");
                    eachNoteScript.add(result.toString());
                }
                else {
                    String type = typeScript(midDuration);
                    result.append("<duration>" + midDuration + "</duration>\n" +
                            "<voice>1</voice>\n" +
                            type +
                            "<notations>\n" +
                            "<technical>\n" +
                            "<string>" + strNum + "</string>\n" +
                            "<fret>" + eachNote + "</fret>\n" +
                            "</technical>\n" +
                            "<slide line-type=\"solid\" number=\"1\" type=\"stop\"/>\n" +
                            "<slide line-type=\"solid\" number=\"1\" type=\"start\"/>\n" +
                            "</notations>\n" +
                            "</note>\n");
                    eachNoteScript.add(result.toString());
                }
            }
        }
    }

    private String typeScript(int duration){
        StringBuilder builder = new StringBuilder("<type>");
        if(duration == 1){
            builder.append("16th</type>\n");
        }
        else if(duration == 2){
            builder.append("eighth</type>\n");
        }
        else if(duration == 3){
            builder.append("eighth</type>\n");
            builder.append("<dot/>\n");
        }
        else if(duration == 4 || duration == 5){
            builder.append("quarter</type>\n");
        }
        else if(duration == 6 || duration == 7){
            builder.append("quarter</type>\n");
            builder.append("<dot/>\n");
        }
        else if(duration >= 8 && duration <= 11){
            builder.append("half</type>\n");
        }
        else if(duration >= 12 && duration <= 15){
            builder.append("half</type>\n");
            builder.append("<dot/>\n");
        }
        else{
            builder.append("whole</type>\n");
        }
        return builder.toString();
    }

}
