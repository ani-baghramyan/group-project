package am.aua.space_encyclopedia.data;

import java.io.*;
import java.util.ArrayList;
import am.aua.space_encyclopedia.core.*;

/**
 * The <code>DataManager</code> class manages collections of celestial objects 
 * (<code>Star</code>s, <code>Planet</code>s, <code>Galaxy</code>s), 
 * handles their persistence to and from a text file, and provides search and add functionality.
 * <p>
 * An object of type <code>DataManager</code> contains:
 * <ul>
 *   <li>Fields of type <code>ArrayList</code> for storing collections in the system:
 *     <ul>
 *       <li><code>stars</code> - List of <code>Star</code> objects</li>
 *       <li><code>planets</code> - List of <code>Planet</code> objects</li>
 *       <li><code>galaxies</code> - List of <code>Galaxy</code> objects</li>
 *     </ul>
 *   </li>
 * </ul>
 */

public class DataManager {
    /**
     * The path to the file where celestial objects are stored.
     */
    private static final String DATA_FILE = "am/aua/space_encyclopedia/data/space_objects.txt";
    
    private ArrayList<Star> stars;
    private ArrayList<Planet> planets;
    private ArrayList<Galaxy> galaxies;
/** Constructs a DataManager and loads data from file or creates sample data if the file does not exist.*/
    public DataManager() {
        stars = new ArrayList<>();
        planets = new ArrayList<>();
        galaxies = new ArrayList<>();
        loadData();
    }
    /**
      *Returns the list of all stars.
     * @return list of stars
     */
    public ArrayList<Star> getStars() {
        return stars;
    }
    /**
    *Returns the list of all planets.
     * @return list of planets
     */
    public ArrayList<Planet> getPlanets() { 
        return planets; 
    }
    /**
    *Returns the list of all galaxies.
     * @return list of galaxies
     */
    public ArrayList<Galaxy> getGalaxies() {
        return galaxies;
    }

    /**
    *Adds a star to the collection in sorted order based on natural ordering.
     * @param newStar the star to add
     */
    public void addStar(Star newStar) {
        if (newStar == null) 
            throw new IllegalArgumentException("Planet cannot be null");
        if (!stars.contains(newStar)) 
            insertInOrder(stars, newStar);
    }
/**
*Adds a planet to the collection in sorted order based on natural ordering.
     * @param newPlanet the planet to add
     */
    
    public void addPlanet(Planet newPlanet) {
         if (newPlanet == null) 
            throw new IllegalArgumentException("Planet cannot be null");
        if (!planets.contains(newPlanet)) 
            insertInOrder(planets, newPlanet);
    }
/**
*Adds a galaxy to the collection in sorted order based on natural ordering.
     * @param newGalaxy the galaxy to add
     */
    public void addGalaxy(Galaxy newGalaxy) {
        if (newGalaxy == null) 
            throw new IllegalArgumentException("Planet cannot be null");
        if (!galaxies.contains(newGalaxy)) 
            insertInOrder(galaxies, newGalaxy);
    }
    /**
    *Inserts an object into the given list maintaining ascending order using compareTo().
     * @param list - the list to insert into
     * @param newObj the object to insert
     * @param <T> - the type of celestial object
     */
    private <T extends CelestialBody> void insertInOrder(ArrayList<T> list, T newObj) {
        int pos = 0;
        // to find the correct position
        while (pos < list.size() && newObj.compareTo(list.get(pos)) > 0) {
            pos++;
        }
        list.add(pos, newObj);
    }
 /**
 * Searches for a celestial body by name across all collections (stars, planets, and galaxies).
 * @param name The name of the celestial body to search for (must not be <code>null</code> or empty)
 * @return The found <code>CelestialBody</code> object, or <code>null</code> if not found
 */
    public CelestialBody findCelestialBodyByName(String name) {
        if (name == null || name.trim().isEmpty())
            return null;

        // Search in stars
        for (Star star : stars)
            if (star.getName().equalsIgnoreCase(name.trim()))
                return star;

        // Search in planets
        for (Planet planet : planets)
            if (planet.getName().equalsIgnoreCase(name.trim()))
                return planet;

        // Search in galaxies
        for (Galaxy galaxy : galaxies)
            if (galaxy.getName().equalsIgnoreCase(name.trim()))
                return galaxy;

        return null;
    }
    
