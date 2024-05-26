package org.example.base;


import javax.swing.JScrollBar;

public class BaseScrollBar extends JScrollBar {
    public BaseScrollBar(int orientation) {
        super(orientation);
        setUI(new BaseScrollBarUI());
    }

}

