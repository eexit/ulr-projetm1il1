package simulationopinion;

import java.util.ArrayList;
import java.util.TreeMap;
/** 
 * @author Joris Berthelot (joris.berthelot@gmail.com)
 * @author Alexandre Coste
 */
public class Environment {

    /**
     * Number of agents in the environment
     */
    private int nbAgent;

    /**
     * Size of the environment
     */
    private int areaSize;

    /**
     * Amount of agent per opinion
     */
    private TreeMap<Integer, ArrayList<Agent>> listAgentsToOpinion;

    /**
     * Agent list
     */
    private ArrayList<Agent> listAgents;

    private boolean running;

    /**
     * Constructor
     */
    public Environment() {
        this.nbAgent = 0;
        this.areaSize = 0;
        this.listAgentsToOpinion = new TreeMap<Integer, ArrayList<Agent>>();
        this.listAgents = new ArrayList<Agent>();
        this.running = false;

        for (int i = 0; i < Agent.OPINION_MAX; i++) {
            this.listAgentsToOpinion.put(i, new ArrayList<Agent>());
        }
    }

    /**
     * Gets a list of agent near to given agent
     * @param agent
     * @return
     */
    public ArrayList<Agent> getNearAgents(Agent agent) {
        ArrayList<Agent> nearAgents = new ArrayList<Agent>();

        Coord topLeft = new Coord(agent.getCoord().x() - agent.getPerceptionDepth(), agent.getCoord().y() - agent.getPerceptionDepth());
        Coord bottomRight = new Coord(agent.getCoord().x() + agent.getPerceptionDepth(), agent.getCoord().y() + agent.getPerceptionDepth());
        
        // Checks topLeft x doesn't underflow
        if (0 > topLeft.x()) {
            topLeft.setX(0);
        }

        // Checks topLeft x doesn't overflow
        if (this.getAreaSize() <= topLeft.x()) {
            topLeft.setX(this.getAreaSize() - 1);
        }

        // Checks topLeft y doesn't underflow
        if (0 > topLeft.y()) {
            topLeft.setY(0);
        }

        // Checks topLeft y doesn't overflow
        if (this.getAreaSize() <= topLeft.y()) {
            topLeft.setY(this.getAreaSize() - 1);
        }

        // Checks bottomRight x doesn't underflow
        if (0 > bottomRight.x()) {
            bottomRight.setX(0);
        }

        // Checks bottomRight x doesn't overflow
        if (this.getAreaSize() <= bottomRight.x()) {
            bottomRight.setX(this.getAreaSize() - 1);
        }

        // Checks bottomRight y doesn't underflow
        if (0 > bottomRight.y()) {
            bottomRight.setY(0);
        }

        // Checks bottomRight y doesn't overflow
        if (this.getAreaSize() <= bottomRight.y()) {
            bottomRight.setY(this.getAreaSize() - 1);
        }

        // Looks for nearby agents
        for (Agent a : this.getListAgents()) {
            if (a.getCoord().x() >= topLeft.x() &&
                a.getCoord().x() <= bottomRight.x() &&
                a.getCoord().y() >= topLeft.y() &&
                a.getCoord().y() <= bottomRight.y() &&
                !a.equals(agent)) {
                nearAgents.add(a);
                // this.getListAgentsToOpinion().get(a.getOpinion()).remove(a);
            }
        }
        return nearAgents;
    }

    /**
     * Runs the environment, move agents
     */
    public void run() throws EnvironmentException {
        if (1 > this.getNbAgent()) {
            throw new EnvironmentException("Can't run environment without any agent...");
        }
        
        this.running = true;

        while (this.isRunning()) {
            for (Agent agent : this.getListAgents()) {
                ArrayList<Agent> nearby = this.getNearAgents(agent);
                // agent.move();
                
                // agent.persuade(this.getNearAgents(agent));
                
            }
        }
    }

    /**
     * @return the nbAgent
     */
    public int getNbAgent() {
        return nbAgent;
    }

    /**
     * @return the areaSize
     */
    public int getAreaSize() {
        return areaSize;
    }

    /**
     * @param areaSize the areaSize to set
     */
    public void setAreaSize(int areaSize) {
        this.areaSize = areaSize;
    }

    /**
     * @return the listAgentsToOpinion
     */
    public TreeMap<Integer, ArrayList<Agent>> getListAgentsToOpinion() {
        return listAgentsToOpinion;
    }

    /**
     * @return the listAgents
     */
    public ArrayList<Agent> getListAgents() {
        return listAgents;
    }

    /**
     * @param listAgents the listAgents to set
     */
    public void setListAgents(ArrayList<Agent> listAgents) {
        this.listAgents = listAgents;
        this.nbAgent = listAgents.size();

        for (Agent agent : listAgents) {
            ArrayList<Agent> list = this.listAgentsToOpinion.put(agent.getOpinion(), this.listAgentsToOpinion.get(agent.getOpinion()));
            list.add(agent);
        }
    }

    /**
     * @return the running
     */
    public boolean isRunning() {
        return running;
    }
}
