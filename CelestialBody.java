public class CelestialBody {
    private String name;
    
    private float diameter;

    private CelestialBody centerOfOrbit;

    private float distanceToCentre;
    private float angleToOrigin;
    private float speed;

    private String color;

    /**
     * Constructor for basic Body centred on the origin
     * @param r Distance from the origin
     * @param th Angle from the origin
     */
    public CelestialBody(float r, float th) {
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
    public CelestialBody(String name, float diameter, String color) {
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
    public CelestialBody(String name, float diameter, float speed, String color, CelestialBody parent, float r, float th) {
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
        system.drawSolarObjectAbout(
            distanceToCentre, angleToOrigin, diameter, color,
            centerOfOrbit.getDistanceToCentre(),
            centerOfOrbit.getAngleToOrigin()
        );
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

    public float getDiameter() {
        return diameter;
    }

    public void setDiameter(float diameter) {
        this.diameter = diameter;
    }

    public CelestialBody getCenterOfOrbit() {
        return centerOfOrbit;
    }

    public void setCenterOfOrbit(CelestialBody centerOfOrbit) {
        this.centerOfOrbit = centerOfOrbit;
    }

    public float getDistanceToCentre() {
        return distanceToCentre;
    }

    public void setDistanceToCentre(float distanceToCentre) {
        this.distanceToCentre = distanceToCentre;
    }

    public float getAngleToOrigin() {
        return angleToOrigin;
    }

    public void setAngleToOrigin(float angleToOrigin) {
        this.angleToOrigin = angleToOrigin;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
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