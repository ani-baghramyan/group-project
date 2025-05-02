package am.aua.space_encyclopedia.core;
import java.util.ArrayList;
public class Galaxy extends CelestialBody {
    public static final double LIGHT_YEAR_IN_METERS = 9.461e15;
    
    private long numberOfStars;
    private double diameter;
    private double age;
    //constuctor
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

    //copy constructor
    public Galaxy(Galaxy other) {
    super(
        other != null ? other.getName() : null,
        other != null ? other.getDistanceFromEarth() : 0,
        other != null ? other.getMass() : 0,
        other != null ? other.getTemperature() : 0,
        other != null ? other.getType() : null
    );

    if (other == null)
        throw new NullPointerException("Cannot copy from a null Galaxy.");

    this.numberOfStars = other.numberOfStars;
    this.diameter = other.diameter;
    this.age = other.age;
}
   
    //accessors
    public long getNumberOfStars() {
        return numberOfStars;
    }
    public double getDiameter() {
        return diameter;
    }
    public double getAge() {
        return age;
    }
    
    //other methods
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
    public double estimateStarDensity() {
        double radiusLY = diameter / 2.0; // diameter in light-years
        double volume = (4.0 / 3.0) * Math.PI * Math.pow(radiusLY, 3); // in cubic light-years
        if (volume <= 0)
            throw new ArithmeticException("Galaxy volume must be greater than zero to estimate star density.");
        return numberOfStars / volume;
    }
   public double calculateEscapeVelocity() {
    double radiusMeters = (diameter * LIGHT_YEAR_IN_METERS) / 2;
    if (radiusMeters <= 0)
        throw new ArithmeticException("Diameter must be greater than zero to calculate escape velocity.");

    return Math.sqrt((2 * G * getMass()) / radiusMeters);
}
    @Override
    public String showFacts(){
        StringBuilder facts = new StringBuilder();

        facts.append("\nMORE GALACTIC FACTS:\n");
        facts.append("- ").append(getName()).append(" is a fascinating galaxy!\n");
        facts.append(String.format("- Estimated Star Density: %.3f stars per cubic light-year\n", estimateStarDensity()));
        facts.append(String.format("- Escape Velocity (at edge): %.2f m/s\n", calculateEscapeVelocity()));

        return facts.toString();
    }
 

}
