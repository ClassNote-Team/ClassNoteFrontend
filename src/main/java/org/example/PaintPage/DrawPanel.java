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
            paintObject.draw((Graphics2D)g2);
        }
        if(currentPaintObject != null) {
            currentPaintObject.draw((Graphics2D)g2);
        }
        g.drawImage(image, 0, 0, null);
    }
    public void saveImage(String path) throws IOException {
        // TODO
        System.out.println("Saving image");
        FileOutputStream os = new FileOutputStream(path);
        // resize the image to 200x150
        // ImageIO.write(image, "PNG", os);
        BufferedImage resizedImage = new BufferedImage(200, 150, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(image, 0, 0, 200, 150, null);
        g.dispose();
        ImageIO.write(resizedImage, "PNG", os);
    }
    private class MouseHandler extends MouseAdapter {
        // creates and sets the initial position for the new shape
        public void mousePressed(MouseEvent e) {
            if(topBar.getCurrentType() == PaintObjectType.RECTANGLE) {
                currentPaintObject = new Rectangle(e.getX(), e.getY(), e.getX(), e.getY(), topBar.getCurrentColor(), topBar.getObjectSize());
            } else if(topBar.getCurrentType() == PaintObjectType.DOT) {
                paintObjects.add(new Dot(e.getX(), e.getY(), topBar.getCurrentColor(), topBar.getObjectSize()));
                repaint();
            }else if(topBar.getCurrentType() == PaintObjectType.OVAL) {
                currentPaintObject = new Oval(e.getX(), e.getY(), e.getX(), e.getY(), topBar.getCurrentColor(), topBar.getObjectSize());
            }else if(topBar.getCurrentType() == PaintObjectType.LINE) {
                currentPaintObject = new Line(e.getX(), e.getY(), e.getX(), e.getY(), topBar.getCurrentColor(), topBar.getObjectSize());
            }
        } // end method mousePressed

        // fixes the current shape onto the panel
        public void mouseReleased(MouseEvent e) {
            if(topBar.getCurrentType() == PaintObjectType.RECTANGLE) {
                ((Rectangle) currentPaintObject).setX2(e.getX());
                ((Rectangle) currentPaintObject).setY2(e.getY());
                paintObjects.add(currentPaintObject);
            }else if(topBar.getCurrentType() == PaintObjectType.OVAL) {
                ((Oval) currentPaintObject).setX2(e.getX());
                ((Oval) currentPaintObject).setY2(e.getY());
                paintObjects.add(currentPaintObject);
            }else if(topBar.getCurrentType() == PaintObjectType.LINE) {
                paintObjects.add(new Line(((Line) currentPaintObject).getX1(), ((Line) currentPaintObject).getY1(), e.getX(), e.getY(), topBar.getCurrentColor(), topBar.getObjectSize()));
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
                paintObjects.add(new Dot(e.getX(), e.getY(), topBar.getCurrentColor(), topBar.getObjectSize()));
            }
            else if(topBar.getCurrentType() == PaintObjectType.OVAL) {
                ((Oval) currentPaintObject).setX2(e.getX());
                ((Oval) currentPaintObject).setY2(e.getY());
            }
            else if(topBar.getCurrentType() == PaintObjectType.LINE) {
                currentPaintObject = new Line(((Line) currentPaintObject).getX1(), ((Line) currentPaintObject).getY1(), e.getX(), e.getY(), topBar.getCurrentColor(), topBar.getObjectSize());
            }
            repaint();
        } // end method mouseDragged

    }
}
