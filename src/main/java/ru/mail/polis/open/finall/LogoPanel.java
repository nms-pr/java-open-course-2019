package ru.mail.polis.open.finall;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class LogoPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        try {
            super.paintComponent(g);

            BufferedImage buffImage = ImageIO.read(LogoPanel.class.getResource("/student_login.png"));
            g.drawImage(buffImage, 0, 0, 195, 100, null);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
