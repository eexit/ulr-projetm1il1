package simulationopinion;

import java.util.ArrayList;
import java.util.Collections;
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
     * Agent allocation for each opinion level
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
            if (a.getCoord().x() >= topLeft.x() &&
                    a.getCoord().x() <= bottomRight.x() &&
                    a.getCoord().y() >= topLeft.y() &&
                    a.getCoord().y() <= bottomRight.y() &&
                    !a.equals(agent)) {
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
    public void run(DisplayManagement display) throws EnvironmentException {
        try {
            if (2 > this.getNbAgent()) {
                throw new EnvironmentException("Two agent at least are needed to make the environment running!");
            }
            this.running = true;

            while (this.isRunning()) {
                ArrayList<Agent> agents = this.getListAgents();
                Collections.shuffle(agents);
                for (Agent agent : agents) {
                    ArrayList<Agent> nearby = this.getNearAgents(agent);
                    Collections.shuffle(nearby);
                    agent.move(this.getAreaSize());
                    for (Agent nearAgent : nearby) {
                        this.getListAgentsToOpinion().get(nearAgent.getOpinion()).remove(nearAgent);
                        agent.persuade(nearAgent);
                        this.updateAgentAllocation(nearAgent);
                        display.update(this.getListAgentsToOpinion());
                        Thread.sleep(50);
                    }
                }
            }
        } catch (AgentException e) {
        } catch (InterruptedException e) {
        }
    }

    /**
     * Stop the execution of the environment
     */
    public void stop() {
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
     * Returns the state of Environment
     * @return the running
     */
    public boolean isRunning() {
        return running;
    }
}