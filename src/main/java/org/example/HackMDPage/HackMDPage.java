package org.example.HackMDPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HackMDPage implements ActionListener {

    private final JFrame frame;
    private JPanel contentPanel;
    private String content = "";
    private FileButtonMenu fileButtonMenu;
    private Boolean FileSaveStatus = false;

    public HackMDPage() {
        frame = new JFrame("HackMD Page");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create toolbar
        JToolBar toolBar = new JToolBar();

        // Integrate FileButtonMenu
        FileButtonMenu fileButtonMenu = new FileButtonMenu(this);
        toolBar.add(fileButtonMenu.getFileButton());

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
        } else {
            return;
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

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "開啟新檔":
                content = "";
                showInputMode();
                break;
            case "開啟舊檔":
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    // Read the file
                    // Update the content
                    // Show the input mode
                }
                break;
            case "儲存檔案":
                JFileChooser fileSaver = new JFileChooser();
                int returnVal = fileSaver.showSaveDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    // Save the file
                }
                break;
        }
    }
}
