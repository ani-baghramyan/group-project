package am.aua.space_encyclopedia.core;
/**
 * Represents a star in the space encyclopedia.
 * A star is a luminous sphere of plasma held together by gravity and undergoes nuclear fusion.
 * This class extends <code>CelestialBody</code> and adds attributes like color, luminosity, and size.
 * <p>
 * An object of type <code>Star</code> contains:
 * <ul>
 *   <li><code>color</code> - A field of type <code>String</code> representing the visual color of the star.</li>
 *   <li><code>luminosity</code> - A field of type <code>double</code> representing the luminosity of the star relative to the Sun.</li>
 *   <li><code>size</code> - A field of type <code>double</code> representing the size of the star relative to the Sun.</li>
 * </ul>
 */
public class Star extends CelestialBody {
      /** The mass of the Sun in kilograms. */
      public static final double SUN_MASS_KG = 1.989e30; 
      /** The absolute magnitude of the Sun in the V band. */
      public static final double SUN_ABSOLUTE_MAGNITUDE = 4.83; 
      /** The expected lifespan of the Sun in billion years.*/
      public static final double SUN_LIFESPAN = 10; 
      private String color;
      private double luminosity;  //relative to the Sun
      private double size;       //relative to the Sun
 /**
     * Constructs a new Star object with specified properties.
     * @param name  the name of the star
     * @param distanceFromEarth the distance from Earth in light-years
     * @param mass  the mass of the star in kilograms
     * @param temperature  surface temperature in Kelvin
     * @param type  the type or category of star
     * @param color  visual color of the star
     * @param luminosity  brightness relative to the Sun
     * @param size  size relative to the Sun
     * @throws IllegalArgumentException  if color is null/empty, or if luminosity/size is non-positive
*/
      public Star(String name, double distanceFromEarth, double mass, double temperature, String type,
            String color, double luminosity, double size) {
          super(name, distanceFromEarth, mass, temperature, type);
      
          if (color == null || color.trim().isEmpty())
              throw new IllegalArgumentException("Color cannot be null or empty.");
          if (luminosity <= 0)
              throw new IllegalArgumentException("Luminosity must be a positive value.");
          if (size <= 0)
              throw new IllegalArgumentException("Size must be a positive value.");
      
          this.color = color;
          this.luminosity = luminosity;
          this.size = size;
      }
 /**
     * Copy constructor for a Star object.
     * @param other the Star object to copy
     * @throws NullPointerException  if the provided Star is null
     */

      public Star(Star other) {
          super(other.getName(), other.getDistanceFromEarth(), other.getMass(), other.getTemperature(), other.getType());
          if (other == null)
              throw new NullPointerException("Cannot copy from a null Star.");
          this.color = other.color;
          this.luminosity = other.luminosity;
          this.size = other.size;
      }
/**
     *@return the color of the star
*/
      public String getColor() {
            return color;
      }
/**
      *@return the luminosity of the star (relative to the Sun) 
*/
      public double getLuminosity() {
            return luminosity;
      }
/**
      *@return the size of the star (relative to the Sun) 
*/
      public double getSize() {
            return size;
      }
/**
     * Returns a formatted string representation of this star.
     * @return a human readable summary of the star's properties
*/
      @Override
      public String toString() {
            return String.format(
                  "%s\n" +
                  "Color: %s\n" +
                  "Luminosity: %.2f solar unites\n" +
                  "Size: %.2f solar radii\n",
                  super.toString(),
                  color,
                  luminosity,
                  size
            );
      }
/**
     * Checks if this star is equal to another object by name (case-insensitive).
     * @param otherObject the object to compare
     * @return true if both are stars with the same name
*/
      @Override
      public boolean equals(Object otherObject){
            if (otherObject == null)
                  return false;
            else if (getClass() != otherObject.getClass())
                  return false;
            else {
                  Star otherStar = (Star) otherObject;
                  return getName() != null && getName().equalsIgnoreCase(otherStar.getName());
            }
      }
/**
     * Provides extended information about the star, such as classification and lifecycle.
     * @return a string containing additional star facts
*/
       public String showFacts() {
             StringBuilder facts = new StringBuilder();
              facts.append("\nMORE STAR FACTS:\n");
              facts.append("\n- ").append(getName()).append(" is a shining body in space!\n");
              facts.append("\nSTELLAR AND PHYSICAL CLASSIFICATION:\n");
              facts.append("- Spectral Type: ").append(classifyStar());
              facts.append(String.format("\n- Absolute Magnitude: %.2f\n", calculateAbsoluteMagnitude()));
              facts.append("\nLIFECYCLE INFORMATION:\n");
              facts.append("- Current Stage: ").append(calculateLifeStage());
              facts.append(String.format("\n- Main Sequence Lifespan: %.1f billion years\n",
                      estimateMainSequenceLifespan()));
             
              return facts.toString();
       }
/**
     * Classifies the star based on its surface temperature and luminosity.
     * @return a string representing the spectral type and luminosity class
*/
      public String classifyStar() {
            String spectralType;

            if (getTemperature() > 30000)
                  spectralType = "O";
            else if (getTemperature() > 10000)
                  spectralType = "B";
            else if (getTemperature() > 7500)
                  spectralType = "A";
            else if (getTemperature() > 6000)
                  spectralType = "F";
            else if (getTemperature() > 5200)
                  spectralType = "G";
             else if (getTemperature() > 3700)
                  spectralType = "K";
            else
                  spectralType = "M";
            return spectralType + classifyLuminosity();
      }

/**
     * Categorizes the star into a luminosity class based on its luminosity.
     * @return a string representing the luminosity class
*/
     public String classifyLuminosity() {
        if (luminosity >= 30000)
            return "I (Supergiant)";
        else if (luminosity >= 1000)
            return "II (Bright Giant)";
        else if (luminosity >= 100)
            return "III (Giant)";
        else if (luminosity >= 10)
            return "IV (Subgiant)";
        else if (luminosity >= 0.01)
            return "V (Main-sequence)";
        else return "VII (White Dwarf)";
     }
/**
     * Estimates the life stage of the star based on its mass.
     * @return a string representing the current evolutionary stage
*/
     public String calculateLifeStage() {
        double massInKg = getMass();  // mass in kg
        double massInSolarMasses = massInKg / SUN_MASS_KG;
    
        if (massInSolarMasses < 0.08)
            return "Brown Dwarf (Failed Star)";
        else if (massInSolarMasses < 0.5)
            return "Low-Mass Main Sequence Star";
        else if (massInSolarMasses <= 8)
            return "Main Sequence Star";
        else
            return "High-Mass Main Sequence Star";
     }
/**
     * Calculates the absolute magnitude using the star's luminosity.
     * @return the absolute magnitude of the star
     * @throws IllegalArgumentException - if luminosity is not positive
*/
     public double calculateAbsoluteMagnitude() {
        if (luminosity <= 0) {
            throw new IllegalArgumentException("Luminosity must be greater than zero to calculate magnitude.");
        }
        return SUN_ABSOLUTE_MAGNITUDE - 2.5 * Math.log10(luminosity);
     }
/**
     * Estimates the duration the star will spend on the main sequence.
     * @return the lifespan in billion years
*/
    public double estimateMainSequenceLifespan() {
        double massInKg = getMass(); // getMass() must return kg
        double massRatio = massInKg / SUN_MASS_KG;

        return SUN_LIFESPAN * Math.pow(massRatio, -2.5);
    }

}
