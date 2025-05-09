package am.aua.space_encyclopedia;

import javax.swing.*;
import java.awt.*;
import am.aua.space_encyclopedia.core.*;
import am.aua.space_encyclopedia.gui.*;
import am.aua.space_encyclopedia.data.DataManager;

public class GUISpaceEncyclopedia extends JFrame {
    private DataManager dataManager;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private SearchPanel searchPanel;
    private DetailsPanel detailsPanel;
    private BrowsePanel browsePanel;

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


    private void showDetailsPanel(CelestialBody body) {
        detailsPanel.displayDetails(body);
        cardLayout.show(mainPanel, "DETAILS");
    }

    private JPanel createMenuPanel() {
        return new MenuPanel(
                e ->  cardLayout.show(mainPanel, "BROWSE"),
                e -> cardLayout.show(mainPanel, "SEARCH"),
                e -> System.exit(0)
        );
    }

    private void showSelectedObject() {
        String selected = searchPanel.getSelectedObjectName();
        if (selected != null) {
            CelestialBody body = dataManager.findCelestialBodyByName(selected);
            if (body != null)
                showDetailsPanel(body);
            else
                JOptionPane.showMessageDialog(this, "Celestial body not found.", "Error", JOptionPane.ERROR_MESSAGE);

        } else
            JOptionPane.showMessageDialog(this, "Please select an item first.", "Warning", JOptionPane.WARNING_MESSAGE);
    }

    public static void main(String[] args) {
        GUISpaceEncyclopedia gui = new GUISpaceEncyclopedia();
        gui.setVisible(true);
    }
}
