package am.aua.space_encyclopedia;
import java.io.*;
import am.aua.space_encyclopedia.core.*;
import am.aua.space_encyclopedia.data.*;

public class SpaceEncyclopedia {
   private  DataManager dataManager;

    public SpaceEncyclopedia() {
        this.dataManager = new DataManager();
    }
    public String showMenuPage() {
        StringBuilder menu = new StringBuilder();

        menu.append("\n----- WELCOME TO THE SPACE ENCYCLOPEDIA ---\n");
        menu.append("Explore stars, planets, and galaxies right from your console!\n");
        menu.append("------------------------------------------------------------\n");
        menu.append("Available Commands:\n");
        menu.append("l          - List all known celestial objects\n");
        menu.append("s <name>   - Search celestial bodies by name\n");
        menu.append("v <name>   - View detailed info about a specific celestial body\n");
        menu.append("m          - Show this command menu again\n");
        menu.append("q          - Exit the encyclopedia\n");
        menu.append("------------------------------------------------------------\n");
        menu.append("Tip: Use full names like 'Sun' or 'Andromeda' when searching.\n");
        return menu.toString();
    }

    public void start() throws IOException {
        System.out.println(showMenuPage());
        BufferedReader input = new BufferedReader (new InputStreamReader(System.in));
        String command = " ";
        System.out.println("Let's explore the universe!\n");
        while (!(command.equals("q")) ){
            try {
               System.out.println("Let's explore the universe!\n");
               System.out.println("You can explore by inputting l, s <name>, v <name>; input m to go back to main menu;");
               System.out.println( "or stop the game by inputting q: " );
               command = input.readLine().trim();
               if (command.equals("l")) {
                   System.out.println("\n=== ALL OBJECTS ===");
   
                   System.out.println("STARS:");
                   for (Star star : dataManager.getStars()) {
                       System.out.println("- " + star.getName());
                   }
   
                   System.out.println("\nPLANETS:");
                   for (Planet planet : dataManager.getPlanets()) {
                       System.out.println("- " + planet.getName());
                   }
   
                   System.out.println("\nGALAXIES:");
                   for (Galaxy galaxy : dataManager.getGalaxies()) {
                       System.out.println("- " + galaxy.getName());
                   }
               }
               else if (command.startsWith("v")) {
                   String name = command.substring(1).trim();
                   if (name.isEmpty()) {
                       System.out.println("Please provide a name. Example: v Sun");
                       continue;
                   }
                   CelestialBody object = dataManager.findStarByName(name);
                   if (object == null)
                       object = dataManager.findPlanetByName(name);
                   if (object == null)
                       object = dataManager.findGalaxyByName(name);
   
                   if (object == null) {
                       System.out.println("Object not found: " + name);
                       return;
                   }
   
                   System.out.println("\n=== " + object.getName().toUpperCase() + " ===");
                   System.out.println(object); // Basic info
   
                   System.out.println("\nInput 'f' for more facts about " + object.getName());
                   System.out.println("Or input l, s <name>, v <name>; m for main menu; q to quit.");
                   String response = input.readLine().trim();
                   if (response.equals("f")) {
                       System.out.println("\nDETAILED FACTS:");
                       System.out.println(object.showFacts());
                   }
               }
               else if (command.startsWith("s")) {
                   String term = command.substring(1).trim();
                   if (term.isEmpty()) {
                       System.out.println("Please provide a search term. Example: s Mars");
                       continue;
                   }
                   System.out.println("\nSEARCH RESULTS:");
   
                   boolean found = false;
   
                   for (Star star : dataManager.getStars()) {
                       if (star.getName().toLowerCase().contains(term.toLowerCase())) {
                           System.out.println("[Star] " + star.getName());
                           found = true;
                       }
                   }
                   for (Planet planet : dataManager.getPlanets()) {
                      if (planet.getName().toLowerCase().contains(term.toLowerCase())) {
                         System.out.println("[Planet] " + planet.getName());
                         found = true;
                      }
                   }
   
                   for (Galaxy galaxy : dataManager.getGalaxies()) {
                       if (galaxy.getName().toLowerCase().contains(term.toLowerCase())) {
                           System.out.println("[Galaxy] " + galaxy.getName());
                           found = true;
                       }
                   }
   
                   if (!found) {
                       System.out.println("Sorry! " + term +" celestial body was not found.");
                   }
               }
               else if (command.equals("m")) {
                   System.out.println(showMenuPage());
               }
               else
                   System.out.println("Invalid input: Please try again.");
           }
        } catch (IOException e) {
            System.out.println("An error occurred while reading input. Please try again.");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Missing name after command. Use format like: s Mars or v Earth.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
   
         System.out.println("Quiting the program. Goodbye!");
         System.out.println(showMenuPage()); // Go back to main page
    } 
    
   public static void main(String[] args) {
    try {
        SpaceEncyclopedia se = new SpaceEncyclopedia();
        se.start();
    } catch (IOException e) {
        System.err.println("An error occurred: " + e.getMessage());
    }
   } 
}
