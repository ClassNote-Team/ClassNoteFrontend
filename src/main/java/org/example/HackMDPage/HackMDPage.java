package org.example.HackMDPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HackMDPage {

    private final JFrame frame;
    private JPanel contentPanel;
    private String content = "";

    public HackMDPage() {
        frame = new JFrame("HackMD Page");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create toolbar
        JToolBar toolBar = new JToolBar();
        JButton fileButton = new JButton("File");
        toolBar.add(fileButton);
        fileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPopupMenu menu = new JPopupMenu();
                JMenuItem newMenuItem = new JMenuItem("New");
                JMenuItem openMenuItem = new JMenuItem("Open");
                JMenuItem saveMenuItem = new JMenuItem("Save");
                menu.add(newMenuItem);
                menu.add(openMenuItem);
                menu.add(saveMenuItem);
                menu.show(fileButton, 0, fileButton.getHeight());
            }
        });

        // change part of the code
        JButton inputButton = new JButton("Input Markdown");
        JButton displayButton = new JButton("Display Page");
        JButton splitButton = new JButton("Half Input/Display");

        // Add action listeners to the buttons
        inputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateContent();
                showInputMode();
            }
        });
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDisplayMode();
            }
        });
        splitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateContent();
                showSplitMode();
            }
        });

        toolBar.add(inputButton);
        toolBar.add(displayButton);
        toolBar.add(splitButton);
        frame.add(toolBar, BorderLayout.NORTH);

        // Show input mode by default
        showInputMode();

        frame.setVisible(true);
    }

    private void updateContent() {
        if (contentPanel instanceof SplitModePanel) {
            content = ((SplitModePanel) contentPanel).getContent();
        } else if (contentPanel instanceof InputModePanel) {
            content = ((InputModePanel) contentPanel).getContent();

        }


    }

    private void clearContent() {
        Container contentPane = frame.getContentPane();
        if (contentPanel != null) {
            contentPane.remove(contentPanel);
        }
    }

    private void refreshPage() {
        frame.revalidate(); // Revalidate to update the frame
        frame.repaint(); // Repaint to reflect changes
    }

    // Method to show input mode
    private void showInputMode() {
        clearContent();
        // Add inputArea
        contentPanel = new InputModePanel(content);
        frame.add(contentPanel, BorderLayout.CENTER);
        refreshPage();
    }

    // Method to show display mode
    private void showDisplayMode() {
        clearContent();
        // Add displayPanel
        contentPanel = new DisplayModePanel(content);
        frame.add(contentPanel, BorderLayout.CENTER);
        refreshPage();
    }

    // Method to show split mode
    private void showSplitMode() {
        clearContent();
        // Add splitPanel
        contentPanel = new SplitModePanel(content);
        frame.add(contentPanel, BorderLayout.CENTER);
        refreshPage();
    }
}
