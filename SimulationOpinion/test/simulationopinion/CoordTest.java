package simulationopinion;

import junit.framework.TestCase;

/**
 * @author claatik
 */
public class CoordTest extends TestCase {

    private static Coord instance;

    @Override
    public void setUp() {
        instance = new Coord();
    }

    /**
     * Test of x method, of class Coord.
     */
    public void testX() {
        assertEquals(0, instance.x());
    }

    /**
     * Test of y method, of class Coord.
     */
    public void testY() {
        assertEquals(0, instance.y());
    }

    /**
     * Test of setCoord method, of class Coord.
     */
    public void testSetCoord() {
        instance.setCoord(new Coord(5, 9));
        assertEquals(5, instance.x());
        assertEquals(9, instance.y());
    }

    /**
     * Test of setX method, of class Coord.
     */
    public void testSetX() {
        instance.setX(3);
        assertEquals(3, instance.x());
    }

    /**
     * Test of setY method, of class Coord.
     */
    public void testSetY() {
        instance.setY(2);
        assertEquals(2, instance.y());
    }
}
