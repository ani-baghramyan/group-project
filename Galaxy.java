public class Galaxy extends CelestialBody {
    private long numberOfStars;
    private double diameter;
    private int age;

    private Star[] stars;
    private Planet[] planets;

    public Galaxy(String name, double distanceFromEarth, double mass, double temperature, String type,
                  long numberOfStars, double diameter, int age,
                  Star[] stars, Planet[] planets) {
        super(name, distanceFromEarth, mass, temperature, type);
        this.numberOfStars = numberOfStars;
        this.diameter = diameter;
        this.age = age;
        this.stars = stars;
        this.planets = planets;
    }

    public void displayInfo() {
        System.out.println("\n--- Galaxy Information ---");
        System.out.println("Galaxy Name: " + getName());
        System.out.println("Type: " + getType());
        System.out.println("Age: " + age + " billion years");
        System.out.println("Diameter: " + diameter + " light years");
        System.out.println("Distance from Earth: " + getDistanceFromEarth() + " million light years");
        System.out.println("Estimated Number of Stars: " + numberOfStars);

        System.out.println("\nStars in this galaxy:");
        if (stars.length == 0) {
            System.out.println(" - No stars listed.");
        } else {
            for (Star star : stars) {
                System.out.println(" - " + star.getName());
            }
        }

        System.out.println("\nPlanets in this galaxy:");
        if (planets.length == 0) {
            System.out.println(" - No planets listed.");
        } else {
            for (Planet planet : planets) {
                System.out.println(" - " + planet.getName());
            }
        }
    }

    public Star findStarByName(String starName) {
        for (Star star : stars) {
            if (star.getName().equalsIgnoreCase(starName)) {
                return star;
            }
        }
        return null;
    }

    public Planet findPlanetByName(String planetName) {
        for (Planet planet : planets) {
            if (planet.getName().equalsIgnoreCase(planetName)) {
                return planet;
            }
        }
        return null;
    }
}
