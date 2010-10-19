/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulationopinion;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author claatik
 */
public class AgentTest {

    /**
     * Test of getOpinion method, of class Agent.
     */
    @Test
    public void testGetOpinion() {
        System.out.println("getOpinion");
        Agent instance = new Agent();
        int expResult = 0;
        int result = instance.getOpinion();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOpinion method, of class Agent.
     */
    @Test
    public void testSetOpinion() {
        System.out.println("setOpinion");
        int expResult = 2;
        int opinion = 2;
        Agent instance = new Agent();
        instance.setOpinion(opinion);
        assertEquals(expResult, instance.getOpinion());
    }

    /**
     * Test of getWaitTime method, of class Agent.
     */
    @Test
    public void testGetWaitTime() {
        System.out.println("getWaitTime");
        Agent instance = new Agent();
        int expResult = 0;
        int result = instance.getWaitTime();
        assertEquals(expResult, result);
    }

    /**
     * Test of setWaitTime method, of class Agent.
     */
    @Test
    public void testSetWaitTime() {
        System.out.println("setWaitTime");
        int expResult = 4;
        int waitTime = 4;
        Agent instance = new Agent();
        instance.setWaitTime(waitTime);
        assertEquals(expResult, instance.getWaitTime());
    }

    /**
     * Test of getTrustLevel method, of class Agent.
     */
    @Test
    public void testGetTrustLevel() {
        System.out.println("getTrustLevel");
        Agent instance = new Agent();
        int expResult = 0;
        int result = instance.getTrustLevel();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTrustLevel method, of class Agent.
     */
    @Test
    public void testSetTrustLevel() {
        System.out.println("setTrustLevel");
        int expResult = 3;
        int trustLevel = 3;
        Agent instance = new Agent();
        instance.setTrustLevel(trustLevel);
        assertEquals(expResult, instance.getTrustLevel());
    }

    /**
     * Test of getPerceptionDepth method, of class Agent.
     */
    @Test
    public void testGetPerceptionDepth() {
        System.out.println("getPerceptionDepth");
        Agent instance = new Agent();
        int expResult = 0;
        int result = instance.getPerceptionDepth();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPerceptionDepth method, of class Agent.
     */
    @Test
    public void testSetPerceptionDepth() {
        System.out.println("setPerceptionDepth");
        int expResult = 4;
        int perceptionDepth = 4;
        Agent instance = new Agent();
        instance.setPerceptionDepth(perceptionDepth);
        assertEquals(expResult, instance.getPerceptionDepth());
    }

    /**
     * Test of getMoveStep method, of class Agent.
     */
    @Test
    public void testGetMoveStep() {
        System.out.println("getMoveStep");
        Agent instance = new Agent();
        int expResult = 0;
        int result = instance.getMoveStep();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMoveStep method, of class Agent.
     */
    @Test
    public void testSetMoveStep() {
        System.out.println("setMoveStep");
        int expResult = 5;
        int moveStep = 5;
        Agent instance = new Agent();
        instance.setMoveStep(moveStep);
        assertEquals(expResult, instance.getMoveStep());
    }

    /**
     * Test of getCoord method, of class Agent.
     */
    @Test
    public void testGetCoord() {
        System.out.println("getCoord");
        Agent instance = new Agent();
        Coord expResult = new Coord();
        Coord result = instance.getCoord();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCoord method, of class Agent.
     */
    @Test
    public void testSetCoord() {
        System.out.println("setCoord");
        int expResultX = 3;
        int expResultY = 2;
        Coord c = new Coord(3, 2);
        Agent instance = new Agent();
        instance.setCoord(c);
        int coordX = instance.getCoord().x();
        int coordY = instance.getCoord().y();
        assertEquals(expResultX, coordX);
        assertEquals(expResultY, coordY);
    }

    /**
     * Test of Agent's constructor.
     */
    public AgentTest() {
        System.out.println("constructor");
        //public Agent(int trustLevel,  int moveStep, int perceptionDepth, int waitTime, Coord c)
        Agent instance = new Agent(2, 1, 8, 3, new Coord(4, 9));
        assertEquals(2, instance.getTrustLevel());
        assertEquals(1, instance.getMoveStep());
        assertEquals(8, instance.getPerceptionDepth());
        assertEquals(3, instance.getWaitTime());
        assertEquals(4, (int) instance.getCoord().x());
        assertEquals(9, (int) instance.getCoord().y());
    }
}