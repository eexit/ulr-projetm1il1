package simulationopinion;

import java.awt.Point;

/**
 * @author Chama Laatik
 */
public class Coord extends Point {

    /**
     * Constructor
     */
    public Coord() {
        super();
    }

    /** Constructs and initializes a point at the specified (x, y) location in the coordinate space.
     * @param x
     * @param y
     */
    public Coord(int x, int y) {
        super(x, y);
    }

    /**
     * Returns the x coordinate of the point in integer value.
     * @return the x coordinate
     */
    public int x() {
        return (int) super.getX();
    }

    /**
     * Returns the y coordinate of the point in integer value.
     * @return the y coordinate
     */
    public int y() {
        return (int) super.getY();
    }

    /**
     * Sets agent coord
     * @param c the coord to set
     */
    public void setCoord(Coord c) {
        this.setLocation((int) c.x, (int) c.y);
    }

    /**
     * Sets agent x coord
     * @param x to set
     */
    public void setX(int x) {
        super.setLocation(x, this.y);
    }

    /**
     * Sets agent y coord
     * @param y to set
     */
    public void setY(int y) {
        super.setLocation(this.x, y);
    }
}
