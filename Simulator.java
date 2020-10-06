import java.util.ArrayList;

public class Simulator {
    private SolarSystem system;

    private ArrayList<CelestialBody> bodies;

    public Simulator() {
        system = new SolarSystem(800, 600);
        bodies = new ArrayList<CelestialBody>();
        start();
        update();
    }

    public void start() {
        bodies.add(new CelestialBody("Sun", 50, "YELLOW"));
        bodies.add(new CelestialBody("Earth", 20, 0.12f, "BLUE", getBodyByName("Sun"), 200, 0));
        bodies.add(new CelestialBody("Moon", 10, 10, "WHITE", getBodyByName("Earth"), 40, 0));
    }

    public void update() {
        while (true) {
            for (CelestialBody b : bodies) {
                b.update();
                b.draw(system);
            }

            system.finishedDrawing();
        }
    }

    public CelestialBody getBodyByName(String name) {
        for (CelestialBody b : bodies) {
            if (b.getName() == name)
                return b;
            }
        return CelestialBody.ORIGIN;
    }
}
