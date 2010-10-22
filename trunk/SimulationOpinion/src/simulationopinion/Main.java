package simulationopinion;

/**
 * @author Alexandre Fantou
 * @author Joris Berthelot
 */
public class Main {

    public static void main(String[] args) {
        try {
            Controller main = new Controller();
            main.start();
            main.join();
        } catch (InterruptedException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
