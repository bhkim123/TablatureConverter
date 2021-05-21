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
        this.scriptsPerMeasure = new ArrayList<>();

        ArrayList<String> storedLines = splitByLines(this.measureInfo);
        this.lines2Darr = make2Darr(storedLines);
        setEachMeasureNotes();
        for(int j = 0; j < eachMeasureNotes.size(); j++) {
            ArrayList<String> temp = eachMeasureNotes.get(j).getCompletedNoteScript();
            String script = "";
            for(int i = 0; i < temp.size(); i++){
                script += temp.get(i);
            }
            if(j == 0){
                script = addAttribute(script);
            }
            script = addMeasureNum(script, j + 1);
            scriptsPerMeasure.add(script);
        }

    }

    public ArrayList<String> getScriptsPerMeasrue(){
        return this.scriptsPerMeasure;
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
        for(String a: storedLines)
            System.out.println(a+"??");
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

    private String addAttribute(String noteScript){
        String attribute = "<attributes>\n" +
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
                "</attributes>\n";
        return attribute + noteScript;
    }

    private String addMeasureNum(String noteScript, int measureNum){
        return "<measure number=\"" + measureNum + "\">\n" + noteScript + "</measure>\n";
    }

    private String addPartAttribution(String script){
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<!DOCTYPE score-partwise PUBLIC \"-//Recordare//DTD MusicXML 3.1 Partwise//EN\" \"http://www.musicxml.org/dtds/partwise.dtd\">\n" +
                "<score-partwise version=\"3.1\">\n" +
                "<part-list>\n" +
                "<score-part id=\"P1\">\n" +
                "<part-name>Classical Guitar</part-name>\n" +
                "</score-part>\n" +
                "</part-list>\n" +
                "<part id=\"P1\">\n" + script + "</part>\n" + "</score-partwise>\n";
    }
}
