import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Star sun = new Star("Sun", 0.0000158, "Yellow dwarf", 5778, 1, "white", 1, 696.340);
        Star proxima = new Star("Proxima Centauri", 4.2, "Red Dwarf", 3042, 0.12, "red", 0.17, 0.14);

        Planet earth = new Planet("Earth", 0.0, 5.972e24, 288, "Terrestrial",
                "Nitrogen (78%) and Oxygen (21%)", 1,
                "Solid, stable and active surface with mountains, valleys, canyons, plains and more.",
                6.378e6);

        Planet mars = new Planet("Mars", 2.3815e-11, 6.39e23, 210, "Terrestrial",
                "Carbon Dioxide (95%), Nitrogen (2.8%)", 2,
                "Rocky surface with canyons and dust storms", 3.3895e6);

        ArrayList<Star> milkyWayStars = new ArrayList<>();
        milkyWayStars.add(sun);
        milkyWayStars.add(proxima);

        ArrayList<Planet> milkyWayPlanets = new ArrayList<>();
        milkyWayPlanets.add(earth);
        milkyWayPlanets.add(mars);

        ArrayList<Galaxy> galaxies = new ArrayList<>();
        galaxies.add(new Galaxy("Milky Way", 0.0, 1.5e12, 5500, "Spiral", 100_000_000_000L, 105_700, 13,
                milkyWayStars, milkyWayPlanets));

        System.out.println("Enter the name of the galaxy:");
        String galaxyName = scanner.nextLine();

        Galaxy selectedGalaxy = null;
        for (Galaxy galaxy : galaxies) {
            if (galaxy.getName().equalsIgnoreCase(galaxyName)) {
                selectedGalaxy = galaxy;
                break;
            }
        }

        if (selectedGalaxy == null) {
            System.out.println("Galaxy not found.");
            return;
        }

        selectedGalaxy.displayInfo();

        System.out.println("\nWould you like to learn about a specific (1) star or (2) planet? Enter 1 or 2:");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            System.out.println("Enter the star's name:");
            String starName = scanner.nextLine();
            Star star = selectedGalaxy.findStarByName(starName);
            if (star != null) star.displayInfo();
            else System.out.println("Star not found.");
         } else if (choice == 2) {
            System.out.println("Enter the planet's name:");
            String planetName = scanner.nextLine();
            Planet planet = selectedGalaxy.findPlanetByName(planetName);
            if (planet != null) planet.displayInfo();
            else System.out.println("Planet not found.");
        } else {
            System.out.println("Invalid choice.");
        }

        scanner.close(); 
    } 
}
