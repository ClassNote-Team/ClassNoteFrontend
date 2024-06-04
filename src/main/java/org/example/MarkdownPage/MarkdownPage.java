package org.example.MarkdownPage;

import javax.swing.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.example.MarkdownPage.ModePanel.DisplayModePanel;
import org.example.MarkdownPage.ModePanel.InputModePanel;
import org.example.MarkdownPage.ModePanel.ModePanel;
import org.example.MarkdownPage.ModePanel.SplitModePanel;
import org.example.MathEditPage.MathEditPage;
import org.example.PaintPage.PaintPage;
import org.example.base.BaseButton;
import org.example.base.BaseToolBar;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MarkdownPage implements InsertButtonHandler, InsertPaintPageImageButtonHandler, InsertOpenFileButtonHandler, SetFilenameButtonHandler{

    private final JFrame frame;
    private final int buttonMargin = 3;
    private ModePanel contentPanel;
    private String content = "";
    private BaseButton inputButton;
    private BaseButton displayButton;
    private BaseButton splitButton;
    private BaseButton mathButton;
    private BaseButton paintButton;
    private String docID = null;
    private String filename = null;

    public MarkdownPage() {
        frame = new JFrame("Markdown Page");
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
                newMenuItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        docID = null;
                        content = "";
                        filename = "";
                        showInputMode();
                    }
                });
                openMenuItem.addActionListener(new OpenFileHandler());
                saveMenuItem.addActionListener(new SaveHandler());
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
        paintButton = new BaseButton("Paint");

        // Add action listeners to the buttons
        ButtonHandler eventHandler = new ButtonHandler();

        inputButton.addActionListener(eventHandler);
        displayButton.addActionListener(eventHandler);
        splitButton.addActionListener(eventHandler);
        mathButton.addActionListener(eventHandler);
        paintButton.addActionListener(eventHandler);

        toolBar.add(inputButton);
        toolBar.addSeparator(new Dimension(buttonMargin, 0));
        toolBar.add(displayButton);
        toolBar.addSeparator(new Dimension(buttonMargin, 0));
        toolBar.add(splitButton);
        toolBar.addSeparator(new Dimension(buttonMargin, 0));
        toolBar.add(mathButton);
        toolBar.addSeparator(new Dimension(buttonMargin, 0));
        toolBar.add(paintButton);
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

    private void openPaintPage() {
        PaintPage paintPage = new PaintPage();
        paintPage.createAndShowGUI(this);
    }

    private void openFileChoose() throws IOException {
        OpenFile openFile = new OpenFile();
        openFile.chooseFile("test", this);
    }

    private void openSetFilenamePage(){
        SetFilename setFilename = new SetFilename();
        setFilename.open(this);
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

    @Override
    public void onButtonPaintPageImagePressed(String path){
        path = "![PaintPageImage](file:" + path + ")";
        if (contentPanel instanceof InputModePanel){
            contentPanel.insertContent(path);
        }
        else if (contentPanel instanceof SplitModePanel){
            contentPanel.insertContent(path);
        }
    }

    @Override
    public void onButtonOpenFilePressed(String docID) throws IOException {
        this.docID = docID;
        URL url = new URL("http://localhost:8080/markdown/" + docID);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json");
        Map<String, Object> file;
        List<String> imageList;
        String newContent;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            String inputLine;
            StringBuilder contentFromBackend = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                contentFromBackend.append(inputLine);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            file = objectMapper.readValue(contentFromBackend.toString(), new TypeReference<Map<String,Object>>(){});
            newContent = (String) file.get("content");
            imageList = (List<String>) file.get("base64Images");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        content = newContent;
        createImageFromContent(content, imageList);
        showInputMode();
        conn.disconnect();
    }

    @Override
    public void onButtonSetFilenamePressed(String filename){
        this.filename = filename;
        try {
            updateContent();
            URL url;
            HttpURLConnection conn;
            System.out.println(docID);
            System.out.println(content);

            url = new URL("http://localhost:8080/markdown");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
  
            System.out.println(conn.getRequestMethod());
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            List<String> imageList = extractFileStrings(content);
            for(int i = 0; i<imageList.size(); i++){
                imageList.set(i, imageToBase64(imageList.get(i)));
            }
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("filename", filename);
            map.put("content", content);
            map.put("userId", "test");
            map.put("base64Images", imageList);
            System.out.println("test");
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(map);
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
            if(docID == null){
                Map<String, List<String>> headers = conn.getHeaderFields();
                String location = headers.get("Location").get(0);
                int firstSlashIndex = location.indexOf('/');
                int secondSlashIndex = location.indexOf('/', firstSlashIndex + 1);
                docID = location.substring(secondSlashIndex + 1);
            }
            System.out.println(conn.getResponseCode());
            conn.disconnect();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static List<String> extractFileStrings(String input) {

        String regex = "!\\[.*?]\\(file:(.*?)\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        // 创建一个列表来存储结果
        List<String> fileList = new ArrayList<>();

        // 查找匹配的子字符串并将其添加到列表中
        while (matcher.find()) {
            String fileString = matcher.group(1);
            fileList.add(fileString);
        }

        return fileList;
    }

    public String imageToBase64(String path) throws IOException {
        //將檔案轉為 byte array
        byte[] fileContent = FileUtils.readFileToByteArray(new File(path));
        //再將Byte array 轉成 base64格式，再轉為字串
        return Base64.getEncoder().encodeToString(fileContent);
    }

    public void Base64ToImage(String base64, String path) throws IOException {
        //將base64格式的字串轉為byte array
        byte[] imageByte = Base64.getDecoder().decode(base64);
        //將byte array 寫入檔案
        FileUtils.writeByteArrayToFile(new File(path), imageByte);
    }

    public void createImageFromContent(String content, List<String> imageList) throws IOException {
        List<String> imagePathList = extractFileStrings(content);
        for(int i = 0; i<imagePathList.size(); i++){
            Base64ToImage(imageList.get(i),imagePathList.get(i));
        }
    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == inputButton) {
                updateContent();
                showInputMode();
            } else if (e.getSource() == displayButton) {
                updateContent();
                showDisplayMode();
            } else if (e.getSource() == splitButton) {
                updateContent();
                showSplitMode();
            } else if (e.getSource() == mathButton) {
                openMathPage();
            } else if (e.getSource() == paintButton) {
                openPaintPage();
            }
        }
    }

    private class OpenFileHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            try {
                openFileChoose();
            }
            catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private class SaveHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(docID == null){
                openSetFilenamePage();
            }
            else{
                try {
                    updateContent();
                    URL url;
                    HttpURLConnection conn;
                    System.out.println(docID);
                    System.out.println(content);

                    url = new URL("http://localhost:8080/markdown/" + docID);
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("PUT");

                    System.out.println(conn.getRequestMethod());
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setDoOutput(true);
                    List<String> imageList = extractFileStrings(content);
                    for(int i = 0; i<imageList.size(); i++){
                        imageList.set(i, imageToBase64(imageList.get(i)));
                    }
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    map.put("filename", filename);
                    map.put("content", content);
                    map.put("userId", "test");
                    map.put("base64Images", imageList);
                    System.out.println("test");
                    ObjectMapper mapper = new ObjectMapper();
                    String jsonString = mapper.writeValueAsString(map);
                    try (OutputStream os = conn.getOutputStream()) {
                        byte[] input = jsonString.getBytes(StandardCharsets.UTF_8);
                        os.write(input, 0, input.length);
                    }
                    if(docID == null){
                        Map<String, List<String>> headers = conn.getHeaderFields();
                        String location = headers.get("Location").get(0);
                        int firstSlashIndex = location.indexOf('/');
                        int secondSlashIndex = location.indexOf('/', firstSlashIndex + 1);
                        docID = location.substring(secondSlashIndex + 1);
                    }
                    System.out.println(conn.getResponseCode());
                    conn.disconnect();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}
