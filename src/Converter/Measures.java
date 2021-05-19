package Converter;

import java.util.ArrayList;

public class Measures {
    protected final int STRING_NUM = 6;
    protected String measureInfo;
    protected String[][] lines2Darr;
    protected ArrayList<Notes> eachMeasureNotes;
    protected ArrayList<String> scriptsPerMeasure;

    public Measures(String measureInfo){
        this.measureInfo = measureInfo;
        this.eachMeasureNotes = new ArrayList<>();
        this.scriptsPerMeasure = new ArrayList<String>();

        ArrayList<String> storedLines = splitByLines(this.measureInfo);
        this.lines2Darr = make2Darr(storedLines);
        setEachMeasureNotes();
        for(Notes o : eachMeasureNotes) {
            ArrayList<String> temp = o.getCompletedNoteScript();
            for(String script : temp){
                scriptsPerMeasure.add(script);
            }
        }
    }

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
            this.eachMeasureNotes.add(notes);
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
