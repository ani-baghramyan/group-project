public abstract class CelestialBody implements Comparable<CelestialBody>{
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

    public String toString(){
        return ("Name: " + getName() + "\n" +
                "Type: " + getType() + "\n" +
                "Distance from Earth: " + getDistanceFromEarth() + " light years\n" +
                "Mass: " + getMass() + " kg\n" +
                "Temperature: " + getTemperature() + " K\n");
    }
    
    public int compareTo(CelestialBody other){
        return name.compareToIgnoreCase(other.name);
    }
    
    private void validateComparison(CelestialBody other) {
        if (other == null) {
            throw new NullPointerException("Cannot compare to null.");
        }
        if (this.getClass() != other.getClass()) {
            throw new IllegalArgumentException("Cannot compare different types of celestial bodies.");
        }
    }
    
    public int compareByMass(CelestialBody other){
        validateComparison(other);
        return Double.compare(mass, other.mass);
  `} 
    
   public int compareByTemperature(CelestialBody other) {
       validateComparison(other);
       return Double.compare(temperature, other.temperature);
   }

   public int compareByDistanceFromEarth(CelestialBody other) {
       validateComparison(other);
       return Double.compare(distanceFromEarth, other.distanceFromEarth);
   }
         
}
