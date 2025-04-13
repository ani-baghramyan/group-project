import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Star sun = new Star("Sun", 0.0000158, "Yellow dwarf", 5778, 1, "white", 1, 696.340);
        Star proxima = new Star("Proxima Centauri", 4.2, "Red Dwarf", 3042, 12, "red", 0.17, 14);

        Planet earth = new Planet("Earth", 0.0, 5.972e24, 288, "Terrestrial","Nitrogen (78%) and Oxygen (21%)",
                                  1, "Solid, stable and active surface with mountains, valleys, canyons, plains and more.", 6.378e6);
        
        Planet mars = new Planet("Mars", 2.3815e-11, 6.39e23, 210, "Terrestrial", "Carbon Dioxide (95%), Nitrogen (2.8%)", 
                                 2,  "Rocky surface with canyons and dust storms", 3.3895e6);

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
