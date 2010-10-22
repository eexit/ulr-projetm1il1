package simulationopinion;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.TreeMap;
import javax.swing.JPanel;

/**
 * @author Teddie Bonnaud
 */
public class DisplayEnvironment extends JPanel {

    /**
     * List of agents
     */
    private ArrayList<Agent> listAgent;
    /**
     * List of agent allocation per color
     */
    private TreeMap<Integer, Color> listColorToOpinion;

    /**
     * Constructor
     */
    public DisplayEnvironment() {
    }

    /**
     * Constructor with given parameters
     * @param listAgent
     * @param listColorToOpinion
     */
    public DisplayEnvironment(ArrayList<Agent> listAgent, TreeMap<Integer, Color> listColorToOpinion) {
        super();
        this.listAgent = listAgent;
        this.listColorToOpinion = listColorToOpinion;
    }

    /**
     * Paints a component
     * @param s
     */
    @Override
    public void paintComponent(Graphics s) {
        super.paintComponent(s);
        s.setColor(Color.WHITE);
        s.fillRect(0, 0, this.getWidth(), this.getHeight());
        for (Agent a : listAgent) {
            s.setColor(this.listColorToOpinion.get(a.getOpinion()));
            s.fillOval(a.getCoord().x(), a.getCoord().y(), 5, 5);
        }
    }
}
