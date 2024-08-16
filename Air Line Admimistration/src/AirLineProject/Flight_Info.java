package AirLineProject;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class Flight_Info extends JFrame {

    public Flight_Info() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JTable table = new JTable();

        // ScrollPane for the table
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 0, 800, 500);
        add(jsp);

        // Fetch data and set the table model
        fetchFlightData(table);

        setSize(800, 500);
        setLocation(400, 200);
        setVisible(true);
    }

    private void fetchFlightData(JTable table) {
        DBConnection conn = null;
        ResultSet rs = null;
        try {
            conn = new DBConnection();
            rs = conn.stm.executeQuery("select * from flight");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching flight data.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Closing resources
            try {
                if (rs != null) rs.close();
                if (conn != null && conn.stm != null) conn.stm.close();
                if (conn != null && conn.con != null) conn.con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Flight_Info();
    }
}
