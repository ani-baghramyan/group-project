public class Star extends CelestialBody {
      
    private String color;
    private double luminosity;  
    private double size;    

    public Star(String name, double distanceFromEarth, String type, double temperature, double mass,
                String color, double luminosity, double size) {
        super(name, distanceFromEarth, mass, temperature, type);
        this.color = color;
        this.luminosity = luminosity;
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getLuminosity() {
        return luminosity;
    }

    public void setLuminosity(double luminosity) {
        this.luminosity = luminosity;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String classifyStar() {
        if (getTemperature() >= 30000) return "O-type";
        else if (getTemperature() >= 10000) 
            return "B-type";
        else if (getTemperature() >= 7500) 
            return "A-type";
        else if (getTemperature() >= 6000) 
            return "F-type";
        else if (getTemperature() >= 5200) 
            return "G-type";
        else if (getTemperature() >= 3700) 
            return "K-type";
        else return "M-type";
    }
  
   @Override
    public String toString() {
        return "Star: " + getName() + "\n" +
               "Distance from Earth: " + getDistanceFromEarth() + " light years\n" +
               "Type: " + getType() + "\n" +
               "Temperature: " + getTemperature() + " K\n" +
               "Mass: " + getMass() + " solar masses\n" +
               "Color: " + color + "\n" +
               "Luminosity: " + luminosity + " solar luminosities\n" +
               "Size: " + size + " solar radii\n" +
               "Spectral Class: " + classifyStar();
    }

   public void displayInfo(){
       System.out.println("\n---Star Information ---");
       System.out.println(toString());
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

    public String getLifeStage() {
    if (getMass() < 0.08)
        return "Brown Dwarf (Failed Star)";
    else if (getMass() < 0.5)
        return "Main Sequence (Low Mass)";
    else if (getMass() <= 8)
        return "Main Sequence";
    else
        return "High-Mass Star (Short Lifespan)";
}

    public double calculateAbsoluteMagnitude() {
        return 4.83 - 2.5 * Math.log10(luminosity);
}

    public boolean isInHabitableZone(double orbitDistanceAU) {
        return orbitDistanceAU >= 0.95 && orbitDistanceAU <= 1.37 && classifyStar().equals("G-type");
}

    public double estimateMainSequenceLifespan() {
        return 10 * (1 / Math.pow(getMass(), 2.5)); 
}
}





