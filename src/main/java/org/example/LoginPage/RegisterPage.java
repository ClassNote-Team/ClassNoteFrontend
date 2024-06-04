package org.example.LoginPage;

import javax.swing.*;

import org.example.base.BaseButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class RegisterPage {
    private JFrame frame;
    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private BaseButton registerButton;

    public RegisterPage() {
        frame = new JFrame("Register");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        usernameField = new JTextField(15);
        emailField = new JTextField(15);
        passwordField = new JPasswordField(15);
        registerButton = new BaseButton("Register");

        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(registerButton);

        frame.add(panel);
        frame.setVisible(true);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                register();
            }
        });
    }

    private void register() {
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        try {
            int responseCode = getCode(username, email, password);
            if (responseCode == HttpURLConnection.HTTP_NO_CONTENT) {
                JOptionPane.showMessageDialog(frame, "User registered successfully");
            } else if (responseCode == HttpURLConnection.HTTP_CONFLICT) {
                JOptionPane.showMessageDialog(frame, "Email already exists");
            } else {
                JOptionPane.showMessageDialog(frame, "Registration failed");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static int getCode(String username, String email, String password) throws IOException {
        URL url = new URL("http://localhost:8080/register");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        String jsonInputString = "{\"username\": \"" + username + "\", \"email\": \"" + email + "\", \"password\": \"" + password + "\"}";

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        return conn.getResponseCode();
    }
}
