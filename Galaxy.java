import java.util.ArrayList;
public class Galaxy extends SpaceObject {
    private long numberOfStars;
    private double diameter;
    private int age;
    private ArrayList<Star> stars;
    private ArrayList<Planet> planets;

    public Galaxy(String name, double distanceFromEarth, double mass, double temperature, String type,
                  long numberOfStars, double diameter, int age,
                  ArrayList<Star> stars, ArrayList<Planet> planets) {
        super(name, distanceFromEarth, mass, temperature, type);
        this.numberOfStars = numberOfStars;
        this.diameter = diameter;
        this.age = age;
        this.stars = stars;
        this.planets = planets;
    }
    

    public long getNumberOfStars() {
        return numberOfStars;
    }

    public double getDiameter() {
        return diameter;
    }

    public int getAge() {
        return age;
    }

    public ArrayList<Star> getStars() {
        return stars;
    }

    public ArrayList<Planet> getPlanets() {
        return planets;
    }

    public Star findStarByName(String name) {
        for (Star star : stars) {
            if (star.getName().equalsIgnoreCase(name)) {
                return star;
            }
        }
        return null;
    }

    public Planet findPlanetByName(String name) {
        for (Planet planet : planets) {
            if (planet.getName().equalsIgnoreCase(name)) {
                return planet;
            }
        }
        return null;
    }

    public void displayInfo() {
        System.out.println("\nGalaxy: " + getName());
        System.out.println("Type: " + getType());
        System.out.println("Distance from Earth: " + getDistanceFromEarth() + " light-years");
        System.out.println("Mass: " + getMass() + " kg");
        System.out.println("Temperature: " + getTemperature() + " K");
        System.out.println("Diameter: " + diameter + " light-years");
        System.out.println("Number of stars: " + numberOfStars);
        System.out.println("Age: " + age + " billion years");

        System.out.println("\nStars:");
        for (Star star : stars) {
            System.out.println("- " + star.getName());
        }

        System.out.println("\nPlanets:");
        for (Planet planet : planets) {
            System.out.println("- " + planet.getName());
        }
    }
}
