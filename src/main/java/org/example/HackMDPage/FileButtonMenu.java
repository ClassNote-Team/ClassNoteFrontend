package org.example.HackMDPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FileButtonMenu {
    private final JButton fileButton;

    public FileButtonMenu(ActionListener listener) {
        fileButton = new JButton("檔案");
        fileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPopupMenu menu = new JPopupMenu();
                JMenuItem newMenuItem = new JMenuItem("開啟新檔");
                JMenuItem openMenuItem = new JMenuItem("開啟舊檔");
                JMenuItem saveMenuItem = new JMenuItem("儲存檔案");


                newMenuItem.addActionListener(listener);
                openMenuItem.addActionListener(listener);
                saveMenuItem.addActionListener(listener);

                menu.add(newMenuItem);
                menu.add(openMenuItem);
                menu.add(saveMenuItem);
                menu.show(fileButton, 0, fileButton.getHeight());
            }
        });
    }

    public JButton getFileButton() {
        return fileButton;
    }
}