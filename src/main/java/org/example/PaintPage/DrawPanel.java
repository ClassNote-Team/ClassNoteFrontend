package org.example.PaintPage;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.example.PaintPage.PaintConstant.PaintObjectType;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;

public class DrawPanel extends JPanel {
    private ArrayList<PaintObject> paintObjects = new ArrayList<PaintObject>();
    private BufferedImage image;
    private PaintObject currentPaintObject;
    private TopBar topBar;
    public DrawPanel(TopBar topBar) {
        setBackground(Color.WHITE);
        this.topBar = topBar;
        MouseHandler mouseHandler = new MouseHandler();
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
        image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
    }

    public void clearDrawing() {
        paintObjects.clear();
        currentPaintObject = null;
        repaint();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        Graphics g2 = image.createGraphics();
        for(PaintObject paintObject : paintObjects) {
            paintObject.draw(g2);
        }
        if(currentPaintObject != null) {
            currentPaintObject.draw(g2);
        }
        g.drawImage(image, 0, 0, null);
    }
    public void saveImage() throws IOException {
        // TODO
        System.out.println("Saving image");
        FileOutputStream os = new FileOutputStream("src/main/java/org/example/PaintPage/img/output.png");
        ImageIO.write(image, "PNG", os);
    }
    private class MouseHandler extends MouseAdapter {
        // creates and sets the initial position for the new shape
        public void mousePressed(MouseEvent e) {
            if(topBar.getCurrentType() == PaintObjectType.RECTANGLE) {
                currentPaintObject = new Rectangle(e.getX(), e.getY(), e.getX(), e.getY(), topBar.getCurrentColor());
            } else if(topBar.getCurrentType() == PaintObjectType.DOT) {
                paintObjects.add(new Dot(e.getX(), e.getY(), topBar.getCurrentColor()));
                repaint();
            }
        } // end method mousePressed

        // fixes the current shape onto the panel
        public void mouseReleased(MouseEvent e) {
            if(topBar.getCurrentType() == PaintObjectType.RECTANGLE) {
                ((Rectangle) currentPaintObject).setX2(e.getX());
                ((Rectangle) currentPaintObject).setY2(e.getY());
                paintObjects.add(currentPaintObject);
            }
            repaint();
        } // end method mouseReleased


        // update the shape to the current mouse position while dragging
        public void mouseDragged(MouseEvent e) {
            // TODO
            if(topBar.getCurrentType() == PaintObjectType.RECTANGLE) {
                ((Rectangle) currentPaintObject).setX2(e.getX());
                ((Rectangle) currentPaintObject).setY2(e.getY());
            }
            else if(topBar.getCurrentType() == PaintObjectType.DOT) {
                paintObjects.add(new Dot(e.getX(), e.getY(), topBar.getCurrentColor()));
            }
            repaint();
        } // end method mouseDragged

    }
}
