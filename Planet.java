public class Planet extends CelestialBody{
  private String atmosphericComposition;
  private int numberOfMoons;
  private String surfaceConditions;
  private double radius;

  public Planet(String name, double distanceFromEarth, double mass, double temperature, String type,
                String atmosphericComposition, int numberOfMoons, String surfaceConditions, int radius){
    super(name, distanceFromEarth, mass, temperature, type);
    this.atmosphericComposition = atmosphericComposition;
    this.numberOfMoons = numberOfMoons;
    this.surfaceConditions = surfaceConditions;
    this.radius = radius;
  }
  
  public Planet(Planet that){
    super(that);
    this.atmosphericComposition = that.atmosphericComposition;
    this.numberOfMoons = that.numberOfMoons;
    this.surfaceConditions = that.surfaceConditions;
    this.radius = that.radius;
  }
  
  public String getAtmosphericComposition() {
    return atmosphericComposition;
  }
  
  public int getNumberOfMoons() {
    return numberOfMoons;
  }

  public String getSurfaceConditions() {
    return surfaceConditions;
  }

  public double getRadius(){
    return radius;
  }
  
  public String toString() {
    return "Planet Name: " + getName() + "\n" +
           "Type: " + getType() + "\n" +
           "Distance from Earth: " + getDistanceFromEarth() + " light years\n" +
           "Mass: " + getMass() + " kg\n" +
           "Radius: " + radius + "m\n" +
           "Temperature: " + getTemperature() + " K\n" +
           "Atmosphere: " + atmosphericComposition + "\n" +
           "Moons: " + numberOfMoons + "\n" +
           "Surface: " + surfaceConditions + "\n";
  }

  public void displayInfo(){
    System.out.println("\n--- Planet Information ---");
    System.out.println(toString());
  }
  
  public boolean equals(Object otherObject){
    if (otherObject == null)
      return false;
    else if (getClass != otherObject.getClass)
      return false;
    else {
      Planet otherPlanet = (Planet) otherObject;
      return getName().equals(otherObject.getName); 
    }    
  }

   public int compareByNumberOfMoons(Planet other) {
        if (numberOfMoons > other.numberOfMoons)
            return 1;
        else if (numberOfMoons < other.numberOfMoons)
            return -1;
        else
            return 0;
   }
  
   public double calculateSurfaceGravity(){
     double G = 6.67430e-11;
     return (G * getMass()) / (radius * radius);
   }
  
   public String moonCategory() {
     if (numberOfMoons == 0) 
       return "Moonless";
     else if (numberOfMoons <= 2) 
       return "Few Moons";
     else if (numberOfMoons <= 10)
       return "Multi-Mooned";
     else
       return "Satellite Swarm";
   }
  
}
