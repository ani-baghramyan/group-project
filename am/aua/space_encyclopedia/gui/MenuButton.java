package am.aua.space_encyclopedia.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 * {@code MenuButton} is a custom JButton with a rounded, styled appearance
 * used in the Space Encyclopedia GUI. It features custom colors,
 * mouse hover effects, and a space-themed look that matches the UI.
 */
public class MenuButton extends JButton {
    private static final Color BUTTON_COLOR = new Color(70, 70, 120);
    private static final Color HOVER_COLOR = new Color(90, 90, 140);
   // private static final Dimension BUTTON_SIZE = new Dimension(100, 60);

 /**
     * Constructs a {@code MenuButton} with the specified text label.
     * Applies custom styling, layout, font, and hover behavior.
     * @param text the label to display on the button
     */
    public MenuButton(String text) {
        super(text);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setForeground(Color.WHITE);
        setFocusPainted(false);
        setSize(Math.max(100, getWidth() / 3),  Math.max(30, getHeight() / 12));
        setLayout(new GridLayout(3, 1, 0, 20));
        setFont(new Font("Montserrat", Font.BOLD, 18));
        setBackground(new Color(30, 60, 120));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 215, 0)),
                BorderFactory.createEmptyBorder(15, 30, 15, 30)));

        addMouseListener(new MouseAdapter() {
 /**
      * Changes the button background color when mouse hovers.
      * @param e the mouse event
      */
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(HOVER_COLOR);
            }
/**
     * Reverts the button background color when mouse exits.
     * @param e the mouse event
             */
            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(BUTTON_COLOR);
            }
        });
    }
  /**
     * Paints the button with a custom rounded rectangle background
     * and handles visual states like hover and press.
     *
     * @param g the Graphics context in which to paint
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        if (getModel().isPressed()) {
            g2.setColor(HOVER_COLOR.darker());
        } else if (getModel().isRollover()) {
            g2.setColor(HOVER_COLOR);
        } else {
            g2.setColor(BUTTON_COLOR);
        }

        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        super.paintComponent(g2);
        g2.dispose();
    }
}
