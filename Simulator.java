import java.util.ArrayList;

public class Simulator {
    private SolarSystem system;

    private ArrayList<CelestialBody> bodies;

    private double scale;
    private double timeScale;

    /**
     * Main class for running the simulation
     */
    public Simulator() {
        system = new SolarSystem(800, 600);
        bodies = new ArrayList<CelestialBody>();
        scale = .5;
        timeScale = 365;
        start();
        update();
    }

    /**
     * Initialise all objects
     */
    public void start() {
        CelestialBody sun = new CelestialBody("Sun", 50, "YELLOW");
        bodies.add(sun);
        // bodies.add(new CelestialBody("Mercury", ))
        bodies.add(new CelestialBody("Earth", 20, 0.12f, "BLUE", CelestialBody.ORIGIN, 200, 0));
        bodies.add(new CelestialBody("Moon", 10, 10, "WHITE", getBodyByName("Earth"), 40, 0));
    }

    /**
     * Update all objects
     */
    public void update() {
        boolean windowVisible = false;
        system.setVisible(windowVisible);

        while (true) {
            scale = system.getScale();

            for (CelestialBody b : bodies) {
                b.update(timeScale);
                b.draw(system, scale);
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
