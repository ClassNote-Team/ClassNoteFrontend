package org.example.HackMDPage;

import javax.swing.*;

import org.example.HackMDPage.ModePanel.DisplayModePanel;
import org.example.HackMDPage.ModePanel.InputModePanel;
import org.example.HackMDPage.ModePanel.ModePanel;
import org.example.HackMDPage.ModePanel.SplitModePanel;
import org.example.MathEditPage.MathEditPage;
import org.example.base.BaseButton;
import org.example.base.BaseToolBar;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HackMDPage implements InsertButtonHandler {

    private final JFrame frame;
    private final int buttonMargin = 3;
    private ModePanel contentPanel;
    private String content = "";
    private BaseButton inputButton;
    private BaseButton displayButton;
    private BaseButton splitButton;
    private BaseButton mathButton;

    public HackMDPage() {
        frame = new JFrame("HackMD Page");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create toolbar
        BaseToolBar toolBar = new BaseToolBar();
        BaseButton fileButton = new BaseButton("File");
        toolBar.add(fileButton);
        toolBar.addSeparator(new Dimension(buttonMargin, 0));
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
        inputButton = new BaseButton("Input Markdown");
        displayButton = new BaseButton("Display Page");
        splitButton = new BaseButton("Half Input/Display");
        mathButton = new BaseButton("Math");

        // Add action listeners to the buttons
        ButtonHandler eventHandler = new ButtonHandler();

        inputButton.addActionListener(eventHandler);
        displayButton.addActionListener(eventHandler);
        splitButton.addActionListener(eventHandler);
        mathButton.addActionListener(eventHandler);

        toolBar.add(inputButton);
        toolBar.addSeparator(new Dimension(buttonMargin, 0));
        toolBar.add(displayButton);
        toolBar.addSeparator(new Dimension(buttonMargin, 0));
        toolBar.add(splitButton);
        toolBar.addSeparator(new Dimension(buttonMargin, 0));
        toolBar.add(mathButton);
        toolBar.addSeparator(new Dimension(buttonMargin, 0));
        frame.add(toolBar, BorderLayout.NORTH);

        // Show input mode by default
        showInputMode();

        frame.setVisible(true);
    }

    private void updateContent() {
        if (contentPanel instanceof SplitModePanel) {
            content = contentPanel.getContent();
        } else if (contentPanel instanceof InputModePanel) {
            content = contentPanel.getContent();
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

    private void openMathPage() {
        MathEditPage mathEditPage = new MathEditPage();
        mathEditPage.createAndShowGUI();
        mathEditPage.setInsertHandler(this);
    }

    @Override
    public void onButtonPressed(String latex){
        latex = "$" + latex + "$";
        if (contentPanel instanceof InputModePanel){
            contentPanel.insertContent(latex);
        }
        else if (contentPanel instanceof SplitModePanel){
            contentPanel.insertContent(latex);
        }
    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == inputButton) {
                updateContent();
                System.out.println(content);
                showInputMode();
            } else if (e.getSource() == displayButton) {
                updateContent();
                showDisplayMode();
            } else if (e.getSource() == splitButton) {
                updateContent();
                showSplitMode();
            } else if (e.getSource() == mathButton) {
                openMathPage();
            }
        }
    }
}
