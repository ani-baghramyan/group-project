package am.aua.space_encyclopedia;

import javax.swing.*;
import java.awt.*;
import am.aua.space_encyclopedia.core.*;
import am.aua.space_encyclopedia.gui.*;
import am.aua.space_encyclopedia.data.DataManager;

/**
 * The main GUI application window for the Space Encyclopedia.
 * This class initializes and manages the layout and panels of the application.
 * <p>
 * An object of type <code>GUISpaceEncyclopedia</code> contains:
 * <ul>
 *   <li>A field of type <code>DataManager</code> to manage celestial body data.</li>
 *   <li>A field of type <code>CardLayout</code> to handle layout switching between panels.</li>
 *   <li>A field of type <code>JPanel</code> to contain the main panels and manage navigation.</li>
 *   <li>Fields of type <code>BrowsePanel</code>, <code>SearchPanel</code>, and <code>DetailsPanel</code> to represent the different panels in the application.</li>
 * </ul>
 */
public class GUISpaceEncyclopedia extends JFrame {
    private DataManager dataManager;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private SearchPanel searchPanel;
    private DetailsPanel detailsPanel;
    private BrowsePanel browsePanel;

    /**
     * Constructs the main window for the Space Encyclopedia GUI.
     * Initializes the panels and sets up layout switching with CardLayout.
     */
    public GUISpaceEncyclopedia() {
        super("Space Encyclopedia");
        this.dataManager = new DataManager();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));

        browsePanel = new BrowsePanel(dataManager, e -> cardLayout.show(mainPanel, "MENU"));
        searchPanel = new SearchPanel(
                dataManager,
                e -> cardLayout.show(mainPanel, "MENU"),
                e -> showSelectedObject()
        );
        detailsPanel = new DetailsPanel(e -> cardLayout.show(mainPanel, "SEARCH"));

        mainPanel.add(createMenuPanel(), "MENU");
        mainPanel.add(browsePanel, "BROWSE");
        mainPanel.add(searchPanel, "SEARCH");
        mainPanel.add(detailsPanel, "DETAILS");

        add(mainPanel, BorderLayout.CENTER);
        cardLayout.show(mainPanel, "MENU");
    }

    /**
     * Displays the details panel for the given celestial body.
     * @param body The celestial body whose details are to be shown.
     */
    private void showDetailsPanel(CelestialBody body) {
        detailsPanel.displayDetails(body);
        cardLayout.show(mainPanel, "DETAILS");
    }

    /**
     * Creates the main menu panel with navigation buttons.
     * @return A JPanel representing the main menu.
     */
    private JPanel createMenuPanel() {
        return new MenuPanel(
                e ->  cardLayout.show(mainPanel, "BROWSE"),
                e -> cardLayout.show(mainPanel, "SEARCH"),
                e -> System.exit(0)
        );
    }

    /**
     * Retrieves the selected object name from the search panel, locates the corresponding celestial body, and displays its details.
     * Shows an error or warning dialog if selection or lookup fails.
     */
    private void showSelectedObject() {
        String selected = searchPanel.getSelectedObjectName();
        if (selected != null) {
            CelestialBody body = dataManager.findCelestialBodyByName(selected);
            if (body != null)
                showDetailsPanel(body);
            else
                JOptionPane.showMessageDialog(this, "Celestial body not found.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Please select an item first.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Launches the Space Encyclopedia GUI.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        GUISpaceEncyclopedia gui = new GUISpaceEncyclopedia();
        gui.setVisible(true);
    }
}
