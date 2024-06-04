package org.example.LoginPage;

import javax.swing.*;

import org.example.base.BaseButton;

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

    public LoginPage() {
        frame = new JFrame("Login");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        emailField = new JTextField(15);
        passwordField = new JPasswordField(15);
        loginButton = new BaseButton("Login");

        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(loginButton);

        frame.add(panel);
        frame.setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
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
            } else {
                JOptionPane.showMessageDialog(frame, "Login failed");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static int getResponseCode(String email, String password) throws IOException {
        URL url = new URL("http://localhost:8080/login");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        String jsonInputString = "{\"email\": \"" + email + "\", \"password\": \"" + password + "\"}";

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        return conn.getResponseCode();
    }
}
