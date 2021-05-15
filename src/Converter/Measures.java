package Converter;

import java.util.ArrayList;

public class Measures {
    protected final int STRING_NUM = 6; //number of guitar string
    protected String measureLines; //total measue lines
    protected ArrayList<String> scriptsPerMeasrue;//each measure string
    protected ArrayList<Notes> eachMeasrueNotes; //notes per each measure
    protected String[][] lines2Darr; // measure string to 2D array box
    protected StringBuilder resultXML;

    public Measures(String lines){
        measureLines = lines;
        scriptsPerMeasrue = new ArrayList<>();
        eachMeasrueNotes = new ArrayList<>();

        ArrayList<String> storedLines = splitByLines(measureLines);
        this.lines2Darr = make2Darr(storedLines);

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
}
