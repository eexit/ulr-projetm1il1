/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulationopinion;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Teddie
 * @author Chama
 */
public class LogManagement {

    public File filename;

    //Constructeur
    public LogManagement() {
        try {
            //Create File
            filename = new File("log");
            if (filename.exists()) {
                filename.delete();
                filename.createNewFile();
            } else {
                filename.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * @author chama
     * @author Teddie
     **/
    public void saveData(TreeMap<Integer, ArrayList> saveOpinion) throws IOException {
        int max = 0;
        FileWriter fw = new FileWriter(filename, true);
        for (Map.Entry<Integer, ArrayList> e : saveOpinion.entrySet()) {
            //Write Key
            max = Math.max(max, e.getValue().size());
            fw.write(e.getKey() + " ");
            //Write Value
            for (Object o : e.getValue()) {
                Agent a = (Agent) o;
                fw.write("_"+ a.getIdent());
            }
            fw.write("\n");
        }
        //Intialisation last line
        max = max + 2;
        for (int i = 0; i <= max; i++) {
            fw.append("_");
        }
        fw.write("\n");
        fw.close();
    }

   
}
