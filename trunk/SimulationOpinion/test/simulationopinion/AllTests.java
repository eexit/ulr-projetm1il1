package simulationopinion;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {
    public static Test suite() {
        TestSuite suite = new TestSuite("Unit Tests");
        suite.addTestSuite(AgentTest.class);
        suite.addTestSuite(CoordTest.class);
        suite.addTestSuite(EnviromnentTest.class);
        suite.addTestSuite(LogManagementTest.class);
        // suite.addTestSuite(SaveManagementTest.class);
        return suite;
    }
}