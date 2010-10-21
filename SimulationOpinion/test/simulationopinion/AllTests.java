package simulationopinion;

import junit.framework.Test;
import junit.framework.TestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({simulationopinion.EnviromnentTest.class})
public class AllTests {

    public static Test suite() {
        TestSuite ts = new TestSuite();
        ts.addTest(new AgentTest());
        ts.addTest(new CoordTest());
        ts.addTest(new EnviromnentTest());
        return ts;
    }
}