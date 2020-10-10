public class CelestialBody {
    private String name;
    
    private double diameter; //in thousand km

    private CelestialBody centerOfOrbit;

    private double distanceToCentre; //in million km
    private double angleToOrigin;
    private double speed; // 365/days
    private double lengthOfYear;

    private String color;

    /**
     * Constructor for basic Body centred on the origin
     * @param r Distance from the origin
     * @param th Angle from the origin
     */
    public CelestialBody(double r, double th) {
        this.name = "";
        this.diameter = 0;
        this.centerOfOrbit = null;
        this.distanceToCentre = r;
        this.angleToOrigin = th;
        this.speed = 0;
        this.lengthOfYear = 0;
        this.color = "BLACK";
    }

    /**
     * Constructor for a Body with no speed, at the origin
     * @param name Name of the Body
     * @param diameter Diameter of the Body
     * @param color Color of the Body
     */
    public CelestialBody(String name, double diameter, String color) {
        this(name, diameter, 0, color, ORIGIN, 0, 0);
    }

    /**
     * Main constructor for a CelestialBody with position, size, centre of orbit and speed. Adds parent radius by default
     * @param name Name of the Body
     * @param diameter Diameter of the Body
     * @param speed Angular speed of the Body
     * @param color Color of the Body
     * @param parent Body at the center of orbit
     * @param r Distance from edge of center of orbit
     * @param th Angle from center of orbit
     */
    public CelestialBody(String name, double diameter, double lengthOfYear, String color, CelestialBody parent, double r, double th) {
        this(name, diameter, lengthOfYear, color, parent, r, th, true);
    }

    /**
     * Constructor with option to not add parent radius
     * @param name Name of the Body
     * @param diameter Diameter of the Body
     * @param speed Angular speed of the Body
     * @param color Color of the Body
     * @param parent Body at the center of orbit
     * @param r Distance from edge of center of orbit
     * @param th Angle from center of orbit
     * @param addParentRadius Whether to add the radius of the parent onto the distance from the center or not
     */
    public CelestialBody(String name, double diameter, double lengthOfYear, String color, CelestialBody parent, double r, double th, boolean addParentRadius) {
        this.name = name;
        this.diameter = diameter;
        this.centerOfOrbit = parent;
        this.distanceToCentre = (addParentRadius) ? r + parent.getDiameter() / 2 : r;
        this.angleToOrigin = th;
        this.speed = 365/lengthOfYear;
        this.lengthOfYear = lengthOfYear;
        this.color = color;
    }

    /**
     * Draws the body to the screen using SolarSystem.drawSolarObjectAbout
     * @param system The SolarSystem to draw to
     */
    public void draw(SolarSystem system) {
        double scale = system.getScale();
        system.drawSolarObjectAbout(this.getScaledBody(scale));
    }

    /**
     * Creates a temporary instance of the body, with dimensions scaled to screen dimensions rather than actual
     * @param scale Current screen scale/zoom
     * @return CelestialBody with scaled diameter and distance
     */
    public CelestialBody getScaledBody(double scale) {
        CelestialBody scaledParent;
        if (this.centerOfOrbit != CelestialBody.ORIGIN)
            scaledParent = this.centerOfOrbit.getScaledBody(scale);
        else
            scaledParent = CelestialBody.ORIGIN;

        CelestialBody scaled = new CelestialBody(
            this.name, this.diameter * scale,
            this.lengthOfYear, this.color,
            scaledParent,
            this.distanceToCentre * scale, this.angleToOrigin
        );
        return scaled;
    }

    /**
     * Updates the position of the body
     */
    public void update(SolarSystem system) {
        if (this.lengthOfYear != 0)
            this.angleToOrigin += system.getTimeScale() / this.lengthOfYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    public CelestialBody getCenterOfOrbit() {
        return centerOfOrbit;
    }

    public void setCenterOfOrbit(CelestialBody centerOfOrbit) {
        this.centerOfOrbit = centerOfOrbit;
    }

    public double getDistanceToCentre() {
        return distanceToCentre;
    }

    public void setDistanceToCentre(double distanceToCentre) {
        this.distanceToCentre = distanceToCentre;
    }

    public double getAngleToOrigin() {
        return angleToOrigin;
    }

    public void setAngleToOrigin(double angleToOrigin) {
        this.angleToOrigin = angleToOrigin;
    }

    public double getLengthOfYear() {
        return lengthOfYear;
    }

    public void setLengthOfYear(double lengthOfYear) {
        this.lengthOfYear = lengthOfYear;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    /**
     * Constant for the Origin - (0, 0)
     */
    public static CelestialBody ORIGIN = new CelestialBody(0, 0);
}