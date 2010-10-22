package simulationopinion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

/** 
 * @author Joris Berthelot (joris.berthelot@gmail.com)
 * @author Alexandre Coste
 */
public class Environment extends Thread {

    /**
     * Interval of millisecond to log the state of application
     */
    public final static int LOG_SAVE_INTERVAL = 200;
    /**
     * Number of agents in the environment
     */
    private int nbAgent;
    /**
     * Size of the environment
     */
    private int areaSize;
    /**
     * Agent allocation for each opinion level
     */
    private TreeMap<Integer, ArrayList<Agent>> listAgentsToOpinion;
    /**
     * Agent list
     */
    private ArrayList<Agent> listAgents;
    /**
     * Environment running state
     */
    private volatile boolean running;
    /**
     * LogManagement instance
     */
    private LogManagement logger;
    /**
     * SaveManagement instance
     */
    private SaveManagement saver;
    /**
     * View instance
     */
    private DisplayManagement view;

    /**
     * Constructor
     */
    public Environment() {
        this.nbAgent = 0;
        this.areaSize = 0;
        this.listAgentsToOpinion = new TreeMap<Integer, ArrayList<Agent>>();
        this.listAgents = new ArrayList<Agent>();
        this.running = false;

        for (int i = Agent.OPINION_MIN; i <= Agent.OPINION_MAX; i++) {
            this.listAgentsToOpinion.put(i, new ArrayList<Agent>());
        }
    }

    /**
     * Returns an agent list which are near to the given agent
     * @param agent
     * @return nearAgents
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
            if (a.getCoord().x() >= topLeft.x()
                    && a.getCoord().x() <= bottomRight.x()
                    && a.getCoord().y() >= topLeft.y()
                    && a.getCoord().y() <= bottomRight.y()
                    && !a.equals(agent)) {
                nearAgents.add(a);
                //  this.getListAgentsToOpinion().get(a.getOpinion()).remove(a);
            }
        }
        return nearAgents;
    }

    /**
     * Class runner (main thread)
     * Runs the environment, move agents
     * @param DisplayManagement d
     * FIXME Stop condition to break out the while()
     */
    @Override
    public void run() {
        try {
            this.running = true;
            
            if (2 > this.getNbAgent()) {
                throw new EnvironmentException("At least two agents are needed to make the environment running!");
            }

            long logtimer = System.currentTimeMillis() + Environment.LOG_SAVE_INTERVAL;

            this.view.update(this.getListAgentsToOpinion());

            if (null != this.getSaver()) {
                this.getSaver().save(String.valueOf(this.getAreaSize()));
                this.getSaver().saveAgent(this.getListAgents());
            }

            this.getLogger().saveData(this.getListAgentsToOpinion());

            while (this.running) {
                ArrayList<Agent> agents = this.getListAgents();
                Collections.shuffle(agents);

                if (System.currentTimeMillis() >= logtimer) {
                    this.getLogger().saveData(this.getListAgentsToOpinion());
                    logtimer = System.currentTimeMillis() + Environment.LOG_SAVE_INTERVAL;
                }

                for (Agent agent : agents) {
                    ArrayList<Agent> nearby = this.getNearAgents(agent);
                    Collections.shuffle(nearby);
                    
                    agent.move(this.getAreaSize());

                    if (null != this.getSaver()) {
                        this.getSaver().saveMove(agent);
                    }

                    for (Agent nearAgent : nearby) {
                        this.getListAgentsToOpinion().get(nearAgent.getOpinion()).remove(nearAgent);
                        agent.persuade(nearAgent);
                        
                        if (null != this.getSaver()) {
                            this.getSaver().savePersuade(nearAgent);
                        }

                        this.updateAgentAllocation(nearAgent);
                        this.view.update(this.getListAgentsToOpinion());
                        
                        if (!this.running) {
                            return;
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Stops the execution of the environment
     */
    public void kill() {
        this.running = false;
    }

    /**
     * Returns the number of working agent
     * @return nbAgent
     */
    public int getNbAgent() {
        return nbAgent;
    }

    /**
     * Returns the agent moving field size
     * @return areaSize
     */
    public int getAreaSize() {
        return areaSize;
    }

    /**
     * Sets the agent moving field size
     * @param areaSize
     */
    public void setAreaSize(int areaSize) {
        this.areaSize = areaSize;
    }

    /**
     * Returns the current agent allocation for each opinion level
     * @return listAgentsToOpinion
     */
    public TreeMap<Integer, ArrayList<Agent>> getListAgentsToOpinion() {
        return listAgentsToOpinion;
    }

    /**
     * Returns the current agent list
     * @return listAgents
     */
    public ArrayList<Agent> getListAgents() {
        return listAgents;
    }

    /**
     * Sets a new list of agents
     * @param listAgents
     */
    public void setListAgents(ArrayList<Agent> listAgents) {
        this.listAgents = listAgents;
        this.nbAgent = listAgents.size();

        for (Agent agent : listAgents) {
            ArrayList<Agent> opinionlist = this.listAgentsToOpinion.put(agent.getOpinion(), this.listAgentsToOpinion.get(agent.getOpinion()));
            opinionlist.add(agent);
        }
    }

    /**
     * Updates agent list
     * Agents contained in the list must be agent extracted agents from initial agent set
     * @param listAgents
     * @throws EnvironmentException
     */
    public void updateAgentAllocation(Agent agent) throws EnvironmentException {
        if (!this.getListAgents().contains(agent)) {
            throw new EnvironmentException("Unexpected agent found! Check out for any intruder! Agent: " + agent);
        }
        ArrayList<Agent> opinionlist = this.getListAgentsToOpinion().get(agent.getOpinion());
        opinionlist.add(agent);
    }

    /**
     * Returns the running state of Environment
     * @return
     */
    public boolean isRunning() {
        return this.running;
    }

    /**
     * Gets the log manager
     * @return
     */
    public LogManagement getLogger() {
        return this.logger;
    }

    /**
     * Sets the log manager
     * @param logger
     */
    public void setLogger(LogManagement logger) {
        this.logger = logger;
    }

    /**
     * Gets the save manager
     * @return
     */
    public SaveManagement getSaver() {
        return this.saver;
    }

    /**
     * Sets the save manager
     * @param saver
     */
    public void setSaver(SaveManagement saver) {
        this.saver = saver;
    }

    public DisplayManagement getView() {
        return this.view;
    }

    public void setView(DisplayManagement view) {
        this.view = view;
    }
}
