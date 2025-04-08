public class Galaxy extends CelestialBody {
    private String type;
    private long numberOfStars;
    private double diameter;
    private int age;

    private Star[] stars;
    private Planet[] planets;

    public Galaxy(String name, double distanceFromEarth, String type, long numberOfStars, double diameter, int age,
                  Star[] stars, Planet[] planets) {
        super(name, distanceFromEarth);
        this.type = type;
        this.numberOfStars = numberOfStars;
        this.diameter = diameter;
        this.age = age;
        this.stars = stars;
        this.planets = planets;
    }

    public void displayInfo() {
        System.out.println("\n--- Galaxy Information ---");
        System.out.println("Galaxy Name: " + name);
        System.out.println("Type: " + type);
        System.out.println("Age: " + age + " billion years");
        System.out.println("Diameter: " + diameter + " light years");
        System.out.println("Distance from Earth: " + distanceFromEarth + " million light years");
        System.out.println("Estimated Number of Stars: " + numberOfStars);

        System.out.println("\nStars in this galaxy:");
        if (stars.length == 0) {
            System.out.println(" - No stars listed.");
        } else {
            for (int i = 0; i < stars.length; i++) {
                System.out.println(" - " + stars[i].getName());
            }
        }

        System.out.println("\nPlanets in this galaxy:");
        if (planets.length == 0) {
            System.out.println(" - No planets listed.");
        } else {
            for (int i = 0; i < planets.length; i++) {
                System.out.println(" - " + planets[i].getName());
            }
        }
    }

    public Star findStarByName(String starName) {
        for (int i = 0; i < stars.length; i++) {
            if (stars[i].getName().equalsIgnoreCase(starName)) {
                return stars[i];
            }
        }
        return null;
    }

    public Planet findPlanetByName(String planetName) {
        for (int i = 0; i < planets.length; i++) {
            if (planets[i].getName().equalsIgnoreCase(planetName)) {
                return planets[i];
            }
        }
        return null;
    }
}
