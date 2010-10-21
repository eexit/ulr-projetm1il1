/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulationopinion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
                fw.write("_" + a.getOpinion());//Mettre getIdent
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

    /**
     * @author Teddie
     * @author Chama
     * @return TreeMap<Integer, ArrayList>
     *
     */
    /*public TreeMap<Integer, ArrayList> readData() {
        TreeMap<Integer, ArrayList> readOpinion = new TreeMap<Integer, ArrayList>();
        try {

            InputStream ips = new FileInputStream(filename);
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String ligne;
            // Line by Line
            while ((ligne = br.readLine()) != null) {
                ligne = br.readLine();
                ArrayList l = new ArrayList();
                String[] skey = ligne.split(" ");
                // Get Key
                int key = Integer.parseInt(skey[0]);

                // Create list value
                String[] svalue = ligne.split("_");

                for (int i = 1; i < svalue.length; i++) {
                    int value = Integer.parseInt(svalue[i]);
                    l.add(value);
                }
                readOpinion.put(key, l);

            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return readOpinion;
    }*/
}
