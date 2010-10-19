/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulationopinion;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author claatik
 */
public class CoordTest {

    /**
     * Test of x method, of class Coord.
     */
    @Test
    public void testX() {
        System.out.println("X");
        Coord instance = new Coord();
        int expResult = 0;
        int result = instance.x();
        assertEquals(expResult, result);
    }

    /**
     * Test of y method, of class Coord.
     */
    @Test
    public void testY() {
        System.out.println("Y");
        Coord instance = new Coord();
        int expResult = 0;
        int result = instance.y();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCoord method, of class Coord.
     */
    @Test
    public void testSetCoord() {
        System.out.println("setCoord");
        Coord c = new Coord(5, 9);
        Coord instance = new Coord();
        instance.setCoord(c);
        assertEquals(5, instance.x());
        assertEquals(9, instance.y());
    }

    /**
     * Test of setX method, of class Coord.
     */
    @Test
    public void testSetX() {
        System.out.println("setX");
        int x = 3;
        Coord instance = new Coord();
        instance.setX(x);
        assertEquals(3, instance.x());
    }

    /**
     * Test of setY method, of class Coord.
     */
    @Test
    public void testSetY() {
        System.out.println("setY");
        int y = 2;
        Coord instance = new Coord();
        instance.setY(y);
        assertEquals(2, instance.y());
    }
}