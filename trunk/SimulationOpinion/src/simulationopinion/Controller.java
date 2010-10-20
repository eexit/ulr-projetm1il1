/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulationopinion;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author afantou
 */
public class Controller {

    public void execute() {
        try {
            ArrayList<Agent> agentsList = new ArrayList<Agent>();
            DisplayManagement graphicInterface = new DisplayManagement(agentsList);
            Environment env = new Environment();

            while (!graphicInterface.isVisible()) {
                System.out.print("");
            }

            for (int i = 0; i < graphicInterface.getNbAgent(); i++) {
                Agent agent = new Agent(graphicInterface.getTrust(), graphicInterface.getStep(), graphicInterface.getArea(), graphicInterface.getWaitTime(), new Coord());
                agentsList.add(agent);
            }

            env.setAreaSize(graphicInterface.getDimEnv());
            env.setListAgents(agentsList);
            env.run(graphicInterface);
        } catch (AgentException ex) {
            System.err.println(ex.getMessage());
        } catch (EnvironmentException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
