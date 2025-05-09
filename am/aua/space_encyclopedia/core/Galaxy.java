package am.aua.space_encyclopedia.core;
import java.util.ArrayList;

/**
   * Represents a galaxy in the space encyclopedia.
   * A galaxy is a massive system consisting of stars, gas, dust, and dark matter—held together by gravity.
   * This class extends the CelestialBody class and adds galaxy-specific attributes like number of stars, diameter, and age.
   * <p>
   * An object of type {@code Galaxy} contains:
   * <ul>
   *   <li>{@link #numberOfStars} - A field of type {@link long} representing the estimated number of stars in the galaxy.</li>
   *   <li>{@link #diameter} - A field of type {@link double} representing the diameter of the galaxy in light-years.</li>
   *   <li>{@link #age} - A field of type {@link double} representing the age of the galaxy in billions of years.</li>
   * </ul>
 */

public class Galaxy extends CelestialBody {
/**
    *Constant to convert light-years to meters. Useful for physical calculations.
*/
    public static final double LIGHT_YEAR_IN_METERS = 9.461e15;
    
    private long numberOfStars;
    private double diameter;
    private double age;

/**
     * Constructs a new Galaxy with all necessary physical and descriptive attributes.
     * @param name  the name of the galaxy
     * @param distanceFromEarth  distance from Earth in light-years
     * @param mass  the galaxy's total mass in kilograms
     * @param temperature  average temperature in Kelvin
     * @param type  type/classification (e.g., "Spiral", "Elliptical")
     * @param numberOfStars  approximate number of stars
     * @param diameter  diameter in light-years
     * @param age  age in billions of years
     * @throws IllegalArgumentException if any value is invalid (e.g., negative age or zero diameter)
*/
    public Galaxy(String name, double distanceFromEarth, double mass, double temperature, String type,
              long numberOfStars, double diameter, double age) {
        super(name, distanceFromEarth, mass, temperature, type);

        if (numberOfStars < 0)
            throw new IllegalArgumentException("Number of stars cannot be negative.");
        if (diameter <= 0)
            throw new IllegalArgumentException("Diameter must be positive.");
        if (age <= 0)
            throw new IllegalArgumentException("Age must be positive.");

        this.numberOfStars = numberOfStars;
        this.diameter = diameter;
        this.age = age;
    
    }

/**
     * Copy constructor for Galaxy.
     * Creates a new instance using values from another Galaxy object.
     * @param other the Galaxy to copy
     * @throws NullPointerException if the input Galaxy is null
*/
    public Galaxy(Galaxy other) {
        super(other.getName(), other.getDistanceFromEarth(), other.getMass(), other.getTemperature(), other.getType());
        if (other == null)
            throw new NullPointerException("Cannot copy from a null Galaxy.");
        this.numberOfStars = other.numberOfStars;
        this.diameter = other.diameter;
        this.age = other.age;
}
   
/**
     *@return estimated number of stars in this galaxy 
*/
    public long getNumberOfStars() {
        return numberOfStars;
    }
/**
    *@return the diameter of the galaxy in light-years
*/
    public double getDiameter() {
        return diameter;
    }
/**
    *@return the age of the galaxy in billions of years 
*/
    public double getAge() {
        return age;
    }
    
/**
     * Provides a complete string representation of the galaxy, including inherited and extra attributes.
     * @return formatted string describing the galaxy
*/
    @Override
    public String toString() {
        return String.format(
                        "%s\n" +
                        "Diameter: %.2f light-years\n" +
                        "Number of Stars: %,d\n" +
                        "Age: %.2f billion years\n",
                super.toString(),
                diameter,
                numberOfStars,
                age
        );

    }

/**
     * Compares two galaxies by their name (case-insensitive).
     * @param otherObject the object to compare with
     * @return true if the names match and it's also a Galaxy
*/
    @Override
    public boolean equals(Object otherObject){
        if (otherObject == null)
            return false;
        else if (getClass() != otherObject.getClass())
            return false;
        else {
            Galaxy otherGalaxy = (Galaxy) otherObject;
            return getName() != null && getName().equalsIgnoreCase(otherGalaxy.getName());
        }
    }
/**
     * Estimates how densely stars are packed in this galaxy.
     * Calculation assumes a spherical volume.
     * @return stars per cubic light-year
     * @throws ArithmeticException  if the galaxy volume is zero or invalid
*/
    
    public double estimateStarDensity() {
        double radiusLY = diameter / 2.0; // diameter in light-years
        double volume = (4.0 / 3.0) * Math.PI * Math.pow(radiusLY, 3); // in cubic light-years
        if (volume <= 0)
            throw new ArithmeticException("Galaxy volume must be greater than zero to estimate star density.");
        return numberOfStars / volume;
    }
/**
     * Calculates the escape velocity at the edge of the galaxy.
     * Uses classical mechanics (v = sqrt(2GM/R)).
     * @return escape velocity in meters per second
     * @throws ArithmeticException  if the radius is zero or negative
*/
    
   public double calculateEscapeVelocity() {
    double radiusMeters = (diameter * LIGHT_YEAR_IN_METERS) / 2;
    if (radiusMeters <= 0)
        throw new ArithmeticException("Diameter must be greater than zero to calculate escape velocity.");

    return Math.sqrt((2 * G * getMass()) / radiusMeters);
}
/**
     * Returns additional interesting facts about the galaxy.
     * Includes calculated values like star density and escape velocity.
     * @return formatted string with galaxy-specific facts
*/
    
    @Override
    public String showFacts(){
        StringBuilder facts = new StringBuilder();

        facts.append("\nMORE GALACTIC FACTS:\n");
        facts.append("\n- ").append(getName()).append(" is a fascinating galaxy!\n");
        facts.append(String.format("- Estimated Star Density: %.3f stars per cubic light-year\n", estimateStarDensity()));
        facts.append(String.format("- Escape Velocity (at edge): %.2f m/s\n", calculateEscapeVelocity()));

        return facts.toString();
    }
 

}
