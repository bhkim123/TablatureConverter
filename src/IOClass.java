import java.io.*;
import java.util.ArrayList;

public class IOClass {
    public static ArrayList<String> textToStr(String path) throws IOException {
        File file = new File(path);
        FileReader fr = new FileReader(file);
        BufferedReader bufr = new BufferedReader(fr);
        ArrayList<String> lines = new ArrayList<>();
        String line = "";
        while((line = bufr.readLine()) != null){
            if(line.trim() != null && line.trim().length() > 0){
                lines.add(line.trim());
            }
        }
        return lines;
    }
}
