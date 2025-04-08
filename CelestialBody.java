public abstract class CelestialBody {
    protected String name;
    protected double distanceFromEarth; 
    private double mass;
    private double temperature;

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
    
    public double getMass(){
        return mass;
    }

    public double getTemperature(){
        return temperature;
    }
    public abstract void displayInfo();
}
