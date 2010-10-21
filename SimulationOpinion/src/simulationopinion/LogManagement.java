package simulationopinion;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Teddie
 * @author Chama
 * @review Joris Berthelot
 */
public class LogManagement {

    /**
     * Default log filename
     */
    public final static String LOG_FILENAME = "opinion_log";
    /**
     * Log filename
     */
    private File filename;

    /**
     * Constructor
     * @throws IOException
     */
    public LogManagement() throws IOException {
        this.filename = new File(LogManagement.LOG_FILENAME);
        if (this.filename.exists()) {
            this.filename.delete();
        }
        this.filename.createNewFile();
    }

    /**
     * Log data saver
     * @param saveOpinion
     * @throws IOException
     */
    public void saveData(TreeMap<Integer, ArrayList> saveOpinion) throws IOException {
        int max = 0;
        FileWriter fw = new FileWriter(this.filename, true);
        for (Map.Entry<Integer, ArrayList> e : saveOpinion.entrySet()) {
            //Write Key
            max = Math.max(max, e.getValue().size());
            fw.write(e.getKey() + " ");
            //Write Value
            for (Object o : e.getValue()) {
                Agent a = (Agent) o;
                fw.write("_" + a.getIdent());
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
