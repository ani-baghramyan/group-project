public class Planet extends CelestialBody{
  private String atmosphericComposition;
  private int numberOfMoons;
  private String surfaceConditions;
  private double radius;

  public Planet(String name, double distanceFromEarth, double mass, double temperature, String type,
                String atmosphericComposition, int numberOfMoons, String surfaceConditions, double radius){
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
    return super.toString() +
           "Radius: " + radius + "m\n" +
           "Atmosphere: " + atmosphericComposition + "\n" +
           "Moons: " + numberOfMoons + "\n" +
           "Surface: " + surfaceConditions;
  }

  public void displayInfo(){
    System.out.println("\n--- Planet Information ---");
    System.out.println(toString());
  }
  
  public boolean equals(Object otherObject){
    if (otherObject == null)
      return false;
    else if (getClass() != otherObject.getClass())
      return false;
    else {a
      Planet otherPlanet = (Planet) otherObject;
      return getName().equals(otherPlanet.getName()); 
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
