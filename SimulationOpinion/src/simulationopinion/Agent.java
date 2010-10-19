package simulationopinion;

import java.util.Random;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author claatik
 */
public class Agent {

    private int opinion;
    private int waitTime;
    private int trustLevel;
    private int perceptionDepth;
    private int moveStep;
    private Coord coord;

    /* ------------- CONSTRUCTORS ------------------ */
    /** Constructs a new agent initialized to 0 **/
    public Agent() {
        this.trustLevel = 0;
        this.perceptionDepth = 0;
        this.moveStep = 0;
        this.waitTime = 0;
        this.coord = new Coord();
        this.opinion = 0;
    }

    /** Constructs a new agent

     * @param trustLevel
     * @param moveStep
     * @param perceptionDepth
     * @param waitTime
     * @param coord
     */
    public Agent(int trustLevel, int moveStep, int perceptionDepth, int waitTime, Coord c) {
        this.trustLevel = trustLevel;
        this.perceptionDepth = perceptionDepth;
        this.moveStep = moveStep;
        this.waitTime = waitTime;
        this.coord = c;
        this.opinion = new Random().nextInt(9);
    }

    /**
     * @author claatik
     * @return the opinion
     */
    public int getOpinion() {
        return this.opinion;
    }

    /**
     * @author claatik
     * @param opinion the opinion to set
     */
    public void setOpinion(int opinion) {
        this.opinion = opinion;
    }

    /**
     * @author claatik
     * @return the waitTime
     */
    public int getWaitTime() {
        return this.waitTime;
    }

    /**
     * @author claatik
     * @param waitTime the waitTime to set
     */
    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    /**
     * @author claatik
     * @return the trustLevel
     */
    public int getTrustLevel() {
        return this.trustLevel;
    }

    /**
     * @author claatik
     * @param trustLevel the trustLevel to set
     */
    public void setTrustLevel(int trustLevel) {
        this.trustLevel = trustLevel;
    }

    /**
     * @author claatik
     * @return the perceptionDepth
     */
    public int getPerceptionDepth() {
        return this.perceptionDepth;
    }

    /**
     * @author claatik
     * @param perceptionDepth the perceptionDepth to set
     */
    public void setPerceptionDepth(int perceptionDepth) {
        this.perceptionDepth = perceptionDepth;
    }

    /**
     * @author claatik
     * @return the moveStep
     */
    public int getMoveStep() {
        return this.moveStep;
    }

    /**
     * @author claatik
     * @param moveStep the moveStep to set
     */
    public void setMoveStep(int moveStep) {
        this.moveStep = moveStep;
    }

    /**
     * @author claatik
     * @return the coord
     */
    public Coord getCoord() {
        return this.coord;
    }

    /**
     * @author claatik
     * @param c the coord to set
     */
    public void setCoord(Coord c) {
        this.coord = c;
    }
}// end of Agent
