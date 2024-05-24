package org.example.HackMDPage.ModePanel;

import javax.swing.*;

import java.awt.*;

public class InputModePanel extends ModePanel {

    public InputModePanel(String content) {
        setLayout(new BorderLayout());
        input.setText(content);
        input.setLineWrap(true);
        add(new JScrollPane(input), BorderLayout.CENTER);
    }

}
