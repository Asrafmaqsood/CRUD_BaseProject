package AirLineProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {

    private JLabel imgLabel, usernameLabel, passwordLabel;
    private JTextField usernameField, passwordField;
    private Button loginButton, resetButton, closeButton;

    public Login() {
        initializeComponents();
        setupLayout();
        addActionListeners();

        setTitle("Airline Login");
        setBounds(400, 200, 600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeComponents() {
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("AirLineProject/Images/loginbg.jpg"));
        imgLabel = new JLabel(icon);

        usernameLabel = createLabel("Username", 80, 50);
        passwordLabel = createLabel("Password", 80, 120);

        usernameField = createTextField("Enter Username", 250, 50);
        passwordField = createTextField("Enter Password", 250, 120);

        loginButton = createButton("Login", 150, 200);
        resetButton = createButton("Reset", 350, 200);
        closeButton = createButton("Close", 250, 280);
    }

    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 100, 20);
        label.setFont(new Font("SansSerif", Font.BOLD, 20));
        label.setForeground(Color.PINK);
        return label;
    }

    private JTextField createTextField(String placeholder, int x, int y) {
        JTextField textField = new JTextField(placeholder);
        textField.setBounds(x, y, 250, 30);
        return textField;
    }

    private Button createButton(String label, int x, int y) {
        Button button = new Button(label);
        button.setBounds(x, y, 120, 30);
        button.setFont(new Font("SansSerif", Font.BOLD, 20));
        button.setBackground(new Color(148, 184, 148));
        button.setForeground(Color.BLACK);
        return button;
    }

    private void setupLayout() {
        imgLabel.setBounds(0, 0, 600, 400);
        setLayout(null);
        add(imgLabel);

        imgLabel.add(usernameLabel);
        imgLabel.add(passwordLabel);
        imgLabel.add(usernameField);
        imgLabel.add(passwordField);
        imgLabel.add(loginButton);
        imgLabel.add(resetButton);
        imgLabel.add(closeButton);
    }

    private void addActionListeners() {
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        closeButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            handleLogin();
        } else if (e.getSource() == resetButton) {
            handleReset();
        } else if (e.getSource() == closeButton) {
            handleClose();
        }
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        try {
            DBConnection con = new DBConnection();
            String query = "SELECT * FROM login WHERE username = '" + username + "' AND password = '" + password + "'";
            ResultSet result = con.stm.executeQuery(query);

            if (result.next()) {
                JOptionPane.showMessageDialog(this, "Login Successfully");
                setVisible(false);
                new Home();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    private void handleReset() {
        usernameField.setText(null);
        passwordField.setText(null);
    }

    private void handleClose() {
        setVisible(false);
        dispose();
    }

    public static void main(String[] args) {
        new Login();
    }
}

