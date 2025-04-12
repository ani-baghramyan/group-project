public abstract class CelestialBody {
    private String name;
    private double distanceFromEarth; 
    private double mass;
    private double temperature;
    private String type;

    public CelestialBody(String name, double distanceFromEarth, double name, double temperature, String type) {
        this.name = name;
        this.distanceFromEarth = distanceFromEarth;
        this.mass = mass;
        this.temperature = temperature;
        this.type = type;
        
    }
    
    protected CelestialBody(CelestialBody that){
        this.name = that.name;
        this.distanceFromEarth = that.distanceFromEarth;
        this.mass = that.mass;
        this.temperature = that.temperature;
        this.type = that.type;
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

    public String getType() {
        return type;
    }
    
    public abstract void displayInfo();
}
