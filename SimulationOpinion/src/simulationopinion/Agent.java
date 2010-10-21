package simulationopinion;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * @author claatik
 * @author Joris Berthelot (joris.berthelot@gmail.com)
 * @version 1.00
 */
public class Agent implements Comparable {

    /**
     * Mininal value of opinion
     */
    public final static int OPINION_MIN = 0;
    /**
     * Maximal value of opinion
     */
    public final static int OPINION_MAX = 9;
    /**
     * Minimal value of trust level
     */
    public final static int TRUST_MIN = 1;
    /**
     * Maximal value of trust level
     */
    public final static int TRUST_MAX = 5;
    /**
     * Minimal value of move step
     */
    public final static int MOVE_STEP_MIN = 1;
    /**
     * Maximal value of move step
     */
    public final static int MOVE_STEP_MAX = 2;
    /**
     * The total number of components.
     * Initialized to 0, it is incremented by the constructor,
     * and used to inialize the identifier.
     */
    protected static int count = 0;
    /**
     * Unique identifier of agent
     */
    private final int ident;
    /**
     * Agent opinion value
     */
    private int opinion;
    /**
     * Agent wait time before being back active
     */
    private int waitTime;
    /**
     * Agent trust level
     */
    private int trustLevel;
    /**
     * Agent perception depth
     */
    private int perceptionDepth;
    /**
     * Agent moving step
     */
    private int moveStep;
    /**
     * Agent current coord
     */
    private Coord coord;
    /**
     * List of agents doesn't speak with the time off
     */
    private TreeMap<Agent, Long> listAgentNotSpeak;

    /**
     * Constructor
     */
    public Agent() {
        this.opinion = new Random().nextInt(Agent.OPINION_MAX + 1);
        this.waitTime = 0;
        this.trustLevel = Agent.TRUST_MIN;
        this.perceptionDepth = 1;
        this.moveStep = Agent.MOVE_STEP_MIN;
        this.coord = new Coord();
        this.listAgentNotSpeak = new TreeMap<Agent, Long>();
        this.ident = ++count;
    }

    /** Constructor with parameters
     * @param trustLevel
     * @param moveStep
     * @param perceptionDepth
     * @param waitTime
     * @param c
     * @throws AgentException
     */
    public Agent(int trustLevel, int moveStep, int perceptionDepth, int waitTime, Coord c) throws AgentException {
        this();
        try {
            this.opinion = new Random().nextInt(Agent.OPINION_MAX + 1);
            this.setTrustLevel(trustLevel);
            this.perceptionDepth = perceptionDepth;
            this.moveStep = moveStep;
            this.waitTime = waitTime;
            this.coord = c;
            this.listAgentNotSpeak = new TreeMap<Agent, Long>();
        } catch (AgentException e) {
            throw e;
        }
    }

    public void move(int areaSize) throws AgentException {
        if (areaSize <= 0) {
            throw new AgentException("AreaSize must be positive and not equal to zero");
        }
        ArrayList<Coord> listMovePossible = new ArrayList<Coord>();
        if ((this.getCoord().x() - this.getMoveStep()) > 0) {
            listMovePossible.add(new Coord(this.getCoord().x() - this.getMoveStep(), this.getCoord().y())); //Déplacement vers le haut
            if ((this.getCoord().y() - this.getMoveStep()) > 0) {
                listMovePossible.add(new Coord(this.getCoord().x() - this.getMoveStep(), this.getCoord().y() - this.getMoveStep())); //Déplacement vers le haut gauche
            }
            if ((this.getCoord().y() + this.getMoveStep()) < areaSize) {
                listMovePossible.add(new Coord(this.getCoord().x() - this.getMoveStep(), this.getCoord().y() + this.getMoveStep())); // Déplacement vers le haut droit
            }
        }
        if ((this.getCoord().x() + this.getMoveStep()) < areaSize) {
            listMovePossible.add(new Coord(this.getCoord().x() + this.getMoveStep(), this.getCoord().y())); //Déplacement vers le bas
            if ((this.getCoord().y() - this.getMoveStep()) > 0) {
                listMovePossible.add(new Coord(this.getCoord().x() + this.getMoveStep(), this.getCoord().y() - this.getMoveStep())); //Déplacement vers le bas gauche
            }
            if ((this.getCoord().y() + this.getMoveStep()) < areaSize) {
                listMovePossible.add(new Coord(this.getCoord().x() + this.getMoveStep(), this.getCoord().y() + this.getMoveStep())); //Déplacement vers le bas droit
            }
        }
        if ((this.getCoord().y() - this.getMoveStep()) > 0) {
            listMovePossible.add(new Coord(this.getCoord().x(), this.getCoord().y() - this.getMoveStep())); //Déplacement vers la gauche
        }
        if ((this.getCoord().y() + this.getMoveStep()) < areaSize) {
            listMovePossible.add(new Coord(this.getCoord().x(), this.getCoord().y() + this.getMoveStep())); //Déplacement vers la droite
        }

        int direction = new Random().nextInt(listMovePossible.size());
        this.setCoord(listMovePossible.get(direction));
    }

