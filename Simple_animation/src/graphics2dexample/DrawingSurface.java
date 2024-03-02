/*
 * B Cutten
    Nov 2021
    A class which allows drawing, because it extends JPanel, by way of the 
    Graphics2D class
    Because this class is only going to ever be used by the Graphics2DTester, it could be 
    included in the same file (Graphics2DTester.java)
 */
package graphics2dexample;

import java.awt.GradientPaint;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import javax.swing.JPanel;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;

public class DrawingSurface extends JPanel {

    private AffineTransform at = new AffineTransform();

    /**
     * Does the actual drawing
     *
     * @param g - the Graphics object to draw with
     */
    private void doDrawing(Graphics g) {
        //the Graphics2D class is the class that handles all the drawing
        //must be casted from older Graphics class in order to have access to some newer methods
        Graphics2D g2d = (Graphics2D) g;
        //draw a backround with grass and sky
        GradientPaint sky = new GradientPaint(100, 0, Color.BLUE, 100, 100, Color.CYAN);
        g2d.setPaint(sky);
        g2d.fillRect(0, 0, 283, 100);

        //draw a backround with grass and sky
        g2d.setColor(Color.GREEN);
        g2d.fillRect(0, 100, 283, 100);

        //draw body
        g2d.setColor(Color.cyan);
        g2d.fillRect(110, 50, 40, 50);

        //draw image on shirt
        Image shirt = new ImageIcon(this.getClass().getResource("/graphics2dexample/smiley-face-resized-final.png")).getImage();
        //Image finalShirt = ImageIcon.getImage().shirt.getScaledInstance(220, 220, Image.SCALE_SMOOTH);
//I just resized the image externaly the above code was the closed I got but has error from invoking non static method
        g2d.drawImage(shirt, 115, 55, null);

        //draw legs
        g2d.setColor(Color.BLACK);
        g2d.fillRect(110, 100, 40, 50);
        g2d.setColor(Color.GREEN);
        g2d.drawLine(130, 120, 130, 160);

        //draw head
        g2d.setColor(Color.yellow);
        g2d.fillOval(110, 11, 40, 40);

        //draw face
        g2d.rotate(0);
        g2d.setColor(Color.white);
        g2d.fillOval(118, 22, 9, 9);
        g2d.fillOval(132, 22, 9, 9);
        g2d.setColor(Color.BLACK);
        g2d.fillOval(120, 24, 4, 4);
        g2d.fillOval(134, 24, 4, 4);
        g2d.drawLine(125, 40, 135, 40);
        g2d.drawLine(130, 31, 128, 33);
        g2d.drawLine(128, 33, 130, 35);
//use for loop to randomly draw hair
        for (int i = 0; i < 100; i++) {

            g2d.drawLine((int) (Math.random() * 30) + 100, (int) (Math.random() * 9) + 6, (int) (Math.random() * 30) + 130, (int) (Math.random() * 12) + 10);

        }

        //draw left arm
        g2d.setColor(Color.cyan);
        g2d.rotate(45);
        g2d.fillRect(104, -70, 20, 25);
        g2d.setColor(Color.yellow);
        g2d.fillRect(105, -45, 20, 15);

        //draw right arm
        g2d.setColor(Color.cyan);
        g2d.rotate(180);
        g2d.fillRect(-15, 155, 20, 25);
        g2d.setColor(Color.yellow);
        g2d.fillRect(-15, 180, 20, 15);

    }

    @Override
    /**
     * Overrides paintComponent in JPanel class so that we can do our own custom
     * painting
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);//does the necessary work to prepare the panel for drawing
        doDrawing(g); //invoke our custom drawing method
    }
}
