/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulationopinion;

import java.io.*;
import java.util.ArrayList;
import java.util.TreeMap;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author Teddie
 */
public class LogManagementTest extends TestCase {
    /**
     * Test of saveData method, of class LogManagement.
     * @author Teddie
     * @author Chama
     */
    @Test
    public void testSaveData() throws Exception {
        System.out.println("saveData");
        TreeMap<Integer, ArrayList<Agent>> saveOpinion = new TreeMap<Integer, ArrayList<Agent>>();
        LogManagement instance = new LogManagement();

        Agent a = new Agent();
        a.setOpinion(2);
        ArrayList<Agent> l = new ArrayList<Agent>();
        l.add(a);
        saveOpinion.put(2, l);
        instance.saveData(saveOpinion);

        InputStream ips = new FileInputStream(LogManagement.LOG_FILENAME);
        InputStreamReader ipsr = new InputStreamReader(ips);
        BufferedReader br = new BufferedReader(ipsr);
        String ligne = br.readLine();
        String[] sOpinion = ligne.split(" ");
        int i = Integer.parseInt(sOpinion[0]);
        assertEquals(2,i);
        String[] sAgent=ligne.split("_");
        for(int j=1;i<sAgent.length;i++){
            i=Integer.parseInt(sAgent[j]);
            assertEquals(2,i);
        }


    }
}
