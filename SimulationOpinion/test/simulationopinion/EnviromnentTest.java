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
        assertEquals(Agent.OPINION_MAX, env.getListAgentsToOpinion().size());
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
            assertEquals(9, env.getListAgentsToOpinion().size());
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
            assertEquals(199, env.getListAgentsToOpinion().get(3).size());
            assertEquals(2, env.getNearAgents(list.get(10)).size());
            assertEquals(197, env.getListAgentsToOpinion().get(3).size());
            assertEquals(1, env.getNearAgents(list.get(199)).size());
            assertEquals(196, env.getListAgentsToOpinion().get(3).size());
        } catch (AgentException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Tests updateListAgents() method
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
            assertTrue(env.getListAgentsToOpinion().get(0).isEmpty());

            // Simulates a Environment.getNearAgents() call
            a1 = env.getListAgentsToOpinion().get(1).remove(2);
            a2 = env.getListAgentsToOpinion().get(1).remove(3);
            assertEquals(8, env.getListAgentsToOpinion().get(1).size());

            // Simulates that the current working agent persuasion succeed
            agents.clear();
            a1.setOpinion(2);
            a2.setOpinion(3);
            agents.add(a1);
            agents.add(a2);

            // Updates the agent allocation through opinion levels
            env.updateListAgents(agents);
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
     * Tests that updateListAgents() method throws an exception if there are
     * agent intruders
     */
    public void testUpdateListAgentsFailTryToAddAgent() {
        try {
            ArrayList<Agent> agents = new ArrayList<Agent>();
            ArrayList<Agent> intruders = new ArrayList<Agent>();

            // Intruder agents
            Agent a1 = new Agent();
            Agent a2 = new Agent();
            a1.setOpinion(4);
            a1.setOpinion(8);
            intruders.add(a1);
            intruders.add(a2);

            for (int i = 0; i < 10; i++) {
                Agent agent = new Agent();
                agent.setOpinion(1);
                agents.add(agent);
            }

            env.setListAgents(agents);
            env.updateListAgents(intruders);
            fail("EnvironmentException throw exception expected!");
        } catch (AgentException e) {
        } catch (EnvironmentException e) {
        }
    }

    /**
     * Tests that run() method not run if environment not well initialized
     */
    public void testRunFailNoAgents() {
        try {
            assertFalse(env.isRunning());
            env.run();
            fail("EnvironmentException throw exception expected!");
        } catch (EnvironmentException e) {
        }
    }

    /**
     * Tests that run() method has a stop condition
     * TODO implement test
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
