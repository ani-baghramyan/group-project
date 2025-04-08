public abstract class CelestialBody {
    protected String name;
    protected double distanceFromEarth; 

    public CelestialBody(String name, double distanceFromEarth) {
        this.name = name;
        this.distanceFromEarth = distanceFromEarth;
    }

    public String getName() {
        return name;
    }

    public double getDistanceFromEarth() {
        return distanceFromEarth;
    }

    public abstract void displayInfo();
}
