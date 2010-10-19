package simulationopinion;

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
        assertEquals(0, instance.getTrustLevel());
        assertEquals(0, instance.getMoveStep());
        assertEquals(0, instance.getPerceptionDepth());
        assertTrue(instance.getCoord() instanceof Coord);
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
