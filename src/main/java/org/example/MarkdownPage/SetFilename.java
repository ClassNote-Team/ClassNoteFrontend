package org.example.MarkdownPage;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.example.base.BaseButton;

public class SetFilename {
    private SetFilenameButtonHandler handler;
    public void open(SetFilenameButtonHandler handler) {
        this.handler = handler;
        JFrame frame = new JFrame("Set Filename");
        frame.setLayout(new GridLayout(0,1));
        JTextArea textArea = new JTextArea();
        frame.add(textArea);
        JPanel panel = new JPanel(new BorderLayout());
        BaseButton button = new BaseButton("Confirm");
        button.addActionListener(e->{
            handler.onButtonSetFilenamePressed(textArea.getText());
            frame.dispose();
        });
        panel.add(button, BorderLayout.EAST);
        frame.add(panel);
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
