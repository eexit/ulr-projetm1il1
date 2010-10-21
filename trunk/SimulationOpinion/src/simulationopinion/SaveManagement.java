package simulationopinion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @author Alexandre Coste
 * @review Joris Berthelot
 */
public class SaveManagement {

    /**
     * Save filename
     */
    private String filename;

    /**
     *      *
     * @param fileName
     */
    public SaveManagement(String fileName) {
        this.filename = fileName;
    }

    /**
     * Loads a saved application log
     * @param filename
     * @throws FileNotFoundException
     * @throws IOException
     * @throws AgentException
     */
    public void load(String filename) throws FileNotFoundException, IOException, AgentException {
        FileInputStream file = new FileInputStream(filename);
        InputStreamReader input = new InputStreamReader(file);
        BufferedReader buffer = new BufferedReader(input);

        String line;
        String[] contentSort = null;
        ArrayList<Agent> a = new ArrayList<Agent>();

        /*Environment areaSize*/
        line = buffer.readLine();
        Environment e = new Environment();
        e.setAreaSize(Integer.parseInt(line));

        /*Agent*/
        line = buffer.readLine();
        contentSort = line.split(" ");
        for (int i = 0; i < contentSort[0].length() - 6; i++) {
            String[] agents = contentSort[0].split(";");
            a.add(new Agent(Integer.parseInt(agents[0]),
                    Integer.parseInt(agents[1]),
                    Integer.parseInt(agents[2]),
                    Integer.parseInt(agents[3]),
                    new Coord(Integer.parseInt(agents[4]), Integer.parseInt(agents[5]))));
        }
        e.setListAgents(a);

        /*action*/
        while ((line = buffer.readLine()) != null) {
            String[] action = line.split(" ");
            ArrayList<Agent> agents = e.getListAgents();
            // TODO replace by a switch
            /*move*/
            switch ((action[0].charAt(0))) {
                /*move*/
                case 'M':
                    agents.get(Integer.parseInt(action[1]) - 1).setCoord(new Coord(Integer.parseInt(action[2]), Integer.parseInt(action[3])));
                    break;
                /*persuade*/
                case 'P':
                    agents.get(Integer.parseInt(action[1]) - 1).setOpinion(Integer.parseInt(action[2]));
                    break;
                default:
                    break;
            }
        }
        input.close();
    }

    /**
     * Saves data into the file (append mod)
     * @param attribute
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void save(String attribute) throws FileNotFoundException, IOException {
        FileWriter file = new FileWriter(this.getFilename(), true);
        BufferedWriter output = new BufferedWriter(file);
        output.write(attribute + "\n");
        output.flush();
        output.close();
    }

    /**
     * Formats extracted data from agent list and saves it
     * @param a
     * @throws IOException
     */
    public void saveAgent(ArrayList<Agent> a) throws IOException {
        String listAgent = new String();
        for (int i = 0; i < a.size(); i++) {
            listAgent += a.get(i).getTrustLevel() + ";"
                + a.get(i).getMoveStep() + ";"
                + a.get(i).getPerceptionDepth() + ";"
                + a.get(i).getWaitTime() + ";"
                + a.get(i).getCoord().x() + ";"
                + a.get(i).getCoord().y() + " ";
        }
        this.save(listAgent);
    }

    /**
     * Saves agent move data
     * @param a
     * @throws IOException
     */
    public void saveMove(Agent a) throws IOException {
        String move = "M ";
        move += a.getIdent() + " "
                + a.getCoord().x() + " "
                + a.getCoord().y();
        this.save(move);
    }

    /**
     * Saves agent persuasion value
     * @param a
     * @throws IOException
     */
    public void savePersuade(Agent a) throws IOException {
        String persuade = "P ";
        persuade += a.getIdent() + " "
                + a.getOpinion();
        this.save(persuade);
    }

    /**
     * Gets the filename
     * @return
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Sets the filename
     * @param filename
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }
}
