package simulationopinion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author Joris Berthelot
 * @author afantou
 */
public class Controller {

    public Controller() {
    }

    /**
     * Application runner
     */
    public void exec() {
        try {
            ArrayList<Agent> agentsList = new ArrayList<Agent>();
            Environment env = new Environment();
            DisplayManagement graphicInterface = new DisplayManagement(agentsList, env);

            while (!graphicInterface.isVisible()) {
                System.out.print("");
            }

            for (int i = 0; i < graphicInterface.getNbAgent(); i++) {
                Agent agent = new Agent(graphicInterface.getTrust(), graphicInterface.getStep(), graphicInterface.getArea(), graphicInterface.getWaitTime(), new Coord(new Random().nextInt(graphicInterface.getDimEnv() + 1), new Random().nextInt(graphicInterface.getDimEnv() + 1)));
                agentsList.add(agent);
            }
            env.setAreaSize(graphicInterface.getDimEnv());
            env.setListAgents(agentsList);

            // TODO graphicInterface.get
            env.setSaver(new SaveManagement("application.log"));
            env.setLogger(new LogManagement());

            env.run(graphicInterface);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (AgentException e) {
            System.err.println(e.getMessage());
        } catch (EnvironmentException e) {
            System.err.println(e.getMessage());
        }
    }
}
