public class Planet extends CelestialBody{
  private String atmosphericComposition;
  private int numberOfMoons;
  private String surfaceConditions;

  public Planet(String name, double distanceFromEarth, double mass, double temperature, String type,
                String atmosphericComposition, int numberOfMoons, String surfaceConditions){
    super(name, distanceFromEarth, mass, temperature, type);
    this.atmosphericComposition = atmosphericComposition;
    this.numberOfMoons = numberOfMoons;
    this.surfaceConditions = surfaceConditions;
  }
  
  public Planet(Planet that){
    super(that);
    this.atmosphericComposition = that.atmosphericComposition;
    this.numberOfMoons = that.numberOfMoons;
    this.surfaceConditions = that.surfaceConditions;
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
  
  public String toString() {
    return "Planet Name: " + getName() + "\n" +
           "Type: " + getType() + "\n" +
           "Distance from Earth: " + getDistanceFromEarth() + " light years\n" +
           "Mass: " + getMass() + " kg\n" +
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
  
}
