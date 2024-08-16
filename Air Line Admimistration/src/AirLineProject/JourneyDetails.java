package AirLineProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class JourneyDetails extends JFrame implements ActionListener {
    JTable table;
    JTextField pnr;
    JButton show;

    public JourneyDetails() {
        // Frame settings
        setTitle("Journey Details");
        setSize(800, 600);
        setLocation(400, 150);
        setLayout(null);
        getContentPane().setBackground(new Color(148, 148, 184));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // PNR Label and TextField
        JLabel lblpnr = new JLabel("PNR Details:");
        lblpnr.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblpnr.setBounds(50, 50, 100, 25);
        add(lblpnr);

        pnr = new JTextField();
        pnr.setBounds(160, 50, 120, 25);
        add(pnr);

        // Show Details Button
        show = new JButton("Show Details");
        show.setBackground(new Color(148, 184, 148));
        show.setBounds(290, 50, 120, 25);
        show.addActionListener(this);
        add(show);

        // JTable to display results
        table = new JTable();
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 100, 800, 150);
        jsp.setBackground(Color.WHITE);
        add(jsp);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == show) {
            fetchJourneyDetails();
        }
    }

    private void fetchJourneyDetails() {
        String pnrNumber = pnr.getText();
        if (pnrNumber.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a PNR number.");
            return;
        }

        try {
            DBConnection conn = new DBConnection();
            String query = "SELECT * FROM reservation WHERE PNR = '" + pnrNumber + "'";
            ResultSet rs = conn.stm.executeQuery(query);

            if (!rs.isBeforeFirst()) {
                JOptionPane.showMessageDialog(null, "No Information Found for the provided PNR.");
            } else {
                table.setModel(DbUtils.resultSetToTableModel(rs));
            }

            conn.close();  // Close the connection after the operation
        } catch (SQLException | ClassNotFoundException exp) {
            JOptionPane.showMessageDialog(null, "Error: " + exp.getMessage());
            exp.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new JourneyDetails();
    }
}