public class CelestialBody {
    private String name;
    
    private double diameter;

    private CelestialBody centerOfOrbit;

    private double distanceToCentre;
    private double angleToOrigin;
    private double speed;

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
     * Main constructor for a CelestialBody with position, size, centre of orbit and speed
     * @param name Name of the Body
     * @param diameter Diameter of the Body
     * @param speed Angular speed of the Body
     * @param color Color of the Body
     * @param parent Body at the center of orbit
     * @param r Distance from center of orbit
     * @param th Angle from center of orbit
     */
    public CelestialBody(String name, double diameter, double speed, String color, CelestialBody parent, double r, double th) {
        this.name = name;
        this.diameter = diameter;
        this.centerOfOrbit = parent;
        this.distanceToCentre = r;
        this.angleToOrigin = th;
        this.speed = speed;
        this.color = color;
    }

    /**
     * Draws the body to the screen using SolarSystem.drawSolarObjectAbout
     * @param system The SolarSystem to draw to
     */
    public void draw(SolarSystem system) {
        system.drawSolarObjectAbout(this);
    }

    /**
     * Updates the position of the body
     */
    public void update() {
        this.angleToOrigin += this.speed;
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

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
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