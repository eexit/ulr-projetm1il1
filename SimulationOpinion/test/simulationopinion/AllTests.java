/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulationopinion;

import junit.framework.Test;
import junit.framework.TestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author 33xiT
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({simulationopinion.EnviromnentTest.class})
public class AllTests {

    public static Test suite() {
        TestSuite ts = new TestSuite();
        ts.addTest(new EnviromnentTest());
        return ts;
    }
}
