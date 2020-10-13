import java.util.ArrayList;

public class Planet extends CelestialBody {
    private double orbitTrack;
    private double orbitStep;

    private ArrayList<OrbitMarker> orbitMarkers;
    
    public Planet(String name, double diameter, double lengthOfYear, String color, CelestialBody parent, double r, double th) {
        super(name, diameter, lengthOfYear, color, parent, r, th);

        orbitTrack = 0;
        orbitStep = 0;

        orbitMarkers = new ArrayList<OrbitMarker>();
    }

    @Override
    public void update(SolarSystem system) {
        super.update(system);
        double timeScale = system.getTimeScale();

        orbitTrack += timeScale / this.getLengthOfYear();
        orbitStep += timeScale / this.getLengthOfYear();
        if (orbitStep >= 2 && orbitTrack <= 360) {
            orbitStep %= 2;
            orbitMarkers.add(new OrbitMarker(this, timeScale));
        }
    }

    @Override
    public void draw(SolarSystem system) {
        for (OrbitMarker o : orbitMarkers)
            if (getDiameter() * system.getScale() >= 2)
                o.draw(system);

        //super called after so that planet is drawn on top of trail
        super.draw(system);
    }
}