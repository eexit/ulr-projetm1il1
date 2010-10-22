package simulationopinion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;

/**
 * @author Joris Berthelot
 * @author Alexandre Fantou
 */
public class Controller extends Thread implements ActionListener {

    /**
     * Environment instance
     */
    private Environment env;
    /**
     * View instance
     */
    private DisplayManagement view;
    /**
     * Agent list
     */
    private ArrayList<Agent> agentsList;

    /**
     * Constructor
     */
    public Controller() {
        this.agentsList = new ArrayList<Agent>();
        this.env = new Environment();
        this.view = new DisplayManagement(this.agentsList, this);
    }

    /**
     * Thread runner
     */
    @Override
    public synchronized void run() {
        try {
            this.wait();
            this.env.start();
        } catch (InterruptedException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Application runner
     */
    public void launch() {
        try {
            for (int i = 0; i < this.view.getNbAgent(); i++) {
                Agent agent = new Agent(this.view.getTrust(), this.view.getStep(), this.view.getArea(), this.view.getWaitTime(), new Coord(new Random().nextInt(this.view.getDimEnv() + 1), new Random().nextInt(this.view.getDimEnv() + 1)));
                this.agentsList.add(agent);
            }

            this.env.setAreaSize(this.view.getDimEnv());
            this.env.setListAgents(this.agentsList);
            this.env.setView(this.view);
            this.view.update(this.env.getListAgentsToOpinion());

            if (null != this.view.getPath()) {
                this.env.setSaver(new SaveManagement(this.view.getPath()));
            }

            this.env.setLogger(new LogManagement());

        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (AgentException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Button event listener receiver
     * @param e
     */
    @Override
    public synchronized void actionPerformed(ActionEvent e) {
        if (((JButton) e.getSource()).getText() == "Ok") {
            this.launch();
        }
        if (((JButton) e.getSource()).getText() == "Start") {
            this.notifyAll();
        }
        if (((JButton) e.getSource()).getText() == "Stop") {
            this.env.kill();
        }
    }
}
