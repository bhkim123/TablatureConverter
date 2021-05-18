package Converter;

public class NoteUtility {
    public static int octave(int stringNumber, int fret) {
        int octave;
        if(stringNumber == 6) {
            if(fret >= 0 && fret <= 7) {
                octave = 2;
            }
            else if(fret >=8 && fret <= 19){
                octave = 3;
            }
            else{
                octave = 4;
            }
        }
        else if(stringNumber == 5) {
            if(fret >= 0 && fret <= 2) {
                octave = 2;
            }
            else if(fret >= 3 && fret <= 14) {
                octave = 3;
            }
            else {
                octave = 4;
            }
        }
        else if(stringNumber == 4) {
            if(fret >=0 && fret <= 9) {
                octave = 3;
            }
            else if(fret >= 10 && fret <= 21){
                octave = 4;
            }
            else{
                octave = 5;
            }
        }
        else if(stringNumber == 3) {
            if(fret >= 0 && fret <= 4) {
                octave = 3;
            }
            else if(fret >= 5 && fret <= 16) {
                octave = 4;
            }
            else {
                octave = 5;
            }
        }
        else if(stringNumber == 2) {
            if(fret == 0) {
                octave = 3;
            }
            else if(fret >= 1 && fret <= 12) {
                octave = 4;
            }
            else {
                octave = 5;
            }
        }
        else {
            if(fret >= 0 && fret <= 7) {
                octave = 4;
            }
            else if(fret >= 8 && fret <= 19){
                octave = 5;
            }
            else{
                octave = 6;
            }
        }
        return octave;
    }

    public static String key(int stringNumber, int fret) {
        String[] keys = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};

        if(stringNumber == 6) {
            return keys[(fret + 4) % 12];
        }
        else if(stringNumber == 5) {
            return keys[(fret + 9) % 12];
        }
        else if(stringNumber == 4) {
            return keys[(fret + 2) % 12];
        }
        else if(stringNumber == 3) {
            return keys[(fret + 7) % 12];
        }
        else if(stringNumber == 2) {
            return keys[(fret + 11) % 12];
        }
        else {
            return keys[(fret + 4) % 12];
        }
    }

    public static String pitch(int octave, String key) {

        String octaveString = "<octave>" + octave + "</octave>\n";
        String stepString;
        if(!key.contains("#")) {
            stepString = "<step>" + key + "</step>\n";
        }
        else {
            stepString = "<step>" + key.charAt(0) + "</step>\n"
                    + "<alter>" + 1 + "</alter>\n";
            //In musicxml, # is expressed as <alter>1</alter>
        }

        return "<pitch>\n"
                + stepString
                + octaveString
                + "</pitch>\n";
    }
}
