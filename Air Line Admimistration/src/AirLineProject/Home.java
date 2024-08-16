package AirLineProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame implements ActionListener {

    private JLabel img, heading;
    private JMenuBar menuBar;
    private JMenuItem flightDetailsItem, customerDetailsItem, bookFlightItem, journeyDetailsItem, ticketCancellationItem, boardingPassItem;
    private JMenu detailsMenu, ticketMenu;

    public Home() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Air India - Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("AirLineProject/Images/Homebhg.jpg"));
        img = new JLabel(icon);
        img.setLayout(null);
        add(img);

        heading = new JLabel("WELCOME TO AIR INDIA");
        heading.setBounds(600, 20, 400, 40);
        heading.setFont(new Font("Raleway", Font.BOLD, 25));
        heading.setForeground(new Color(83,7,7));
        img.add(heading);

        setupMenuBar();
        setVisible(true);
    }

    private void setupMenuBar() {
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        detailsMenu = new JMenu("Details");
        menuBar.add(detailsMenu);

        flightDetailsItem = createMenuItem("Flight Details", detailsMenu);
        customerDetailsItem = createMenuItem("Add Customer Details", detailsMenu);
        bookFlightItem = createMenuItem("Book Flight", detailsMenu);
        journeyDetailsItem = createMenuItem("Journey Details", detailsMenu);
        ticketCancellationItem = createMenuItem("Ticket Cancellation", detailsMenu);

        ticketMenu = new JMenu("Ticket");
        menuBar.add(ticketMenu);

        boardingPassItem = createMenuItem("Boarding Pass", ticketMenu);
    }

    private JMenuItem createMenuItem(String text, JMenu parentMenu) {
        JMenuItem menuItem = new JMenuItem(text);
        parentMenu.add(menuItem);
        menuItem.addActionListener(this);
        return menuItem;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "Add Customer Details":
                new AddCustDetails();
                break;
            case "Flight Details":
                new Flight_Info();
                break;
            case "Book Flight":
                new Book_Flight();
                break;
            case "Journey Details":
                new JourneyDetails();
                break;
            case "Ticket Cancellation":
                new Cancellation();
                break;
            case "Boarding Pass":
                new BoardingPass();
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {
        new Home();
    }
}