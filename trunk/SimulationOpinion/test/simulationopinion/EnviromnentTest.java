package simulationopinion;

import java.util.ArrayList;
import junit.framework.TestCase;

/**
 * @author Joris Berthelot
 * @author Alexandre Coste
 */
public class EnviromnentTest extends TestCase {

     /**
     * The environment working instance
     */
    private static Environment env;

    /**
     * The methode will be called before every test methode
     */
    @Override
    public void setUp() {
        env = new Environment();
        assertEquals(0, env.getNbAgent());
        assertEquals(0, env.getAreaSize());
        assertEquals(Agent.OPINION_MAX + 1, env.getListAgentsToOpinion().size());
        assertTrue(env.getListAgents().isEmpty());
        assertFalse(env.isRunning());

        for (int i = Agent.OPINION_MIN; i < Agent.OPINION_MAX; i++) {
            assertTrue(env.getListAgentsToOpinion().get(i).isEmpty());
        }
    }

    /**
     * Test setAreaSize() and getAreaSize() methods
     */
    public void testSetAndSetAreaSize() {
        env.setAreaSize(200);
        assertEquals(200, env.getAreaSize());
    }

    /**
     * Test setListAgents() and getListAgents() methods
     */
    public void testGetAndSetListAgents() {
        ArrayList<Agent> list = new ArrayList<Agent>();
        for (int i = 0; i < 200; i++) {
            list.add(new Agent());
        }
        env.setListAgents(list);
        assertEquals(200, env.getListAgents().size());
        assertEquals(200, env.getNbAgent());
    }

    /**
     * Tests getListAgentToOpinion method
     */
    public void testGetListAgentsToOpinion() {
        try {
            ArrayList<Agent> list = new ArrayList<Agent>();
            for (int i = 0; i < 200; i++) {
                Agent agent = new Agent();
                agent.setOpinion(3);
                list.add(agent);
            }
            env.setListAgents(list);
            assertEquals(10, env.getListAgentsToOpinion().size());
            assertEquals(200, env.getListAgentsToOpinion().get(3).size());
        } catch (AgentException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Tests getNearAgents() method
     */
    public void testGetNearAgents() {
        try {
            ArrayList<Agent> list = new ArrayList<Agent>();
            env.setAreaSize(200);
            for (int i = 0; i < 200; i++) {
                Agent a = new Agent();
                a.setOpinion(3);
                a.setPerceptionDepth(1);
                a.setCoord(new Coord(i, i));
                list.add(a);
            }
            env.setListAgents(list);
            assertEquals(200, env.getListAgents().size());
            assertEquals(200, env.getNbAgent());
            assertEquals(200, env.getListAgentsToOpinion().get(3).size());
            assertEquals(1, env.getNearAgents(list.get(0)).size());
            assertEquals(200, env.getListAgentsToOpinion().get(3).size());
            assertEquals(2, env.getNearAgents(list.get(10)).size());
            assertEquals(200, env.getListAgentsToOpinion().get(3).size());
            assertEquals(1, env.getNearAgents(list.get(199)).size());
            assertEquals(200, env.getListAgentsToOpinion().get(3).size());
        } catch (AgentException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Tests updateAgentAllocation() method
     */
    public void testUpdateListAgents() {
        try {
            ArrayList<Agent> agents = new ArrayList<Agent>();
            Agent a1;
            Agent a2;
            
            for (int i = 0; i < 10; i++) {
                Agent agent = new Agent();
                agent.setOpinion(1);
                agents.add(agent);
            }

            env.setListAgents(agents);
            assertEquals(10, env.getNbAgent());
            assertEquals(10, env.getListAgentsToOpinion().get(1).size());

            // Simulates a Environment.getNearAgents() call
            a1 = env.getListAgentsToOpinion().get(1).remove(2);
            a2 = env.getListAgentsToOpinion().get(1).remove(3);
            assertEquals(8, env.getListAgentsToOpinion().get(1).size());

            // Simulates that the current working agent persuasion succeed
            a1.setOpinion(2);
            a2.setOpinion(3);

            // Updates the agent allocation through opinion levels
            env.updateAgentAllocation(a1);
            assertEquals(10, env.getNbAgent());
            assertEquals(8, env.getListAgentsToOpinion().get(1).size());
            assertEquals(1, env.getListAgentsToOpinion().get(2).size());
            assertEquals(0, env.getListAgentsToOpinion().get(3).size());

            env.updateAgentAllocation(a2);
            assertEquals(10, env.getNbAgent());
            assertEquals(8, env.getListAgentsToOpinion().get(1).size());
            assertEquals(1, env.getListAgentsToOpinion().get(2).size());
            assertEquals(1, env.getListAgentsToOpinion().get(3).size());

        } catch (AgentException e) {
            fail(e.getMessage());
        } catch (EnvironmentException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Tests that updateAgentAllocation() method throws an exception if there are
     * agent intruders
     */
    public void testUpdateListAgentsFailTryToAddAgent() {
        try {
            ArrayList<Agent> agents = new ArrayList<Agent>();

            // Intruder agents
            Agent a1 = new Agent();
            a1.setOpinion(4);

            for (int i = 0; i < 10; i++) {
                Agent agent = new Agent();
                agent.setOpinion(1);
                agents.add(agent);
            }

            env.setListAgents(agents);
            env.updateAgentAllocation(a1);
            fail("EnvironmentException throw exception expected!");
        } catch (AgentException e) {
        } catch (EnvironmentException e) {
        }
    }

    /**
     * Tests that run() method has a stop condition
     */
    public void testRunCanStop() {
        try {
            ArrayList<Agent> list = new ArrayList<Agent>();
            env.setAreaSize(200);
            for (int i = 0; i < 200; i++) {
                Agent a = new Agent();
                a.setOpinion(8);
                a.setPerceptionDepth(1);
                a.setCoord(new Coord(0, 0));
                list.add(a);
            }
            env.setListAgents(list);
            assertFalse(env.isRunning());
            assertEquals(200, env.getListAgentsToOpinion().get(8).size());
            // env.run();
            // assertTrue(env.isRunning());
            env.stop();
            assertFalse(env.isRunning());
            assertEquals(200, env.getListAgentsToOpinion().get(8).size());
        } catch (AgentException e) {
        // } catch (EnvironmentException e) {
        }
    }
}