    public void persuade(Agent agent) throws AgentException {
        TreeMap<Agent, Long> mutedAgents = new TreeMap<Agent, Long>(this.listAgentNotSpeak);
        for (Map.Entry<Agent, Long> e : this.listAgentNotSpeak.entrySet()) {
            if (e.getValue() > System.currentTimeMillis()) {
                mutedAgents.remove(e.getKey());
            }
        }

        this.listAgentNotSpeak = mutedAgents;
        
        if (agent.getOpinion() - agent.getTrustLevel() <= this.getOpinion() && agent.getOpinion() + agent.getTrustLevel() >= this.getOpinion() && !this.listAgentNotSpeak.containsKey(agent)) {
            agent.setOpinion(this.getOpinion());
            long waitOffTime = System.currentTimeMillis() + (this.getWaitTime() * 1000);
            this.addToListAgentNotSpeak(agent, waitOffTime);
            agent.addToListAgentNotSpeak(this, waitOffTime);
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    public void addToListAgentNotSpeak(Agent a, long waitOffTime) {
        this.listAgentNotSpeak.put(a, waitOffTime);
    }

    /**
     * @return the opinion
     * @throws AgentException
     */
    public int getOpinion() {
        return this.opinion;
    }

    /**
     * @param opinion the opinion to set
     * @throws AgentException
     */
    public void setOpinion(int opinion) throws AgentException {
        if (Agent.OPINION_MIN > opinion || opinion > Agent.OPINION_MAX) {
            throw new AgentException("Opinion must has a value between " + Agent.OPINION_MIN + " and " + Agent.OPINION_MAX + " (inclusive)!");
        }
        this.opinion = opinion;
    }

    /**
     * @return the waitTime
     */
    public int getWaitTime() {
        return this.waitTime;
    }

    /**
     * @param waitTime the waitTime to set
     */
    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    /**
     * @return the trustLevel
     */
    public int getTrustLevel() {
        return this.trustLevel;
    }

    /**
     * @param trustLevel the trustLevel to set
     * @throws AgentException
     */
    public void setTrustLevel(int trustLevel) throws AgentException {
        if (Agent.TRUST_MIN > trustLevel || trustLevel > Agent.TRUST_MAX) {
            throw new AgentException("Trust Level must has a value between " + Agent.TRUST_MIN + " and " + Agent.TRUST_MAX + " (inclusive)!");
        }
        this.trustLevel = trustLevel;
    }

    /**
     * @return the perceptionDepth
     */
    public int getPerceptionDepth() {
        return this.perceptionDepth;
    }

    /**
     * @param perceptionDepth the perceptionDepth to set
     */
    public void setPerceptionDepth(int perceptionDepth) {
        this.perceptionDepth = perceptionDepth;
    }

    /**
     * @return the moveStep
     */
    public int getMoveStep() {
        return this.moveStep;
    }

    /**
     * @param moveStep the moveStep to set
     * @throws AgentException
     */
    public void setMoveStep(int moveStep) throws AgentException {
        if (Agent.MOVE_STEP_MIN > moveStep || moveStep > Agent.MOVE_STEP_MAX) {
            throw new AgentException("Perception Depth must has a value between " + Agent.MOVE_STEP_MIN + " and " + Agent.MOVE_STEP_MAX + " (inclusive)!");
        }
        this.moveStep = moveStep;
    }

    /**
     * @return the coord
     */
    public Coord getCoord() {
        return this.coord;
    }

    /**
     * @param c the coord to set
     */
    public void setCoord(Coord c) {
        this.coord = c;
    }

    /**
     * @return the ident
     */
    public int getIdent() {
        return ident;
    }

    @Override
    public int compareTo(Object o) {
        Agent a = (Agent) o;
        if (this.getIdent() < a.getIdent()) {
            return -1;
        } else if (this.getIdent() > a.getIdent()) {
            return 1;
        } else {
            return 0;
        }
    }
}
