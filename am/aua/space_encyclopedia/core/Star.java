package am.aua.space_encyclopedia.core;
public class Star extends CelestialBody {
      public static final double SUN_MASS_KG = 1.989e30;// kg
      public static final double SUN_ABSOLUTE_MAGNITUDE = 4.83; // in V band
      public static final double SUN_LIFESPAN = 10;// in billion years
      
      private String color;
      private double luminosity;  
      private double size;   
      //constructor
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
      //copy constrcutor
      public Star(Star other) {
          super(other.getName(), other.getDistanceFromEarth(), other.getMass(), other.getTemperature(), other.getType());
          if (other == null)
              throw new NullPointerException("Cannot copy from a null Star.");
          this.color = other.color;
          this.luminosity = other.luminosity;
          this.size = other.size;
      }
      //accessors
      public String getColor() {
            return color;
      }
      public double getLuminosity() {
            return luminosity;
      }
      public double getSize() {
            return size;
      }
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
       public String showFacts() {
             StringBuilder facts = new StringBuilder();
              // Header
              facts.append("\nMORE STAR FACTS:\n");
              // Basic Info
              facts.append("- ").append(getName()).append("star.\n");
              // Classification
              facts.append("\nSTELLAR AND PHYSICAL CLASSIFICATION:\n");
              facts.append("- Spectral Type: ").append(classifyStar()).append("\n");
              facts.append(String.format("- Absolute Magnitude: %.2f\n", calculateAbsoluteMagnitude()));
      
              // Lifecycle Info
              facts.append("\nLIFECYCLE INFORMATION:\n");
              facts.append("- Current Stage: ").append(calculateLifeStage()).append("\n");
              facts.append(String.format("- Main Sequence Lifespan: %.1f billion years\n",
                      estimateMainSequenceLifespan()));
      
              return facts.toString();
       }
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
      
     public String calculateLifeStage() {
        double massInKg = getMass();  // mass in kg

        // Normalize the mass to solar masses
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
      
     public double calculateAbsoluteMagnitude() {
        if (luminosity <= 0) {
            throw new IllegalArgumentException("Luminosity must be greater than zero to calculate magnitude.");
        }
        return SUN_ABSOLUTE_MAGNITUDE - 2.5 * Math.log10(luminosity);
     }

    public double estimateMainSequenceLifespan() {
        double massInKg = getMass(); // getMass() must return kg
        double massRatio = massInKg / SUN_MASS_KG;

        return SUN_LIFESPAN * Math.pow(massRatio, -2.5);
    }

}





