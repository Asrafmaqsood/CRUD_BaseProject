package AirLineProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BoardingPass extends JFrame implements ActionListener {

    private JTextField pnrField;
    private JLabel nameLabel, nationalityLabel, srcLabel, destLabel, flightNameLabel, flightCodeLabel, dateLabel;
    private JButton fetchButton;

    public BoardingPass() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Boarding Pass");
        setLayout(null);
        getContentPane().setBackground(new Color(148, 148, 184));
        setBounds(300, 150, 1000, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createLabels();
        createTextField();
        createButtons();
        addImage();

        setVisible(true);
    }

    private void createLabels() {
        JLabel heading = createLabel("AIR INDIA", new Font("SansSerif", Font.PLAIN, 32), 350, 8, 450, 35);
        JLabel subheading = createLabel("Boarding Pass", new Font("SansSerif", Font.PLAIN, 24), 340, 50, 300, 30);
        subheading.setForeground(new Color(139, 46, 9));

        JLabel pnrLabel = createLabel("PNR DETAILS", new Font("Tahoma", Font.PLAIN, 16), 60, 100, 150, 25);
        JLabel nameLabel = createLabel("NAME", new Font("Tahoma", Font.PLAIN, 16), 60, 140, 150, 25);
        this.nameLabel = createLabel("", new Font("Tahoma", Font.PLAIN, 16), 220, 140, 150, 25);

        JLabel nationalityLabel = createLabel("NATIONALITY", new Font("Tahoma", Font.PLAIN, 16), 60, 180, 150, 25);
        this.nationalityLabel = createLabel("", new Font("Tahoma", Font.PLAIN, 16), 220, 180, 150, 25);

        JLabel srcLabel = createLabel("SRC", new Font("Tahoma", Font.PLAIN, 16), 60, 220, 150, 25);
        this.srcLabel = createLabel("", new Font("Tahoma", Font.PLAIN, 16), 220, 220, 150, 25);

        JLabel destLabel = createLabel("DEST", new Font("Tahoma", Font.PLAIN, 16), 380, 220, 150, 25);
        this.destLabel = createLabel("", new Font("Tahoma", Font.PLAIN, 16), 540, 220, 150, 25);

        JLabel flightNameLabel = createLabel("Flight Name", new Font("Tahoma", Font.PLAIN, 16), 60, 260, 150, 25);
        this.flightNameLabel = createLabel("", new Font("Tahoma", Font.PLAIN, 16), 220, 260, 150, 25);

        JLabel flightCodeLabel = createLabel("Flight Code", new Font("Tahoma", Font.PLAIN, 16), 380, 260, 150, 25);
        this.flightCodeLabel = createLabel("", new Font("Tahoma", Font.PLAIN, 16), 540, 260, 150, 25);

        JLabel dateLabel = createLabel("Date", new Font("Tahoma", Font.PLAIN, 16), 60, 300, 150, 25);
        this.dateLabel = createLabel("", new Font("Tahoma", Font.PLAIN, 16), 220, 300, 150, 25);
    }

    private JLabel createLabel(String text, Font font, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        label.setFont(font);
        add(label);
        return label;
    }

    private void createTextField() {
        pnrField = new JTextField();
        pnrField.setBounds(220, 100, 150, 25);
        add(pnrField);
    }

    private void createButtons() {
        fetchButton = new JButton("Enter");
        fetchButton.setBounds(390, 98, 120, 30);
        fetchButton.setFont(new Font("SansSerif", Font.BOLD, 20));
        fetchButton.setBackground(new Color(148, 184, 148));
        fetchButton.addActionListener(this);
        add(fetchButton);
    }

    private void addImage() {
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("AirLineProject/Images/airindia.png"));
        Image scaledImage = icon.getImage().getScaledInstance(300, 230, Image.SCALE_DEFAULT);
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
        imageLabel.setBounds(600, 0, 300, 300);
        add(imageLabel);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String pnr = pnrField.getText();

        try {
            DBConnection conn = new DBConnection();
            String query = "SELECT * FROM reservation WHERE PNR = '" + pnr + "'";
            ResultSet rs = conn.stm.executeQuery(query);

            if (rs.next()) {
                nameLabel.setText(rs.getString("name"));
                nationalityLabel.setText(rs.getString("nationality"));
                srcLabel.setText(rs.getString("src"));
                destLabel.setText(rs.getString("des"));
                flightNameLabel.setText(rs.getString("flightname"));
                flightCodeLabel.setText(rs.getString("flightcode"));
                dateLabel.setText(rs.getString("ddate"));
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a correct PNR");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new BoardingPass();
    }
}