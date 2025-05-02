package am.aua.space_encyclopedia.core;

import java.io.*;
import java.util.ArrayList;

public class DataManager {
    private ArrayList<Star> stars;
    private ArrayList<Planet> planets;
    private ArrayList<Galaxy> galaxies;
    private static final String DATA_FILE = "space_objects.txt";

    public DataManager() {
        stars = new ArrayList<>();
        planets = new ArrayList<>();
        galaxies = new ArrayList<>();
        loadData();
    }
    // Getter methods
    public ArrayList<Star> getStars() { return stars; }
    public ArrayList<Planet> getPlanets() { return planets; }
    public ArrayList<Galaxy> getGalaxies() { return galaxies; }

    // Add methods
    public void addStar(Star newStar) {
//        if (!stars.contains(newStar))
            insertInOrder(stars, newStar);
    }

    public void addPlanet(Planet newPlanet) {
        insertInOrder(planets, newPlanet);
    }

    public void addGalaxy(Galaxy newGalaxy) {
        insertInOrder(galaxies, newGalaxy);
    }
    private <T extends CelestialBody> void insertInOrder(ArrayList<T> list, T newObj) {
        int pos = 0;
        // Find the correct position
        while (pos < list.size() && newObj.compareTo(list.get(pos)) > 0) {
            pos++;
        }
        list.add(pos, newObj);
    }


    // Search methods
    public Planet findPlanetByName(String name) {
        for (Planet planet : planets) {
            if (planet.getName().equalsIgnoreCase(name)) {
                return planet;
            }
        }
        return null;
    }

    public Star findStarByName(String name) {
        for (Star star : stars) {
            if (star.getName().equalsIgnoreCase(name)) {
                return star;
            }
        }
        return null;
    }

    public Galaxy findGalaxyByName(String name) {
        for (Galaxy galaxy : galaxies) {
            if (galaxy.getName().equalsIgnoreCase(name)) {
                return galaxy;
            }
        }
        return null;
    }
    private void loadData() {
        // Create sample data if file doesn't exist
        if (!new File(DATA_FILE).exists()) {
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
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
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
        } catch (Exception e) {
            System.out.println("Error parsing line: " + line);
            return null;
        }
    }
    private void createSampleData() {
        // Create sample star (correct parameter order)
        addStar(new Star(
                "Sun",
                0.0000158,
                1.989e30,
                5778,
                "Yellow Dwarf",
                "Yellow",
                1.0,
                1.0
        ));

        // Create sample planet
        addPlanet(new Planet("Earth", 0.0000158, 5.972e24, 288,
                "Terrestrial", "Nitrogen-Oxygen", 1,
                "Rocky with water", 6371
        ));
        addGalaxy(new Galaxy("Milky Way",0.0,1.5e42,0,"Spiral",
                250_000_000_000L,105700.0,13.51
        ));
    }

    public void saveData() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE))) {
            // Save stars
            for (Star star : stars) {
                writer.println(String.format("Star,%s,%.6f,%.3e,%.2f,%s,%s,%.2f,%.2f",
                        star.getName(),
                        star.getDistanceFromEarth(),
                        star.getMass(),
                        star.getTemperature(),
                        star.getType(),
                        star.getColor(),
                        star.getLuminosity(),
                        star.getSize()));
            }

            // Save planets
            for (Planet planet : planets) {
                writer.println(String.format("Planet,%s,%.6f,%.3e,%.2f,%s,%s,%d,%s,%.2f",
                        planet.getName(),
                        planet.getDistanceFromEarth(),
                        planet.getMass(),
                        planet.getTemperature(),
                        planet.getType(),
                        planet.getAtmosphericComposition(),
                        planet.getNumberOfMoons(),
                        planet.getSurfaceConditions(),
                        planet.getRadius()));
            }

            // Save galaxies
            for (Galaxy galaxy : galaxies) {
                writer.println(String.format("Galaxy,%s,%.6f,%.3e,%.2f,%s,%d,%.2f,%.2f",
                        galaxy.getName(),
                        galaxy.getDistanceFromEarth(),
                        galaxy.getMass(),
                        galaxy.getTemperature(),
                        galaxy.getType(),
                        galaxy.getNumberOfStars(),
                        galaxy.getDiameter(),
                        galaxy.getAge()));
            }
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

}