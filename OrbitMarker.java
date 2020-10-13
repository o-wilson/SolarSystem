//Not really a "CelestialBody" as such but oh well
public class OrbitMarker extends CelestialBody {
    private CelestialBody parent;
    
    public OrbitMarker(CelestialBody parent, double timeScale) {
        super(
            "", 2, 0, "GRAY",
            parent.getCenterOfOrbit(),
            parent.getDistanceToCentre(),
            parent.getAngleToOrigin(), false
        );

        this.parent = parent;
    }

    @Override
    public void draw(SolarSystem system) {
        system.drawSolarObjectAbout(this, new boolean[] {false, true});
    }
}