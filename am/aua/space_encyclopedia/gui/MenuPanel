package am.aua.space_encyclopedia.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuPanel extends JPanel {
    private static final Color[] STAR_COLORS = {
            new Color(255, 255, 255),   // White
            new Color(200, 200, 255),   // Bluish white
            new Color(255, 182, 193),   // Light pink
            new Color(173, 216, 230),   // Light cyan
            new Color(216, 191, 216),   // Lavender
            new Color(238, 130, 238)    // Violet
    };
    private JLabel title;
    private JLabel subtitle;
    private MenuButton browseBtn;
    private MenuButton searchBtn;
    private MenuButton exitBtn;

    public MenuPanel(ActionListener browseAction, ActionListener searchAction,
                                ActionListener exitAction) {
        setLayout(new GridBagLayout());

        // Initialize components
        title = createTitleLabel();
        subtitle = createSubtitleLabel();
        browseBtn = new MenuButton("Browse Celestial Bodies");
        searchBtn = new MenuButton("Search Database");
        exitBtn = new MenuButton("Exit");
        // Setup components
        browseBtn.addActionListener(browseAction);
        searchBtn.addActionListener(searchAction);
        exitBtn.addActionListener(exitAction);
        setupLayout();
        setupResponsiveBehavior();
        adjustComponentSizes(800);
    }
    private JLabel createTitleLabel() {
        JLabel label = new JLabel("SPACE ENCYCLOPEDIA", SwingConstants.CENTER);
        label.setForeground(new Color(180, 180, 190));
        return label;
    }

    private JLabel createSubtitleLabel() {
        JLabel label = new JLabel("Welcome! Let's Explore the Universe", SwingConstants.CENTER);
        label.setForeground(new Color(200, 200, 255));
        return label;
    }

    private void setupLayout() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridy = 0;
        add(title, gbc);

        gbc.gridy = 1;
        add(subtitle, gbc);

        gbc.gridy = 2;
        add(createButtonPanel(), gbc);
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 50, 10, 50);

        gbc.gridy = 0;
        panel.add(browseBtn, gbc);

        gbc.gridy = 1;
        panel.add(searchBtn, gbc);

        gbc.gridy = 2;
        panel.add(exitBtn, gbc);

        return panel;
    }

    private void setupResponsiveBehavior() {
        addComponentListener(new ComponentAdapter() { public void componentResized(ComponentEvent e) {
                adjustComponentSizes(getWidth());
            }
        });
    }

    private void adjustComponentSizes(int containerWidth) {
        int titleSize = Math.max(10, containerWidth / 20);
        int subtitleSize = Math.max(8, containerWidth / 50);
        int buttonSize = Math.max(10, containerWidth / 60);

        title.setFont(new Font("Segoi UI", Font.BOLD, titleSize));
        subtitle.setFont(new Font("Segoi UI", Font.PLAIN, subtitleSize));

        Font buttonFont = new Font("Segoi UI", Font.BOLD, buttonSize);
        browseBtn.setFont(buttonFont);
        searchBtn.setFont(buttonFont);
        exitBtn.setFont(buttonFont);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        paintBackgroundGradient(g2d);
        paintStars(g2d);
    }

    private void paintBackgroundGradient(Graphics2D g2d) {
        GradientPaint gradient = new GradientPaint(
                0, 0, new Color(10, 0, 20),
                getWidth(), getHeight(), new Color(50, 0, 70));
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    private void paintStars(Graphics2D g2d) {
        int starCount = (int)(getWidth() * getHeight() * 0.001); // Density based on size

        for (int i = 0; i < starCount; i++) {
            Color starColor = STAR_COLORS[(int)(Math.random() * STAR_COLORS.length)];
            g2d.setColor(starColor);

            int x = (int)(Math.random() * getWidth());
            int y = (int)(Math.random() * getHeight());
            int size = 1 + (int)(Math.random() * 3);

            g2d.fillOval(x, y, size, size);
        }
    }
}
