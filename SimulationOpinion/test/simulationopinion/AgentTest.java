package simulationopinion;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;
import static org.junit.Assert.*;

/**
 * @author Joris Berthelot (joris.berthelot@gmail.com)
 */
public class AgentTest extends TestCase {

    /**
     * The agent working instance
     */
    private static Agent instance;

    /**
     * The methode will be called before every test methode
     */
    @Override
    public void setUp() {
        instance = new Agent();
        assertEquals(0, instance.getOpinion());
        assertEquals(0, instance.getWaitTime());
        assertEquals(1, instance.getTrustLevel());
        assertEquals(1, instance.getMoveStep());
        assertEquals(0, instance.getPerceptionDepth());
        assertTrue(instance.getCoord() instanceof Coord);
    }

    public void testMove() {
        try {
            instance.move(12);
            if(instance.getCoord().x() == 0 && instance.getCoord().y() == 0)
                fail("Agent don't move");
        } catch (AgentException ex) {
        }

    }

    public void testMoveFail() {
        try {
            instance.move(-2);
            fail("AgentException thrown exception expected!");
        } catch (AgentException ex) {
        }

    }

    public void testPersuade(){
        try {
            Agent a1 = new Agent(5, 1, 5, 5, new Coord(1, 1));
            a1.setOpinion(1);
            Agent a2 = new Agent(5, 1, 5, 5, new Coord(1, 2));
            a2.setOpinion(2);
            ArrayList<Agent> listAgent = new ArrayList<Agent>();
            listAgent.add(a2);
            a1.persuade(listAgent);
            assertEquals(a1.getOpinion(), a2.getOpinion());
        } catch (AgentException ex) {
            Logger.getLogger(AgentTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getOpinion and setOpinion methods, of class Agent.
     */
    public void testGetAndSetOpinion() {
        try {
            instance.setOpinion(2);
            assertEquals(2, instance.getOpinion());
        } catch (AgentException e) {
        }
    }

    /**
     * Tests that the agent can't have an opinion value over the defined limit
     */
    public void testSetOpinionFailOverflowValue() {
        try {
            instance.setOpinion(Agent.OPINION_MAX + 10);
            fail("AgentException thrown exception expected!");
        } catch (AgentException e) {
        }
    }

    /**
     * Tests that the agent can't have an opinion value under the defined limit
     */
    public void testSetOpinionFailUnderflowValue() {
        try {
            instance.setOpinion(Agent.OPINION_MIN - 10);
            fail("AgentException thrown exception expected!");
        } catch (AgentException e) {
        }
    }

    /**
     * Test of getWaitTime and setWaitTime methods, of class Agent.
     */
    public void testGetAndSetWaitTime() {
        instance.setWaitTime(4);
        assertEquals(4, instance.getWaitTime());
    }

    /**
     * Test of getTrustLevel and setTrustLevel methods, of class Agent.
     */
    public void testGetAndSetTrustLevel() {
        try {
            instance.setTrustLevel(3);
            assertEquals(3, instance.getTrustLevel());
        } catch (AgentException e) {
        }
    }

    /**
     * Tests that the agent can't have a trust level value over the defined limit
     */
    public void testSetTrustLevelFailOverflowValue() {
        try {
            instance.setTrustLevel(Agent.TRUST_MAX + 10);
            fail("AgentException thrown exception expected!");
        } catch (AgentException e) {
        }
    }

    /**
     * Tests that the agent can't have a trust level value under the defined limit
     */
    public void testSetTrustLevelFailUnderflowValue() {
        try {
            instance.setTrustLevel(Agent.TRUST_MIN - 10);
            fail("AgentException thrown exception expected!");
        } catch (AgentException e) {
        }
    }

    /**
     * Test of getPerceptionDepth and setPerceptionDepth methods, of class Agent.
     */
    public void testGetAndSetPerceptionDepth() {
        instance.setPerceptionDepth(4);
        assertEquals(4, instance.getPerceptionDepth());
    }

    /**
     * Test of getMoveStep and setMoveStep methods, of class Agent.
     */
    public void testGetAndSetMoveStep() {
        try {
            instance.setMoveStep(1);
            assertEquals(1, instance.getMoveStep());
        } catch (AgentException e) {
        }
    }

    /**
     * Tests that the agent can't have a move step value over the defined limit
     */
    public void testSetMoveStepFailOverflowValue() {
        try {
            instance.setMoveStep(Agent.MOVE_STEP_MAX + 10);
            fail("AgentException thrown exception expected!");
        } catch (AgentException e) {
        }
    }

    /**
     * Tests that the agent can't have a move step value under the defined limit
     */
    public void testSetMoveStepFailUnderflowValue() {
        try {
            instance.setMoveStep(Agent.MOVE_STEP_MIN - 10);
            fail("AgentException thrown exception expected!");
        } catch (AgentException e) {
        }
    }

    /**
     * Test of getCoord and setCoord methods, of class Agent.
     */
    public void testGetandSetCoord() {
        assertEquals(instance.getCoord(), new Coord());
        instance.setCoord(new Coord(1, 2));
        assertEquals(instance.getCoord(), new Coord(1, 2));
    }
}
