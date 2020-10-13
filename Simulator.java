import java.util.ArrayList;
import java.util.Random;

public class Simulator {
    private SolarSystem system;

    private ArrayList<CelestialBody> bodies;

    private double scale;
    private double timeScale;

    private Random rand;

    /**
     * Main class for running the simulation
     */
    public Simulator() {
        system = new SolarSystem(1600, 900);
        bodies = new ArrayList<CelestialBody>();
        rand = new Random();
        start();
        update();
    }

    /**
     * Initialise all objects
     */
    public void start() {
        CelestialBody sun = new CelestialBody("Sun", 1392.7, "YELLOW");
        bodies.add(sun);
        bodies.add(new Planet("Mercury", 4.8794, 88, "GRAY", sun, 57.91, 90));
        bodies.add(new Planet("Venus", 12.104, 225, "ORANGE", sun, 108.2, 90));
        bodies.add(new Planet("Earth", 12.742, 365, "BLUE", sun, 149.6, 90));
        bodies.add(new CelestialBody("Moon", 3.4742, 1, "WHITE", getBodyByName("Earth"), .384, 90));
        bodies.add(new Planet("Mars", 6.779, 687, "RED", sun, 227.9, 90));
        bodies.add(new Planet("Jupiter", 139.82, 4333, "ORANGE", sun, 778.5, 90));
        bodies.add(new Planet("Saturn", 116.46, 10759, "YELLOW", sun, 1434, 90));
        bodies.add(new Planet("Uranus", 50.724, 30688, "CYAN", sun, 2871, 90));
        bodies.add(new Planet("Neptune", 49.244, 60182, "BLUE", sun, 4495, 90));

        
    }

    /**
     * Update all objects
     */
    public void update() {
        boolean windowVisible = false;
        system.setVisible(windowVisible);

        while (true) {
            scale = system.getScale();
            timeScale = system.getTimeScale();
            system.update();

            for (CelestialBody b : bodies) {
                b.update(system);
                b.draw(system);
            }
            system.finishedDrawing();

            if (!windowVisible) system.setVisible(windowVisible = true);
        }
    }

    /**
     * Search for a CelestialBody instance with a given name
     * @param name Name of the Body being searched for
     * @return Returns an instance of CelestialBody with the passed name, or the origin if there is no Body with that name 
     */
    public CelestialBody getBodyByName(String name) {
        for (CelestialBody b : bodies)
            if (b.getName() == name)
                return b;

        return CelestialBody.ORIGIN;
    }
    
    /**
     * Search for the index of a CelestialBody instance with a given name
     * @param name Name of the Body being searched for
     * @return Returns index of the Body in the ArrayList bodies, or -1 if not found
     */
    public int getIndexByName(String name) {
        int i = 0;
        for (CelestialBody b : bodies) {
            if (b.getName() == name)
                return i;

            i++;
        }

        return -1;
    }
}
