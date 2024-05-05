import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HackMDPage {

    private JFrame frame;
    private JTextArea inputArea;
    private JPanel displayPanel;
    private JSplitPane splitPane;

    public void createAndShowGUI() {
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

    private void clearContent() {
        Container contentPane = frame.getContentPane();
        if (inputArea != null)
            contentPane.remove(inputArea);
        if (displayPanel != null)
            contentPane.remove(displayPanel);
        if (splitPane != null)
            contentPane.remove(splitPane);
    }

    private void refreshContent() {
        frame.revalidate(); // Revalidate to update the frame
        frame.repaint(); // Repaint to reflect changes
    }

    // Method to show input mode
    private void showInputMode() {
        clearContent();
        // Add inputArea
        inputArea = new JTextArea();
        frame.add(inputArea, BorderLayout.CENTER);

        frame.revalidate(); // Revalidate to update the frame
    }

    // Method to show display mode
    private void showDisplayMode() {
        clearContent();
        // Add displayPanel
        displayPanel = new JPanel();
        frame.add(displayPanel, BorderLayout.CENTER);

        frame.revalidate(); // Revalidate to update the frame
    }

    // Method to show split mode
    private void showSplitMode() {
        clearContent();
        // Add splitPane
        inputArea = new JTextArea();
        displayPanel = new JPanel();
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, inputArea, displayPanel);
        splitPane.setResizeWeight(0.5);
        frame.add(splitPane, BorderLayout.CENTER);

        frame.revalidate(); // Revalidate to update the frame
    }
}
