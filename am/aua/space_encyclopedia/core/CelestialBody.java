package am.aua.space_encyclopedia.core;

/**
 * Represents a general celestial body in space—like a star, planet, or galaxy.
 * This is an abstract class, so you can't create a generic CelestialBody directly instead, you extend this class to build more specific objects. 
 * It includes shared properties such as name, distance from Earth, mass, temperature, and type. It also defines behavior that all celestial bodies should follow.
 */
public abstract class CelestialBody implements Comparable<CelestialBody>{
    /**
    *Gravitational constant (used for any physics-based calculations if needed)
    */
    public final static double G = 6.67430e-11;
    private String name;
    private double distanceFromEarth; 
    private double mass;
    private double temperature;
    private String type;

    /**
     * Constructs a new CelestialBody with the given properties.
     * @param name - the name of the celestial body (must not be null or empty)
     * @param distanceFromEarth- how far it is from Earth in light years (must be ≥ 0)
     * @param mass -its mass in kilograms (must be > 0)
     * @param temperature - its surface temperature in Kelvin (must be ≥ 0)
     * @param type - what kind of object it is (e.g., "Star", "Planet")
     * @throws IllegalArgumentException - if any input is invalid
     */

public CelestialBody(String name, double distanceFromEarth, double mass, double temperature, String type) {
    if (name == null || name.trim().isEmpty())
        throw new IllegalArgumentException("Name cannot be null or empty.");
    if (distanceFromEarth < 0)
        throw new IllegalArgumentException("Distance from Earth cannot be negative.");
    if (mass <= 0)
        throw new IllegalArgumentException("Mass must be positive.");
    if (temperature < 0)
        throw new IllegalArgumentException("Temperature cannot be negative.");
    if (type == null || type.trim().isEmpty())
        throw new IllegalArgumentException("Type cannot be null or empty.");

    this.name = name;
    this.distanceFromEarth = distanceFromEarth;
    this.mass = mass;
    this.temperature = temperature;
    this.type = type;
}

/**
     * Copy constructor - for creating a new CelestialBody based on another one.
     * @param other the celestial body to copy
     * @throws IllegalArgumentException -if other is null
     */
    
public CelestialBody(CelestialBody other) {
    if (other == null)
        throw new IllegalArgumentException("Cannot copy from a null CelestialBody.");

    this.name = other.name;
    this.distanceFromEarth = other.distanceFromEarth;
    this.mass = other.mass;
    this.temperature = other.temperature;
    this.type = other.type;
}
    /**
    *@return the name of this celestial body 
    */
    public String getName() {
        return name;
    }
/**
*@return how far this body is from Earth, in light years
*/
    public double getDistanceFromEarth() {
        return distanceFromEarth;
    }
    /**
    *@return the mass of this body in kilograms 
    */
    public double getMass(){
        return mass;
    }
/**
*@return the temperature in Kelvin 
*/
    public double getTemperature(){
        return temperature;
    }
/**
*@return the type of this object (like "Star", "Planet", etc.)
*/
    public String getType() {
        return type;
    }
    /**
     * This method must be implemented by any subclass to show extra facts specific to that type of celestial body.
     * @return a string containing interesting or specific facts
     */
    
    public abstract String showFacts();

    /**
     * Returns a formatted summary of the celestial body's main properties.
     * @return string representation of this body
     */
    @Override
    public String toString() {
        return String.format(
                "Name: %s\n" +
                        "Type: %s\n" +
                        "Distance from Earth: %.2f light years\n" +
                        "Mass: %.2e kg\n" +  // Scientific notation for large masses
                        "Temperature: %.1f K",
                getName(),
                getType(),
                getDistanceFromEarth(),
                getMass(),
                getTemperature()
        );
    }
    /**
     * Checks if this object is equal to another, based only on their name (case-insensitive).
     * @param otherObject - the object to compare to
     * @return true if the names match and the other object is a CelestialBody of the same type
     */
    @Override
    public boolean equals(Object otherObject) {
        if (otherObject == null || getClass() != otherObject.getClass())
            return false;
        else{
            CelestialBody that = (CelestialBody) otherObject;
            if (this.name == null || that.name == null)
                return false;
            return name.equalsIgnoreCase(that.name);  // Name-based equality only
        }
    }
     /**
     * Compares two celestial bodies by their names (case-insensitive). 
     * Only objects of the same subclass type (e.g., two Stars or two Planets) can be compared.
     * @param other the other celestial body to compare to
     * @return a negative number, zero, or a positive number depending on name order
     * @throws NullPointerException - if other is null
     * @throws IllegalArgumentException - if types are different
     */
    public int compareTo(CelestialBody other){
         if (other == null) {
            throw new NullPointerException("Cannot compare to null.");
        }
        if (this.getClass() != other.getClass()) {
            throw new IllegalArgumentException("Cannot compare different types of celestial bodies.");
        }
        return name.compareToIgnoreCase(other.name);
    }
         
}
