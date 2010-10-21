/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
 *
 * @author Alex
 */
public class SaveManagement {
    /**
     * simulation filename
     */
    private String filename;

    /**
     * constructor
     */
    public SaveManagement(String fileName){
        this.filename=fileName;
    }

    /*
     * @param filename
     */
    void load(String filename) throws FileNotFoundException, IOException, AgentException{
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
        for(int i= 0;i<contentSort[0].length()-6;i++){
            String[] agents = contentSort[i].split(";");
            a.add(new Agent(Integer.parseInt(agents[0]),
                    Integer.parseInt(agents[1]),
                    Integer.parseInt(agents[2]),
                    Integer.parseInt(agents[3]),
                    new Coord(Integer.parseInt(agents[4]),Integer.parseInt(agents[5]))
                    ));
        }
        e.setListAgents(a);

        /*action*/
        while ((line = buffer.readLine())!=null){
            String[] action = line.split(" ");
            ArrayList<Agent> agents = e.getListAgents();
            /*move*/
            if(action[0].equals("M")){                
                agents.get(Integer.parseInt(action[1])-1).setCoord(new Coord(Integer.parseInt(action[2]),Integer.parseInt(action[3])));
            }
            /*persuade*/
            if(action[0].equals("P")){
                agents.get(Integer.parseInt(action[1])-1).setOpinion(Integer.parseInt(action[2]));
            }
        }
        input.close();
    }

    /*
     * @param attribute
     */
    void save(String attribute) throws FileNotFoundException, IOException{
        FileWriter file = new FileWriter(this.getFilename(),true);
        BufferedWriter output = new BufferedWriter(file);
        output.write(attribute+"\n");
        output.flush();
        output.close();
    }

    /**
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @param filename the filename to set
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }
}
