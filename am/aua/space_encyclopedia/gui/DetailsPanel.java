package am.aua.space_encyclopedia.gui;

import am.aua.space_encyclopedia.core.CelestialBody;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DetailsPanel extends JPanel {
    private static final int MIN_WIDTH = 400;
    private static final int MIN_HEIGHT = 300;

    // Static final constants for  colors
    public static final Color BUTTON_COLOR = new Color(120, 170, 230);
    public static final Color HOVER_COLOR = new Color(150, 200, 255);
    private static final Color BACKGROUND_COLOR = new Color(240, 244, 250); // Background color for panels


    private ActionListener backToSearchListener;
    private JTextArea detailsArea;

    public DetailsPanel(ActionListener backToSearchListener) {
        this.backToSearchListener = backToSearchListener;
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

        JLabel title = new JLabel("CELESTIAL BODY DETAILS", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(Color.WHITE);

        headerPanel.add(title, BorderLayout.CENTER);

        return headerPanel;
    }

    private JButton createBackButton() {
        JButton backButton = new JButton("← Back to Search");
        styleButton(backButton);
        backButton.addActionListener(backToSearchListener);

        // Add hover effect using MouseListener
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

        return backButton;
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(BUTTON_COLOR);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(6, 16, 6, 16));
    }

    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(BACKGROUND_COLOR);

        JPanel whiteBoxPanel = new JPanel(new BorderLayout());
        whiteBoxPanel.setBackground(Color.WHITE);
        whiteBoxPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 210, 230)),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        detailsArea = new JTextArea();
        configureDetailsArea();

        JScrollPane scrollPane = new JScrollPane(detailsArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        whiteBoxPanel.add(scrollPane, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(20, 50, 20, 50);

        contentPanel.add(whiteBoxPanel, gbc);
        setupResizeListener();

        return contentPanel;
    }

    private void configureDetailsArea() {
        detailsArea.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        detailsArea.setEditable(false);
        detailsArea.setLineWrap(true);
        detailsArea.setWrapStyleWord(true);
        detailsArea.setBackground(Color.WHITE);
        detailsArea.setForeground(Color.DARK_GRAY);
    }

    private void setupResizeListener() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension size = getSize();

                if (size.width < MIN_WIDTH || size.height < MIN_HEIGHT) {
                    setPreferredSize(new Dimension(
                            Math.max(size.width, MIN_WIDTH),
                            Math.max(size.height, MIN_HEIGHT)
                    ));
                    revalidate();
                }
            }
        });
    }

    public void displayDetails(CelestialBody body) {
        if (body == null) {
            detailsArea.setText("No celestial body data available.");
            return;
        }

        StringBuilder details = new StringBuilder();
        details.append(body.getClass().getSimpleName().toUpperCase())
                .append("\n\n")
                .append(body)
                .append("\n\n")
                .append(body.showFacts());

        detailsArea.setText(details.toString());
        detailsArea.setCaretPosition(0);
    }
}
