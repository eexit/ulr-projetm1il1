package simulationopinion;

import java.util.Random;

/**
 * @author claatik
 * @author Joris Berthelot (joris.berthelot@gmail.com)
 * @version 1.00
 */
public class Agent {
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
    public final static int TRUST_MIN = 0;

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
     * Constructor
     */
    public Agent() {
        this.opinion = 0;
        this.waitTime = 0;
        this.trustLevel = 0;
        this.perceptionDepth = 0;
        this.moveStep = 0;
        this.coord = new Coord();

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
            this.setOpinion(new Random().nextInt(Agent.OPINION_MAX));
            this.setTrustLevel(trustLevel);
            this.perceptionDepth = perceptionDepth;
            this.moveStep = moveStep;
            this.waitTime = waitTime;
            this.coord = c;
        } catch (AgentException e) {
            throw e;
        }
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
}
