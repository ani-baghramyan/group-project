public abstract class CelestialBody implements Comparable<CelestialBody>{
    public final static double G = 6.67430e-11;
    private String name;
    private double distanceFromEarth; 
    private double mass;
    private double temperature;
    private String type;

    public CelestialBody(String name, double distanceFromEarth, double mass, double temperature, String type) {
        this.name = name;
        this.distanceFromEarth = distanceFromEarth;
        this.mass = mass;
        this.temperature = temperature;
        this.type = type;
        
    }
    public CelestialBody(String name, double distanceFromEarth, double mass, double temperature, String type) {
        this.name = name;
        this.distanceFromEarth = distanceFromEarth;
        this.mass = mass;
        this.temperature = temperature;
        this.type = type;
        
    }
    
    public CelestialBody(CelestialBody that){
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
    public abstract String showFacts();
  
    @Override
    public String toString() {
        return String.format(
                "Name: %s\n" +
                        "Type: %s\n" +
                        "Distance from Earth: %.2f light years\n" +
                        "Mass: %.2e kg\n" +  // Scientific notation for large masses
                        "Temperature: %.1f K",
                getName(),
                getType(),
                getDistanceFromEarth(),
                getMass(),
                getTemperature()
        );
    }
    
    @Override
    public boolean equals(Object otherObject) {
        if (otherObject == null || getClass() != otherObject.getClass())
            return false;
        else{
            CelestialBody that = (CelestialBody) otherObject;
            return name.equalsIgnoreCase(that.name);  // Name-based equality only
        }
    }
    
    public int compareTo(CelestialBody other){
         if (other == null) {
            throw new NullPointerException("Cannot compare to null.");
        }
        if (this.getClass() != other.getClass()) {
            throw new IllegalArgumentException("Cannot compare different types of celestial bodies.");
        }
        return name.compareToIgnoreCase(other.name);
    }
         
}
