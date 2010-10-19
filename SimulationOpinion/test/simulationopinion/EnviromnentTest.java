package simulationopinion;

import java.util.ArrayList;
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

    public void setUp() {
        env = new Environment();
        assertEquals(0, env.getNbAgent());
        assertEquals(0, env.getAreaSize());
        assertTrue(env.getListAgentsToOpinion().isEmpty());
        assertTrue(env.getListAgents().isEmpty());
    }

    public void testSetNbAgent() {
        env.setNbAgent(2);
        assertEquals(2, env.getNbAgent());
    }

    public void testSetAreaSize() {
        env.setAreaSize(200);
        assertEquals(200, env.getAreaSize());
    }

    public void testGetListAgentsToOpinion() {
        TreeMap<Integer, ArrayList<Agent>> list = new TreeMap<Integer, ArrayList<Agent>>();
        for (int i = 0; i < 9; i++) {
            ArrayList<Agent> al = new ArrayList<Agent>();
            al.add(new Agent());
            list.put(i, al);
        }
        env.setListAgentsToOpinion(list);
        assertEquals(9, env.getListAgentsToOpinion().size());
    }

    public void testGetListAgents() {
        ArrayList<Agent> list = new ArrayList<Agent>();
        for (int i = 0; i < 200; i++) {
            list.add(new Agent());
        }
        env.setListAgents(list);
        assertEquals(200, env.getListAgents().size());
        assertEquals(200, env.getNbAgent());
    }

    public void testGetNearAgents() {
        ArrayList<Agent> list = new ArrayList<Agent>();
        env.setAreaSize(200);
        for (int i = 0; i < 200; i++) {
            Agent a = new Agent();
            a.setPerceptionDepth(1);
            a.setCoord(new Coord(i, i));
            list.add(a);
        }
        env.setListAgents(list);
        assertEquals(200, env.getListAgents().size());
        assertEquals(200, env.getNbAgent());
        assertEquals(1, env.getNearAgents(list.get(0)).size());
        assertEquals(2, env.getNearAgents(list.get(10)).size());
        assertEquals(1, env.getNearAgents(list.get(199)).size());
    }
}
