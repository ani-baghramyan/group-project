public class Galaxy extends CelestialBody {
    private String type;
    private long numberOfStars;
    private double diameter;

    public Galaxy(String name, double distanceFromEarth, String type, long numberOfStars, double diameter) {
        super(name, distanceFromEarth);
        this.type = type;
        this.numberOfStars = numberOfStars;
        this.diameter = diameter;
    }

    public void displayInfo() {
        System.out.println("Galaxy Name: " + name);
        System.out.println("Type: " + type);
        System.out.println("Stars: " + numberOfStars);
        System.out.println("Diameter: " + diameter + " light years");
        System.out.println("Distance from Earth: " + distanceFromEarth + " million light years");
    }
}
