package org.example.MarkdownPage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import org.example.base.BaseButton;
import org.example.base.BaseContants;

public class OpenFile {
    private InsertOpenFileButtonHandler handler;

    public void chooseFile(String userId, InsertOpenFileButtonHandler insertOpenFileButtonHandler) throws IOException {
        this.handler = insertOpenFileButtonHandler;
        JFrame frame = new JFrame("Open File");
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        URL url = new URL(BaseContants.BASE_URL + "/markdown/fileList/" + userId);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json");
        List<Map<String, Object>> fileList;
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            String inputLine;
            StringBuilder contentFromBackend = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                contentFromBackend.append(inputLine);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            fileList = objectMapper.readValue(contentFromBackend.toString(),
                    new TypeReference<List<Map<String, Object>>>() {
                    });
        }
        for (Map<String, Object> file : fileList) {
            BaseButton button = new BaseButton((String) file.get("filename"));
            button.addActionListener(e -> {
                try {
                    handler.onButtonOpenFilePressed((String) file.get("id"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                frame.dispose();
                conn.disconnect();
            });
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
            panel.add(button, BorderLayout.CENTER);
            frame.add(panel);
        }
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}
