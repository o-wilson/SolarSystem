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
    public CelestialBody getScaledBody(double scale) {
        CelestialBody scaledParent;
        if (this.getCenterOfOrbit() != CelestialBody.ORIGIN)
            scaledParent = this.getCenterOfOrbit().getScaledBody(scale);
        else
            scaledParent = CelestialBody.ORIGIN;

        CelestialBody scaled = new CelestialBody(
            this.getName(), this.getDiameter(),
            this.getLengthOfYear(), this.getColor(),
            scaledParent,
            this.getDistanceToCentre() * scale, this.getAngleToOrigin()
        );
        return scaled;
    }
}