/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulationopinion;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

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
                Agent agent = new Agent(graphicInterface.getTrust(), graphicInterface.getStep(), graphicInterface.getArea(), graphicInterface.getWaitTime(), new Coord(new Random().nextInt(graphicInterface.getDimEnv() + 1), new Random().nextInt(graphicInterface.getDimEnv() + 1)));
                agentsList.add(agent);
                System.out.println(agent.getOpinion() + " x : " + agent.getCoord().x() + " y : " + agent.getCoord().y());
            }
            env.setAreaSize(graphicInterface.getDimEnv());
            env.setListAgents(agentsList);
            for (Map.Entry<Integer, ArrayList<Agent>> e : env.getListAgentsToOpinion().entrySet()) {
                System.out.println("Opinion " + e.getKey());
                for (Agent a : e.getValue()) {
                    System.out.println(a.getOpinion());
                }
            }
            env.run(graphicInterface);
        } catch (AgentException ex) {
            System.err.println(ex.getMessage());
        } catch (EnvironmentException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
