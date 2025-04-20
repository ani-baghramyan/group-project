public abstract class CelestialBody implements Comparable{
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

    public int compareTo(Object other){
        CelesrialBody otherCelestialBody = (CelestialBody) other;
        return name.compareToIgnoreCase(other.name);
    }
    
    public int compareByMass(CelestialBody other){
        CelestialBody otherCelestialBody = (CelestialBody) other;
        if (mass > other.mass)
          return 1;
        else if (mass < other.mass)
          return -1;
        else 
          return 0;
  `} 
    
   public int compareByTemperature(CelestialBody other) {
        if (temperature > other.temperature)
            return 1;
        else if (temperature < other.temperature)
            return -1;
        else
            return 0;
   }

   public int compareByDistanceFromEarth(CelestialBody other) {
        if (distanceFromEarth > other.distanceFromEarth)
            return 1;
        else if (distanceFromEarth < other.distanceFromEarth)
            return -1;
        else
            return 0;
   }
         
}
