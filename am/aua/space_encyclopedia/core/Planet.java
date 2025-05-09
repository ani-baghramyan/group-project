package am.aua.space_encyclopedia.core;
/**
   * Represents a planet in the space encyclopedia.
   * A planet is a celestial body that orbits a star, has cleared its orbit, and possesses characteristics like an atmosphere, surface, and moons.
   * This class extends the CelestialBody class and adds planet-specific attributes.
   * <p>
   * An object of type {@code Planet} contains:
   * <ul>
   *   <li>{@link #atmosphericComposition} - A field of type {@link String} representing the composition of the planet's atmosphere.</li>
   *   <li>{@link #numberOfMoons} - A field of type {@link int} representing the number of natural satellites (moons) orbiting the planet.</li>
   *   <li>{@link #surfaceConditions} - A field of type {@link String} representing a description of the surface conditions on the planet (e.g., rocky, gaseous).</li>
   *   <li>{@link #radius} - A field of type {@link double} representing the radius of the planet in meters.</li>
   * </ul>
 */

public class Planet extends CelestialBody{
  private String atmosphericComposition;
  private int numberOfMoons;
  private String surfaceConditions;
  private double radius;//in meters

  /**
     *Constructs a new Planet with detailed physical and descriptive information.
     * @param name  name of the planet
     * @param distanceFromEarth  distance from Earth in light-years
     * @param mass  mass in kilograms
     * @param temperature  average surface temperature in Kelvin
     * @param type  classification (e.g., "Terrestrial", "Gas Giant")
     * @param atmosphericComposition  description of the atmosphere
     * @param numberOfMoons  number of natural satellites
     * @param surfaceConditions  description of the surface environment
     * @param radius  radius in meters
     * @throws IllegalArgumentException  if any argument is invalid
  */

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

/**
     * Copy constructor for Planet.
     * Creates a new instance using values from another Planet object.
     * @param other the Planet to copy
     * @throws NullPointerException  if the input is null
*/

 public Planet(Planet other) {
    super(other);
    if (other == null)
        throw new NullPointerException("Cannot copy from a null Planet.");

    this.atmosphericComposition = other.atmosphericComposition;
    this.numberOfMoons = other.numberOfMoons;
    this.surfaceConditions = other.surfaceConditions;
    this.radius = other.radius;
}
/**
     *@return the planet's atmospheric composition 
*/
  public String getAtmosphericComposition() {
    return atmosphericComposition;
  }
/**
    *@return the number of natural satellites (moons) 
*/
  public int getNumberOfMoons() {
    return numberOfMoons;
  }
/**
   *@return description of the planet’s surface conditions 
*/
  public String getSurfaceConditions() {
    return surfaceConditions;
  }
/**
   *@return the planet's radius in meters 
*/
  public double getRadius(){
    return radius;
  }
  /**
     * Returns a formatted string representing the planet and its properties.
     * @return descriptive string of the planet
     */
  @Override
  public String toString() {
    return String.format(
            "%s\n" +
                    "Radius: %.1f km\n" +
                    "Atmosphere: %s\n" +
                    "Moons: %d\n" +
                    "Surface Conditions: %s",
            super.toString(),
            radius / 1000, // converts from m to km
            atmosphericComposition,
            numberOfMoons,
            surfaceConditions
    );
  }
  /**
     *Compares two planets by their names (case-insensitive).
     * @param otherObject the object to compare
     * @return true if both are Planets and have the same name
     */
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
   /**
     * Displays extended facts about the planet.
     * Includes surface gravity and a fun moon category.
     * @return formatted string of additional planetary facts
     */
  @Override
  public String showFacts(){
    StringBuilder facts = new StringBuilder();
    facts.append("\nMORE PLANETARY FACTS:\n");
    facts.append("- ").append(getName()).append(" is a fascinating world!\n");
    facts.append("\nPHYSICAL CHARACTERISTICS:\n");
    facts.append(String.format("- Surface Gravity: %.2f m/s²\n", calculateSurfaceGravity()));
    facts.append("\nSATELLITE SYSTEM:\n");
    facts.append("- Number of Moons: ").append(numberOfMoons).append("\n");
    facts.append("- Moon Category: ").append(moonCategory()).append("\n");

    return facts.toString();
  }
  /**
     * Calculates the surface gravity based on the planet’s mass and radius.
     * Uses the formula: g = G * mass / radius^2
     * @return surface gravity in m/s^2
     * @throws ArithmeticException  if radius is zero or negative
     */
  public double calculateSurfaceGravity() {
    if (radius <= 0)
        throw new ArithmeticException("Radius must be positive to calculate surface gravity.");
    return (G * getMass()) / (radius * radius);
}
   /**
      * Categorizes the planet based on its number of moons.
      * @return a descriptive label (e.g., "Few Moons", "Satellite Swarm")
     */
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
