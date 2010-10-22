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

    private ArrayList<Agent> listAgent;
    private TreeMap<Integer, Color> listColorToOpinion;

    public DisplayEnvironment() {
    }

    public DisplayEnvironment(ArrayList<Agent> listAgent, TreeMap<Integer, Color> listColorToOpinion) {
        super();
        this.listAgent = listAgent;
        this.listColorToOpinion = listColorToOpinion;
    }

    @Override
    public void paintComponent(Graphics s) {
        super.paintComponent(s);
        s.setColor(Color.WHITE);
        s.fillRect(0, 0, this.getWidth(), this.getHeight());
        for (Agent a : listAgent) {
            s.setColor(this.listColorToOpinion.get(a.getOpinion()));
            s.fillOval(a.getCoord().x(), a.getCoord().y(), 5, 5);
        }
        /*s.setColor(Color.BLACK);
        s.fillOval(10, 10, 10, 10);*/
    }
}
