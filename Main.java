import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Star sun = new Star("Sun", 0.0000158, "Yellow dwarf", 5778, 1, "white", 1, 696.340);
        Star proxima = new Star("Proxima Centauri", 4.2, "Red Dwarf", 3042, 12, "red", 0.17, 14);

        Planet earth = new Planet("Earth", 0.0, true, 6371);
        Planet mars = new Planet("Mars", 0.0, false, 3389);

        System.out.println("Enter the name of the galaxy:");
        String galaxyName = scanner.nextLine();

        Galaxy selectedGalaxy = null;
        for (int i = 0; i < galaxies.length; i++) {
            if (galaxies[i].getName().equalsIgnoreCase(galaxyName)) {
                selectedGalaxy = galaxies[i];
                break;
            }
        }
    }
}
