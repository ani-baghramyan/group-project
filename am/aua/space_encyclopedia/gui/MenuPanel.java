package am.aua.space_encyclopedia.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * The code>MenuPanel</code>  class represents the main menu panel
 * of the Space Encyclopedia application. It displays the title,
 * subtitle, and three action buttons: Browse, Search, and Exit.
 * It also features a gradient space-themed background with randomly
 * generated animated stars, and adjusts UI element sizes dynamically
 * based on the window width.
 
 * An object of type <code>MenuPanel</code> contains a field of type <code>JLabel</code> 
 * for the title, a <code>JLabel</code> for the subtitle, three fields of type <code>MenuButton</code> 
 * the buttons (Browse, Search, and Exit).
 */
public class MenuPanel extends JPanel {
    /**
     * The factor used to calculate star density based on the panel's dimensions.
     * Higher values result in more stars being drawn.
     */
    private static final double STAR_DENSITY_FACTOR = 0.001;
    /**
     * The minimum font size for the title label. Ensures that the title does not
     * get too small when the window width is small.
     */
    private static final int MIN_TITLE_SIZE = 10;
     /**
     * The minimum font size for the subtitle label. Ensures that the subtitle does not
     * get too small when the window width is small.
     */
    private static final int MIN_SUBTITLE_SIZE = 8;
    /**
     * The minimum font size for the buttons. Ensures that buttons do not become too small
     * when the window width is small.
     */
    private static final int MIN_BUTTON_FONT_SIZE = 8;
    /**
     * The color used for the title text. A light grey color.
     */
    private static final Color TITLE_COLOR = new Color(180, 180, 190);
    /**
     * The color used for the subtitle text. A light bluish color.
     */
    private static final Color SUBTITLE_COLOR = new Color(200, 200, 255);
    /**
     * The font used for all buttons. 
     */
    private static final String PRIMARY_FONT = "SansSerif";
    
    /** An array of colors used to render randomly generated stars. */
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
    /**
     * Constructs a {@code MenuPanel} with specified action listeners for each of the menu buttons.
     * @param browseAction the action performed when the "Browse" button is clicked
     * @param searchAction the action performed when the "Search" button is clicked
     * @param exitAction   the action performed when the "Exit" button is clicked
     */
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
        /**
     * Creates the title label component.
     * @return a styled JLabel representing the title
     */
    private JLabel createTitleLabel() {
        JLabel label = new JLabel("SPACE ENCYCLOPEDIA", SwingConstants.CENTER);
        label.setForeground(TITLE_COLOR);
        return label;
    }
    /**
     * Creates the subtitle label component.
     * @return a styled JLabel representing the subtitle
     */
    private JLabel createSubtitleLabel() {
        JLabel label = new JLabel("Welcome! Let's Explore the Universe", SwingConstants.CENTER);
        label.setForeground(SUBTITLE_COLOR );
        return label;
    }
    
    /**
     * Sets up the main layout of the panel using GridBagLayout.
     */
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
    /**
     * Creates a subpanel containing the menu buttons.
     * @return a JPanel with buttons arranged vertically
     */
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
   /**
     * Sets up a listener to adjust font sizes when the panel is resized.
     */
    private void setupResponsiveBehavior() {
        addComponentListener(new ComponentAdapter() { public void componentResized(ComponentEvent e) {
                adjustComponentSizes(getWidth());
            }
        });
    }
    /**
     * Adjusts the font sizes of labels and buttons based on the panel width.
     * @param containerWidth the current width of the container
     */
    private void adjustComponentSizes(int containerWidth) {
        int titleSize = Math.max(MIN_TITLE_SIZE , containerWidth / 20);
        int subtitleSize = Math.max(MIN_SUBTITLE_SIZE , containerWidth / 50);
        int buttonSize = Math.max(MIN_BUTTON_FONT_SIZE , containerWidth / 60);

        title.setFont(new Font(PRIMARY_FONT, Font.BOLD, titleSize));
        subtitle.setFont(new Font(PRIMARY_FONT, Font.PLAIN, subtitleSize));

        Font buttonFont = new Font(PRIMARY_FONT, Font.BOLD, buttonSize);
        browseBtn.setFont(buttonFont);
        searchBtn.setFont(buttonFont);
        exitBtn.setFont(buttonFont);
    }
    /**
     * Overrides paintComponent to draw a gradient background and stars.
     * @param g the Graphics context in which to paint
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        paintBackgroundGradient(g2d);
        paintStars(g2d);
    }
    /**
     * Paints the space-themed gradient background.
     * @param g2d the Graphics2D context used for drawing
     */
    private void paintBackgroundGradient(Graphics2D g2d) {
        GradientPaint gradient = new GradientPaint(
                0, 0, new Color(10, 0, 20),
                getWidth(), getHeight(), new Color(50, 0, 70));
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
    /**
     * Randomly paints star-like dots across the panel.
     * @param g2d the Graphics2D context used for drawing
     */
    private void paintStars(Graphics2D g2d) {
        int starCount = (int)(getWidth() * getHeight() * STAR_DENSITY_FACTOR); // Density based on size

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
