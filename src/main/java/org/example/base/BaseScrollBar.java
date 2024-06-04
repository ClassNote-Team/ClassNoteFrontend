package org.example.base;


import javax.swing.JScrollBar;

public class BaseScrollBar extends JScrollBar {
    public BaseScrollBar(int orientation) {
        super(orientation);
        setUI(new BaseScrollBarUI());
        setUnitIncrement(32);

    }

    public BaseScrollBar(int orientation, int value, int extent, int min, int max, int width) {
        super(orientation, value, extent, min, max);
        BaseScrollBarUI ui = new BaseScrollBarUI();
        ui.setThumbWidth(width);
        ui.setTrackWidth(width);
        setUI(ui);
    }
}

