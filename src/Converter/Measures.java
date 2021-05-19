package Converter;

import java.util.ArrayList;

public class Measures {
    protected final int STRING_NUM = 6; //number of guitar string
    protected String measureLines; //total measue lines
    protected ArrayList<String> scriptsPerMeasure;//each measure string
    protected ArrayList<Notes> eachMeasrueNotes; //notes per each measure
    protected String[][] lines2Darr; // measure string to 2D array box

    public Measures(String lines){
        measureLines = lines;
        scriptsPerMeasure = new ArrayList<>();
        eachMeasrueNotes = new ArrayList<>();

        ArrayList<String> storedLines = splitByLines(measureLines);
        this.lines2Darr = make2Darr(storedLines);
        setEachMeasureNotes();

        for(Notes o : eachMeasrueNotes){
            ArrayList<String> temp = o.getCompletedNoteScript();
            for(int i = 0; i < temp.size(); i++){
                String noteScript = temp.get(i);
                if(i == 0){
                    addAttribute(noteScript);
                }
                addMeasureNum(noteScript, i + 1);
                scriptsPerMeasure.add(noteScript);
            }
        }
    }

    // split string by each line
    private ArrayList<String> splitByLines(String measureInfo){
        ArrayList<String> storedLines = new ArrayList<>();
        String[] split = measureInfo.split("\n");
        for(String str : split){
            if(!str.equals(" ") && !str.equals("")){
                str = str.trim();
                storedLines.add(str);
            }
        }
        return storedLines;
    }

    // make a 2D array look like
    private String[][] make2Darr(ArrayList<String> storedLines){
        String[][] linesByMeasure = new String[STRING_NUM][];
        for(int i = 0; i < linesByMeasure.length; i++){
            String[] split = storedLines.get(i).split("[|]");
            ArrayList<String> temp = new ArrayList<>();
            for(String str : split){
                if(!str.equals("") && !str.equals(" ") && str.contains("-")){
                    str = str.trim();
                    temp.add(str);
                }
            }
            linesByMeasure[i] = new String[temp.size()];
            for(int j = 0; j < temp.size(); j++){
                linesByMeasure[i][j] = temp.get(j);
            }
        }
        return linesByMeasure;
    }

    private void setEachMeasureNotes(){
        int totalMeasureNum = lines2Darr[0].length;
        for(int i = 0; i < totalMeasureNum; i++){
            ArrayList<String> eachMeasure = new ArrayList<>();
            for(int j = 0; j < STRING_NUM; j++){
                eachMeasure.add(lines2Darr[j][i]);
            }
            Notes notes = new Notes(eachMeasure);
            this.eachMeasrueNotes.add(notes);
        }
    }

    public ArrayList<String> getScriptsPerMeasure(){
        return scriptsPerMeasure;
    }

    private void addAttribute(String noteScript){
        noteScript = "<attributes>\n" +
                "<divisions>2</divisions>\n" +
                "<key>\n" +
                "<fifths>0</fifths>\n" +
                "</key>\n" +
                "<time>\n" +
                "<beats>4</beats>\n" +
                "<beat-type>4</beat-type>\n" +
                "</time>\n" +
                "<clef>\n" +
                "<sign>TAB</sign>\n" +
                "<line>5</line>\n" +
                "</clef>\n" +
                "<staff-details>\n" +
                "<staff-lines>6</staff-lines>\n" +
                "<staff-tuning line=\"1\">\n" +
                "<tuning-step>E</tuning-step>\n" +
                "<tuning-octave>2</tuning-octave>\n" +
                "</staff-tuning>\n" +
                "<staff-tuning line=\"2\">\n" +
                "<tuning-step>A</tuning-step>\n" +
                "<tuning-octave>2</tuning-octave>\n" +
                "</staff-tuning>\n" +
                "<staff-tuning line=\"3\">\n" +
                "<tuning-step>D</tuning-step>\n" +
                "<tuning-octave>3</tuning-octave>\n" +
                "</staff-tuning>\n" +
                "<staff-tuning line=\"4\">\n" +
                "<tuning-step>G</tuning-step>\n" +
                "<tuning-octave>3</tuning-octave>\n" +
                "</staff-tuning>\n" +
                "<staff-tuning line=\"5\">\n" +
                "<tuning-step>B</tuning-step>\n" +
                "<tuning-octave>3</tuning-octave>\n" +
                "</staff-tuning>\n" +
                "<staff-tuning line=\"6\">\n" +
                "<tuning-step>E</tuning-step>\n" +
                "<tuning-octave>4</tuning-octave>\n" +
                "</staff-tuning>\n" +
                "</staff-details>\n" +
                "</attributes>\n" + noteScript;
    }

    private void addMeasureNum(String noteScript, int measureNum){
        noteScript = "<measure number=\"" + measureNum + "\">\n" + noteScript + "</measure>\n";
    }
}
