package simulationopinion;

import junit.framework.TestCase;

/**
 * @author Joris Berthelot
 */
public class SaveManagementTest extends TestCase {

    private static SaveManagement sm;

    @Override
    public void setUp() {
        sm = new SaveManagement("test.log");
        assertEquals("test.log", sm.getFilename());
    }

}