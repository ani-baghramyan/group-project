package am.aua.space_encyclopedia.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuButton extends JButton {
    private static final Color BUTTON_COLOR = new Color(70, 70, 120);
    private static final Color HOVER_COLOR = new Color(90, 90, 140);
   // private static final Dimension BUTTON_SIZE = new Dimension(100, 60);

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
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(HOVER_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(BUTTON_COLOR);
            }
        });
    }

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
