package simulationopinion;

import java.util.ArrayList;
import java.util.TreeMap;
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
        assertEquals(0, instance.getWaitTime());
        assertEquals(Agent.TRUST_MIN, instance.getTrustLevel());
        assertEquals(Agent.MOVE_STEP_MIN, instance.getMoveStep());
        assertEquals(1, instance.getPerceptionDepth());
        assertTrue(instance.getCoord() instanceof Coord);
    }

    /**
     * Tests opinion random allocation for new agent
     */
    public void testAgentRandomOpinion() {
        int amount = 250;
        TreeMap<Integer, Integer> stats = new TreeMap<Integer, Integer>();
        for (int i = Agent.OPINION_MIN; i <= Agent.OPINION_MAX; i++) {
            stats.put(i, new Integer(0));
        }

        for (int i = 0; i < amount; i++) {
            Agent a = new Agent();
            Integer value = stats.get(a.getOpinion());
            stats.put(a.getOpinion(), ++value);
            // System.out.println(stats.get(a.getOpinion()));
        }

        System.out.println("Opinion allocation for " + amount + " agents:");
        for (int i = Agent.OPINION_MIN; i <= Agent.OPINION_MAX; i++) {
            System.out.println("Opinion" + i + " => " + stats.get(i) * 100 / amount + "%");
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

    public void testMove() {
        try {
            instance.move(12);
            if (instance.getCoord().x() == 0 && instance.getCoord().y() == 0) {
                fail("Agent don't move");
            }
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

    public void testPersuade() {
        try {
            Agent a1 = new Agent(5, 1, 5, 5, new Coord(1, 1));
            Agent a2 = new Agent(5, 1, 5, 5, new Coord(1, 2));
            a1.setOpinion(1);
            a2.setOpinion(2);
            a1.persuade(a2);
            assertEquals(a1.getOpinion(), a2.getOpinion());
        } catch (AgentException e) {
        }
    }
}
