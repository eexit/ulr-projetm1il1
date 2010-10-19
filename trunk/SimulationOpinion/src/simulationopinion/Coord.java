/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulationopinion;

import java.awt.Point;

/**
 *
 * @author claatik
 */
public class Coord extends Point {
    /* ------------- CONSTRUCTORS ------------------ */

    /** Constructs and initializes a point at the origin (0,0) of the coordinate space.
     * @author claatik
     */
    public Coord() {
        super();
    }

    /** Constructs and initializes a point at the specified (x, y) location in the coordinate space.
     * @author claatik
     * @param x
     * @param y
     */
    public Coord(int x, int y) {
        super(x, y);
    }

    /**
     * Returns the x coordinate of the point in integer value.
     * @author claatik
     * @return the x coordinate
     */
    public int x() {
        return (int) super.getX();
    }

    /**
     * Returns the y coordinate of the point in integer value.
     * @author claatik
     * @return the y coordinate
     */
    public int y() {
        return (int) super.getY();
    }

    /**
     * @author claatik
     * @param c the coord to set
     */
    public void setCoord(Coord c) {
        this.setLocation((int) c.x, (int) c.y);
    }

    /**
     * @author claatik
     * @param x to set
     */
    public void setX(int x) {
        super.setLocation(x, this.y);
    }

    /**
     * @author claatik
     * @param y to set
     */
    public void setY(int y) {
        super.setLocation(this.x, y);
    }
}
