package am.aua.space_encyclopedia.gui;

import am.aua.space_encyclopedia.core.*;
import am.aua.space_encyclopedia.data.DataManager;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class BrowsePanel extends JPanel {
    // Static final constants
    private static final Color BUTTON_COLOR = new Color(120, 170, 230);
    private static final Color HOVER_COLOR = new Color(150, 200, 255);
    private static final Color BACKGROUND_COLOR = new Color(240, 244, 250);
    private static final Color LIST_BACKGROUND_COLOR = new Color(240, 245, 255);
    private static final Color BORDER_COLOR = new Color(100, 120, 160);
    private static final Color TITLE_COLOR = new Color(30, 60, 120);
    private static final Color LIST_FOREGROUND_COLOR = Color.DARK_GRAY;
    private static final Font BUTTON_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 20);
    private static final Font CATEGORY_TITLE_FONT = new Font("Segoe UI", Font.BOLD, 16);
    private static final Font LIST_FONT = new Font("Segoe UI", Font.PLAIN, 14);

    private DataManager dataManager;
    private ActionListener showMenuListener;

    public BrowsePanel(DataManager dataManager, ActionListener showMenuListener) {
        this.dataManager = dataManager;
        this.showMenuListener = showMenuListener;
        setLayout(new BorderLayout());
        setBackground(BACKGROUND_COLOR);
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createContentPanel(), BorderLayout.CENTER);
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(91, 139, 216));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JButton backButton = createBackButton();
        headerPanel.add(backButton, BorderLayout.WEST);

        JLabel title = new JLabel("BROWSE CELESTIAL OBJECTS", JLabel.CENTER);
        title.setFont(TITLE_FONT);
        title.setForeground(Color.WHITE);
        headerPanel.add(title, BorderLayout.CENTER);

        return headerPanel;
    }

    private JButton createBackButton() {
        JButton backButton = new JButton("← Back to Menu");
        styleButton(backButton);
        backButton.addActionListener(showMenuListener);
        return backButton;
    }

    private void styleButton(JButton button) {
        button.setFont(BUTTON_FONT);
        button.setForeground(Color.WHITE);
        button.setBackground(BUTTON_COLOR);
        button.setBorder(BorderFactory.createEmptyBorder(6, 16, 6, 16));
        button.setFocusPainted(false);

        // Add mouse listener for hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(HOVER_COLOR); // Set hover color
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(BUTTON_COLOR); // Reset to normal color
            }
        });
    }

    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel(new GridLayout(1, 3, 20, 0));
        contentPanel.setBackground(BACKGROUND_COLOR);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        contentPanel.add(createCategoryPanel("STARS", dataManager.getStars()));
        contentPanel.add(createCategoryPanel("PLANETS", dataManager.getPlanets()));
        contentPanel.add(createCategoryPanel("GALAXIES", dataManager.getGalaxies()));

        return contentPanel;
    }

    private <T extends CelestialBody> JPanel createCategoryPanel(String title, ArrayList<T> objects) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(createCategoryBorder(title));
        panel.setBackground(Color.WHITE);

        // Convert ArrayList to String array
        String[] names = new String[objects.size()];
        for (int i = 0; i < objects.size(); i++) {
            names[i] = objects.get(i).getName();
        }

        // Create the JList with the names array
        JList<String> objectList = new JList<>(names);
        styleObjectList(objectList);

        panel.add(new JScrollPane(objectList), BorderLayout.CENTER);
        return panel;
    }

    private void styleObjectList(JList<String> list) {
        list.setFont(LIST_FONT);
        list.setBackground(LIST_BACKGROUND_COLOR);
        list.setForeground(LIST_FOREGROUND_COLOR);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private Border createCategoryBorder(String title) {
        return BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(BORDER_COLOR),
                        title,
                        TitledBorder.CENTER,
                        TitledBorder.TOP,
                        CATEGORY_TITLE_FONT,
                        TITLE_COLOR
                ),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );
    }
}
