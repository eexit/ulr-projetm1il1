package simulationopinion;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import junit.framework.TestCase;

/**
 * @author Joris Berthelot
 * @author Alexandre Coste
 */
public class EnviromnentTest extends TestCase {

    protected static Environment env;

    public EnviromnentTest() {
    }

    @Override
    public void setUp() {
        env = new Environment();
        assertEquals(0, env.getNbAgent());
        assertEquals(0, env.getAreaSize());
        assertEquals(Agent.OPINION_MAX, env.getListAgentsToOpinion().size());
        assertTrue(env.getListAgents().isEmpty());
        assertFalse(env.isRunning());
    }

    public void testSetAndSetAreaSize() {
        env.setAreaSize(200);
        assertEquals(200, env.getAreaSize());
    }

    public void testGetAndSetListAgents() {
        ArrayList<Agent> list = new ArrayList<Agent>();
        for (int i = 0; i < 200; i++) {
            list.add(new Agent());
        }
        env.setListAgents(list);
        assertEquals(200, env.getListAgents().size());
        assertEquals(200, env.getNbAgent());
    }

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
        }
    }

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
            assertEquals(2, env.getNearAgents(list.get(10)).size());
            assertEquals(1, env.getNearAgents(list.get(199)).size());
        } catch (AgentException e) {
        }
    }
    /**
     * Dans un while(true), on parcours tous les agents, on les move, on les persuade, on update, on teste la
     * condition d'arrêt
     */
    public void testRunFailNoAgents() {
        try {
            assertFalse(env.isRunning());
            env.run();
            fail("EnvironmentException should be thrown!");
        } catch (EnvironmentException e) {
        }
    }

    public void testRunCanStop() {
        fail("Not implemented yet!");
    }
}
