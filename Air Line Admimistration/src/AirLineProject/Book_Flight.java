package AirLineProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Random;
import com.toedter.calendar.JDateChooser;

public class Book_Flight extends JFrame implements ActionListener {

    private JTextField tfaadhar;
    private JLabel tfname, tfnationality, tfaddress, labelgender, labelfname, labelfcode;
    private JButton bookflight, fetchButton, flight;
    private Choice source, destination;
    private JDateChooser dcdate;

    public Book_Flight() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Book Flight");
        getContentPane().setBackground(new Color(148, 148, 184));
        setLayout(null);
        setBounds(200, 50, 1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel heading = createLabel("Book Flight", 420, 20, 500, 35, new Font("Tahoma", Font.PLAIN, 32), Color.BLUE);
        add(heading);

        add(createLabel("Aadhar", 60, 80, 150, 25, new Font("Tahoma", Font.PLAIN, 16), Color.BLACK));
        tfaadhar = createTextField(220, 80, 150, 25);
        add(tfaadhar);

        fetchButton = createButton("Fetch User", 390, 80, 120, 25, this);
        add(fetchButton);

        tfname = createLabelField("Name", 130);
        tfnationality = createLabelField("Nationality", 180);
        tfaddress = createLabelField("Address", 230);
        labelgender = createLabelField("Gender", 280);

        JLabel lblsource = createLabel("Source", 60, 330, 150, 25, new Font("Tahoma", Font.PLAIN, 16), Color.BLACK);
        add(lblsource);

        source = new Choice();
        source.setBounds(220, 330, 150, 30);
        add(source);

        JLabel lbldest = createLabel("Destination", 60, 380, 150, 30, new Font("Tahoma", Font.PLAIN, 16), Color.BLACK);
        add(lbldest);

        destination = new Choice();
        destination.setBounds(220, 380, 150, 35);
        add(destination);

        loadFlightOptions();

        flight = createButton("Fetch Flights", 390, 380, 150, 25, this);
        add(flight);

        labelfname = createLabelField("Flight Name", 430);
        labelfcode = createLabelField("Flight Code", 480);

        JLabel lbldate = createLabel("Date of Travel", 60, 530, 150, 30, new Font("Tahoma", Font.PLAIN, 16), Color.BLACK);
        add(lbldate);

        dcdate = new JDateChooser();
        dcdate.setBounds(220, 530, 150, 30);
        add(dcdate);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("AirLineProject/Images/bookpic.jpg"));
        Image i2 = i1.getImage().getScaledInstance(450, 320, Image.SCALE_DEFAULT);
        JLabel lblimage = new JLabel(new ImageIcon(i2));
        lblimage.setBounds(550, 80, 500, 410);
        add(lblimage);

        bookflight = createButton("Book Flight", 220, 580, 120, 30, this);
        add(bookflight);

        setVisible(true);
    }

    private JLabel createLabel(String text, int x, int y, int width, int height, Font font, Color color) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        label.setFont(font);
        label.setForeground(color);
        return label;
    }

    private JTextField createTextField(int x, int y, int width, int height) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, width, height);
        return textField;
    }

    private JLabel createLabelField(String labelText, int yPos) {
        add(createLabel(labelText, 60, yPos, 150, 25, new Font("Tahoma", Font.PLAIN, 16), Color.BLACK));
        JLabel label = new JLabel();
        label.setBounds(220, yPos, 150, 25);
        add(label);
        return label;
    }

    private JButton createButton(String text, int x, int y, int width, int height, ActionListener listener) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setBackground(new Color(148, 184, 148));
        button.setFont(new Font("SansSerif", Font.BOLD, 15));
        button.addActionListener(listener);
        return button;
    }

    private void loadFlightOptions() {
        try {
            DBConnection c = new DBConnection();
            ResultSet rs = c.stm.executeQuery("SELECT DISTINCT source, destination FROM flight");

            while (rs.next()) {
                source.add(rs.getString("source"));
                destination.add(rs.getString("destination"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == fetchButton) {
            fetchPassengerDetails();
        } else if (ae.getSource() == flight) {
            fetchFlightDetails();
        } else if (ae.getSource() == bookflight) {
            bookFlight();
        }
    }

    private void fetchPassengerDetails() {
        String aadhar = tfaadhar.getText();

        try {
            DBConnection conn = new DBConnection();
            ResultSet rs = conn.stm.executeQuery("SELECT * FROM PassengerDetails WHERE adhar = '" + aadhar + "'");

            if (rs.next()) {
                tfname.setText(rs.getString("name"));
                tfnationality.setText(rs.getString("nationality"));
                tfaddress.setText(rs.getString("address"));
                labelgender.setText(rs.getString("gender"));
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a correct Aadhar number");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fetchFlightDetails() {
        String src = source.getSelectedItem();
        String dest = destination.getSelectedItem();

        try {
            DBConnection conn = new DBConnection();
            ResultSet rs = conn.stm.executeQuery("SELECT * FROM flight WHERE Source = '" + src + "' AND Destination = '" + dest + "'");

            if (rs.next()) {
                labelfname.setText(rs.getString("Flight_Name"));
                labelfcode.setText(rs.getString("Flight_Code"));
            } else {
                JOptionPane.showMessageDialog(null, "No Flights Found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void bookFlight() {
        Random random = new Random();
        String aadhar = tfaadhar.getText();
        String name = tfname.getText();
        String nationality = tfnationality.getText();
        String flightname = labelfname.getText();
        String flightcode = labelfcode.getText();
        String src = source.getSelectedItem();
        String dest = destination.getSelectedItem();
        String ddate = ((JTextField) dcdate.getDateEditor().getUiComponent()).getText();

        if (aadhar.isEmpty() || name.isEmpty() || nationality.isEmpty() || flightname.isEmpty() || flightcode.isEmpty() || src.isEmpty() || dest.isEmpty() || ddate.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all the details carefully!");
            return;
        }

        try {
            DBConnection conn = new DBConnection();
            String query = String.format("INSERT INTO reservation VALUES('PNR-%d', 'Ticket_Id%d', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                    random.nextInt(1000000), random.nextInt(10000), aadhar, name, nationality, flightname, flightcode, src, dest, ddate);

            conn.stm.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Ticket Booked Successfully");
            setVisible(false);
            new Home();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Book_Flight();
    }
}