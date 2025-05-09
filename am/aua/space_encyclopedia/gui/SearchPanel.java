package am.aua.space_encyclopedia.gui;

import am.aua.space_encyclopedia.core.*;
import am.aua.space_encyclopedia.data.DataManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class SearchPanel extends JPanel {

    // Color constants
    private static final Color BACKGROUND_COLOR = new Color(240, 244, 250);
    private static final Color BUTTON_COLOR = new Color(120, 170, 230);
    private static final Color HOVER_COLOR = new Color(150, 200, 255);
    private static final Color LIST_BACKGROUND_COLOR = new Color(245, 245, 245);
    private static final Color HEADER_COLOR = new Color(91, 139, 216);

    // Font constants
    private static final Font FONT = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 20);

    private DataManager dataManager;
    private ActionListener showMenuListener;
    private ActionListener viewDetailsListener;
    private JTextField searchField;
    private JList<String> resultsList;
    private DefaultListModel<String> listModel;

    public SearchPanel(DataManager dataManager,
                       ActionListener showMenuListener,
                       ActionListener viewDetailsListener) {
        this.dataManager = dataManager;
        this.showMenuListener = showMenuListener;
        this.viewDetailsListener = viewDetailsListener;
        setLayout(new BorderLayout());
        setBackground(BACKGROUND_COLOR);

        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createContentPanel(), BorderLayout.CENTER);
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(HEADER_COLOR);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JButton backButton = new JButton("← Back to Menu");
        styleButton(backButton);
        backButton.addActionListener(showMenuListener);

        // Add hover effect
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                backButton.setBackground(HOVER_COLOR);  // Change to hover color
            }

            @Override
            public void mouseExited(MouseEvent e) {
                backButton.setBackground(BUTTON_COLOR);  // Revert to normal color
            }
        });

        JLabel title = new JLabel("SEARCH CELESTIAL OBJECTS", JLabel.CENTER);
        title.setFont(TITLE_FONT);
        title.setForeground(Color.WHITE);
        headerPanel.add(backButton, BorderLayout.WEST);
        headerPanel.add(title, BorderLayout.CENTER);

        return headerPanel;
    }

    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Search controls
        JPanel searchControls = new JPanel(new BorderLayout(10, 10));
        searchControls.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        searchField = new JTextField();
        styleTextField(searchField);

        JButton searchBtn = new JButton("Search");
        styleButton(searchBtn);
        searchBtn.addActionListener(e -> performSearch());

        // Add hover effect to the search button
        searchBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                searchBtn.setBackground(HOVER_COLOR);  // Change to hover color
            }

            @Override
            public void mouseExited(MouseEvent e) {
                searchBtn.setBackground(BUTTON_COLOR);  // Revert to normal color
            }
        });

        searchControls.add(new JLabel("Enter object name:"), BorderLayout.WEST);
        searchControls.add(searchField, BorderLayout.CENTER);
        searchControls.add(searchBtn, BorderLayout.SOUTH);

        // Results panel
        listModel = new DefaultListModel<>();
        resultsList = new JList<>(listModel);
        styleResultsList();

        JScrollPane scrollPane = new JScrollPane(resultsList);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        JButton viewDetailsBtn = new JButton("View Selected");
        styleButton(viewDetailsBtn);
        viewDetailsBtn.addActionListener(viewDetailsListener);

        // Add hover effect to the view details button
        viewDetailsBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                viewDetailsBtn.setBackground(HOVER_COLOR);  // Change to hover color
            }

            @Override
            public void mouseExited(MouseEvent e) {
                viewDetailsBtn.setBackground(BUTTON_COLOR);  // Revert to normal color
            }
        });

        contentPanel.add(searchControls, BorderLayout.NORTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.add(viewDetailsBtn, BorderLayout.SOUTH);

        return contentPanel;
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(BUTTON_COLOR);  // Use the BUTTON_COLOR constant
        button.setBorder(BorderFactory.createEmptyBorder(6, 16, 6, 16));
        button.setFocusPainted(false);
    }

    private void styleTextField(JTextField field) {
        field.setFont(FONT);
        field.setBackground(LIST_BACKGROUND_COLOR);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 210, 230)),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
    }

    private void styleResultsList() {
        resultsList.setFont(FONT);
        resultsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        resultsList.setBackground(LIST_BACKGROUND_COLOR);
        resultsList.setForeground(Color.DARK_GRAY);
    }

    public void performSearch() {
        String term = searchField.getText().trim();
        listModel.clear();

        if (term.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a search term",
                    "Search Error",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        searchCategory(term, dataManager.getStars(), "Star");
        searchCategory(term, dataManager.getPlanets(), "Planet");
        searchCategory(term, dataManager.getGalaxies(), "Galaxy");

        if (listModel.isEmpty()) {
            listModel.addElement("No results found for " + term);
        }
    }

    private <T extends CelestialBody> void searchCategory(String term, ArrayList<T> objects, String typeName) {
        String lowerTerm = term.toLowerCase();
        ArrayList<T> matchingObjects = new ArrayList<>();

        // Collect objects that start with the search term
        for (T obj : objects) {
            if (obj.getName().toLowerCase().startsWith(lowerTerm)) {
                matchingObjects.add(obj);
            }
        }

        // Sort the matching objects using their compareTo method
        Collections.sort(matchingObjects);

        // Add sorted names to the listModel
        for (T obj : matchingObjects) {
            listModel.addElement("[" + typeName + "] " + obj.getName());
        }
    }

    public String getSelectedObjectName() {
        if (resultsList == null || listModel == null || listModel.isEmpty()) {
            return null;
        }
        int index = resultsList.getSelectedIndex();
        if (index < 0) {
            return null;
        }

        String selected = listModel.getElementAt(index);

        if (selected.contains("]")) {
            return selected.substring(selected.indexOf("]") + 1).trim();
        }
        return null;
    }
}
