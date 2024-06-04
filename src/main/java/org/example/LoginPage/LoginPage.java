package org.example.LoginPage;

import javax.swing.*;

import org.example.MarkdownPage.MarkdownPage;
import org.example.base.BaseButton;
import org.example.base.BaseContants;
import org.example.base.ResponseReader;
import org.json.JSONObject;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class LoginPage{

    private JFrame frame;
    private JTextField emailField;
    private JPasswordField passwordField;
    private BaseButton loginButton;
    private BaseButton registerButton;

    public LoginPage() {
        frame = new JFrame("Login");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 5, 5));

        emailField = new JTextField(15);
        passwordField = new JPasswordField(15);
        loginButton = new BaseButton("Login");
        registerButton = new BaseButton("Register");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 5, 5));
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(buttonPanel);

        frame.add(panel);
        frame.setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterPage();
            }
        });
    }

    private void login() {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        try {
            int responseCode = getResponseCode(email, password);
            if (responseCode == HttpURLConnection.HTTP_OK) {
                JOptionPane.showMessageDialog(frame, "Login successful");
                frame.dispose();
                new MarkdownPage();
            } else {
                JOptionPane.showMessageDialog(frame, "Login failed");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static int getResponseCode(String email, String password) throws IOException {
        URL url = new URL(BaseContants.BASE_URL + "/login");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        String jsonInputString = "{\"email\": \"" + email + "\", \"password\": \"" + password + "\"}";

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        JSONObject body = ResponseReader.read(conn);
        BaseContants.USER_ID = body.getString("id");
        BaseContants.USER_NAME = body.getString("username");
        BaseContants.USER_EMAIL = body.getString("email");

        return conn.getResponseCode();
    }

}
