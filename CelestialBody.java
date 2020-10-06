public class CelestialBody {
    private String name;
    
    private float diameter;

    private CelestialBody centerOfOrbit;

    private float distanceToCentre;
    private float angleToOrigin;
    private float speed;

    private String color;

    public CelestialBody(float r, float th) {
        this.name = "";
        this.diameter = 0;
        this.centerOfOrbit = null;
        this.distanceToCentre = r;
        this.angleToOrigin = th;
        this.speed = 0;
        this.color = "BLACK";
    }

    public CelestialBody(String name, float diameter, String color) {
        this(name, diameter, 0, color, ORIGIN, 0, 0);
    }

    public CelestialBody(String name, float diameter, float speed, String color, CelestialBody parent) {
        this(name, diameter, speed, color, parent, 0, 0);
    }

    public CelestialBody(String name, float diameter, float speed, String color, CelestialBody parent, float r, float th) {
        this.name = name;
        this.diameter = diameter;
        this.centerOfOrbit = parent;
        this.distanceToCentre = r;
        this.angleToOrigin = th;
        this.speed = speed;
        this.color = color;
    }

    public void draw(SolarSystem system) {
        system.drawSolarObjectAbout(
            distanceToCentre, angleToOrigin, diameter, color,
            centerOfOrbit.getDistanceToCentre(),
            centerOfOrbit.getAngleToOrigin()
        );
    }

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



    public static CelestialBody ORIGIN = new CelestialBody(0, 0);
}