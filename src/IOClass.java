import java.io.*;
import java.util.ArrayList;

public class IOClass {

    //Text file information to string information to convert.
    public static ArrayList<String> textToStr(String path) throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        try{
            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader bufr = new BufferedReader(fr);
            String line = "";
            while((line = bufr.readLine()) != null){
                if(line.trim() != null && line.trim().length() > 0){
                    lines.add(line.trim());
                }
            }
            return lines;
        }
        catch (Exception e){
            System.out.println("The path is wrong or the input file does not exist. Try again.\n");
            return null;
        }
    }

    // Check if the path to save file is proper.
    public static boolean isValid(String outputPathName, String allScripts){
        try{
            File file = new File(outputPathName);
            FileReader fr = new FileReader(file);
            System.out.println("The file was not created. There is a file that has the same name. Try again.\n");
            return false;
        }
        catch (Exception ex){
            try{
                BufferedWriter fw = new BufferedWriter(new FileWriter(outputPathName, true));

                fw.write(allScripts);
                fw.flush();

                fw.close();
                return true;
            }
            catch (Exception e){
                System.out.println("The file was not created. The path is wrong. Try again.\n");
                return false;
            }
        }
    }

}
