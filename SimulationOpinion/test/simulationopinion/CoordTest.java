/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulationopinion;

import junit.framework.TestCase;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author claatik
 */
public class CoordTest extends TestCase
{
    private static Coord instance;

    /**
     * The methode will be called before every test methode
     */
    public void setUp() {
        instance = new Coord();
        assertEquals(0, instance.x());
        assertEquals(0, instance.y());
        //assertTrue(instance.x() instanceof Integer);
    }

     /**
     * Test of x and setX methods, of class Coord.
     */
    @Test
    public void testGetAndSetX() {
        instance.setX(3);
        assertEquals(3, instance.x());
    }

    /**
     * Test of y and setY methods, of class Coord.
     */
    @Test
    public void testGetAndSetY() {
        instance.setY(2);
        assertEquals(2, instance.y());
    }

    /**
     * Test of setCoord method, of class Coord.
     */
    @Test
    public void testSetCoord() {
        instance.setCoord(new Coord(5, 9));
        assertEquals(5, instance.x());
        assertEquals(9, instance.y());
    }

   
}