package am.aua.space_encyclopedia.core;

public class Planet extends CelestialBody{
  private String atmosphericComposition;
  private int numberOfMoons;
  private String surfaceConditions;
  private double radius;

  //constructor
  public Planet(String name, double distanceFromEarth, double mass, double temperature, String type,
              String atmosphericComposition, int numberOfMoons, String surfaceConditions, double radius) {
    super(name, distanceFromEarth, mass, temperature, type);

    if (atmosphericComposition == null || atmosphericComposition.trim().isEmpty())
        throw new IllegalArgumentException("Atmospheric composition cannot be null or empty.");
    if (numberOfMoons < 0)
        throw new IllegalArgumentException("Number of moons cannot be negative.");
    if (surfaceConditions == null || surfaceConditions.trim().isEmpty())
        throw new IllegalArgumentException("Surface conditions cannot be null or empty.");
    if (radius <= 0)
        throw new IllegalArgumentException("Radius must be a positive value.");

    this.atmosphericComposition = atmosphericComposition;
    this.numberOfMoons = numberOfMoons;
    this.surfaceConditions = surfaceConditions;
    this.radius = radius;
}
  //copy constructor
 public Planet(Planet other) {
    super(other);
    if (other == null)
        throw new NullPointerException("Cannot copy from a null Planet.");

    this.atmosphericComposition = other.atmosphericComposition;
    this.numberOfMoons = other.numberOfMoons;
    this.surfaceConditions = other.surfaceConditions;
    this.radius = other.radius;
}
  //accessors
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
  //other methods
  @Override
  public String toString() {
    return String.format(
            "%s\n" +
                    "Radius: %.1f km\n" +
                    "Atmosphere: %s\n" +
                    "Moons: %d\n" +
                    "Surface Conditions: %s",
            super.toString(),
            radius / 1000, // convert from m to km
            atmosphericComposition,
            numberOfMoons,
            surfaceConditions
    );
  }
  
  @Override
  public boolean equals(Object otherObject){
    if (otherObject == null)
      return false;
    else if (getClass() != otherObject.getClass())
      return false;
    else {
      Planet otherPlanet = (Planet) otherObject;
      return getName() != null && getName().equalsIgnoreCase(otherPlanet.getName()); 
    }    
  }
  @Override
  public String showFacts(){
    StringBuilder facts = new StringBuilder();

    // Header
    facts.append("\nMORE PLANETARY FACTS:\n");

    facts.append("- ").append(getName()).append(" is a fascinating world!\n");

    // Physical Properties
    facts.append("\nPHYSICAL CHARACTERISTICS:\n");
    facts.append(String.format("- Surface Gravity: %.2f m/s²\n", calculateSurfaceGravity()));

    // Moons
    facts.append("\nSATELLITE SYSTEM:\n");
    facts.append("- Number of Moons: ").append(numberOfMoons).append("\n");
    facts.append("- Moon Category: ").append(moonCategory()).append("\n");

    return facts.toString();
  }
  
  public double calculateSurfaceGravity() {
    if (radius <= 0)
        throw new ArithmeticException("Radius must be positive to calculate surface gravity.");
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