    /**
    *Loads celestial objects from a data file or creates and saves sample data if the file does not exist.*/
    private void loadData() {
        if (!new File(DATA_FILE).exists()) {
            System.out.println("Creating and loading sample celestial objects...");
            createSampleData();
            saveData();
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                CelestialBody body = parseLine(line);
                if (body != null) {
                    if (body instanceof Star) {
                        addStar((Star) body);
                    } else if (body instanceof Planet) {
                        addPlanet((Planet) body);
                    } else if (body instanceof Galaxy) {
                        addGalaxy((Galaxy) body);
                    }
                }
            }
        } catch (IOException e) {
        System.out.println("Error loading data from " + DATA_FILE + ": " + e.getMessage());
    }
    }
    /**
    *Parses a line of text from the data file and converts it into a celestial object.
     * @param line the line to parse
     * @return the parsed CelestialBody object, or null if invalid
     */
    private CelestialBody parseLine(String line) {
        String[] parts = line.split("\\s*,\\s*");
        try {
            switch (parts[0]) {
                case "Star":
                    return new Star(
                            parts[1],                     // name
                            Double.parseDouble(parts[2]), // distanceFromEarth
                            Double.parseDouble(parts[3]),//mass
                            Double.parseDouble(parts[4]), // temperature
                            parts[5], //type
                            parts[6],                     // color
                            Double.parseDouble(parts[7]), // luminosity
                            Double.parseDouble(parts[8])  // size
                    );
                case "Planet":
                    return new Planet(
                            parts[1],                    // name
                            Double.parseDouble(parts[2]), // distance
                            Double.parseDouble(parts[3]), // mass
                            Double.parseDouble(parts[4]), // temp
                            parts[5],                    // type
                            parts[6],                    // atmosphere
                            Integer.parseInt(parts[7]),   // moons
                            parts[8],                    // surface
                            Double.parseDouble(parts[9]) // radius
                    );
                case "Galaxy":
                    return new Galaxy(
                            parts[1],                    // name
                            Double.parseDouble(parts[2]), // distance
                            Double.parseDouble(parts[3]), // mass
                            Double.parseDouble(parts[4]), // temp
                            parts[5],                    // type
                            Long.parseLong(parts[6]),          // number of stars
                            Double.parseDouble(parts[7]),      // diameter
                            Double.parseDouble(parts[8])   // age

                    );
                default:
                    return null;
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Error parsing line (invalid number or missing data): " + line);
            return null;
        }
    }
    /**
    *Creates initial sample data (Sun, Earth, Milky Way) and adds them to the respective lists.
    */
    private void createSampleData() {
        addStar(new Star("Sun",0.0000158,1.989e30,5778,"Yellow Dwarf","Yellow",1.0,1.0 ));
        addPlanet(new Planet("Earth", 0.0000158, 5.972e24, 288, "Terrestrial", "Nitrogen-Oxygen", 1, "Rocky with water", 6371));
        addGalaxy(new Galaxy("Milky Way",0.0,1.5e42,0,"Spiral", 250_000_000_000L,105700.0,13.51));
    }
/**
*Saves all celestial objects to the data file in comma-separated format.
*/
    public void saveData() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE))) {
            for (Star star : stars) {
                writer.println(String.format("Star,%s,%.6f,%.3e,%.2f,%s,%s,%.2f,%.2f",star.getName(),star.getDistanceFromEarth(),star.getMass(),star.getTemperature(),star.getType(),star.getColor(), star.getLuminosity(),star.getSize()));
            }
            for (Planet planet : planets) {
                writer.println(String.format("Planet,%s,%.6f,%.3e,%.2f,%s,%s,%d,%s,%.2f", planet.getName(), planet.getDistanceFromEarth(), planet.getMass(), planet.getTemperature(), planet.getType(), planet.getAtmosphericComposition(), planet.getNumberOfMoons(), planet.getSurfaceConditions(), planet.getRadius()));
            }
            for (Galaxy galaxy : galaxies) {
                writer.println(String.format("Galaxy,%s,%.6f,%.3e,%.2f,%s,%d,%.2f,%.2f",galaxy.getName(),galaxy.getDistanceFromEarth(), galaxy.getMass(), galaxy.getTemperature(),galaxy.getType(), galaxy.getNumberOfStars(),galaxy.getDiameter(), galaxy.getAge()));
            }
        } catch (IOException e) {
            System.out.println("Error saving data to " + DATA_FILE + ": " + e.getMessage());
        }
    }

}
