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

    public static boolean isFileStored(String outputPathName, String allScripts) throws IOException {

        try{
            BufferedWriter fw = new BufferedWriter(new FileWriter(outputPathName, true));

            fw.write(allScripts);
            fw.flush();

            fw.close();
        }
        catch (Exception e){
            System.out.println("The file was not created. Check path and file name.\n");
            return false;
        }

        return true;
    }
}
