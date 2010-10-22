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
    public final static String LOG_FILENAME = "agent.log";
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
    public void saveData(TreeMap<Integer, ArrayList<Agent>> saveOpinion) throws IOException {
        
        FileWriter fw = new FileWriter(this.filename, true);
        for (Map.Entry<Integer, ArrayList<Agent>> e : saveOpinion.entrySet()) {
            //Write Key
            fw.write(e.getKey() + " ");
            //Write Value
            for (Agent agent : e.getValue()) {
                fw.write("_" + agent.getIdent());
            }
            fw.write("\n");
        }
        
        //Intialisation last line
            fw.append("=== next save ===");
        fw.write("\n");
        fw.close();
    }
}
