/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package simulationopinion;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.TreeMap;
import javax.swing.JPanel;

/**
 *
 * @author Teddie
 */
public class DisplayEnvironment extends JPanel {
    private ArrayList<Agent> listAgent;
    private TreeMap<Integer,Color> listColorToOpinion;
    public DisplayEnvironment(ArrayList<Agent> listAgent, TreeMap<Integer,Color> listColorToOpinion){
        super();
        this.listAgent = listAgent;
        this.listColorToOpinion = listColorToOpinion;
    }
    @Override
    protected void paintComponent(Graphics s){
        for(Agent a : listAgent)
        {
            s.setColor(this.listColorToOpinion.get(a.getOpinion()));
            s.fillOval(a.getCoord().x(), a.getCoord().y(), 5, 5);
        }
        s.setColor(Color.BLACK);
        s.drawOval(10, 10, 10, 10);
    }

}
