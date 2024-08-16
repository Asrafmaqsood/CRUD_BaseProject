package AirLineProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCustDetails extends JFrame implements ActionListener {

    private JLabel heading, imagelb, namelb, nationalitylb, adharlb, addresslb, genderlb, phonelb;
    private JTextField nameTF, nationalityTF, adharTf, addressTf, phoneTF;
    private JRadioButton male, female;
    private Button savebtn, backbtn;

    public AddCustDetails() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Add Customer Details");
        setBounds(400, 100, 900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(148, 148, 184));
        setLayout(null);

        heading = createLabel("ADD CUSTOMER DETAILS", 260, 25, 400, 40, new Font("Raleway", Font.BOLD, 25), Color.BLUE);
        add(heading);

        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("AirLineProject/Images/AddCustpic.jpg"));
        imagelb = new JLabel(icon);
        imagelb.setBounds(480, 200, 400, 250);
        add(imagelb);

        Font labelFont = new Font("SansSerif", Font.BOLD, 15);

        namelb = createLabel("Full Name", 50, 160, 100, 30, labelFont, Color.BLACK);
        nameTF = createTextField(180, 160, 250, 30);

        nationalitylb = createLabel("Nationality", 50, 220, 100, 30, labelFont, Color.BLACK);
        nationalityTF = createTextField(180, 220, 250, 30);

        adharlb = createLabel("Adhar number", 50, 280, 100, 30, labelFont, Color.BLACK);
        adharTf = createTextField(180, 280, 250, 30);

        addresslb = createLabel("Address", 50, 340, 100, 30, labelFont, Color.BLACK);
        addressTf = createTextField(180, 340, 250, 30);

        genderlb = createLabel("Gender", 50, 400, 100, 30, labelFont, Color.BLACK);
        male = createRadioButton("Male", 180, 400, 70, 25);
        female = createRadioButton("Female", 280, 400, 70, 25);
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);

        phonelb = createLabel("Phone number", 50, 460, 150, 30, labelFont, Color.BLACK);
        phoneTF = createTextField(180, 460, 250, 30);

        savebtn = createButton("Save", 140, 550, 120, 30, this);
        backbtn = createButton("Back", 300, 550, 120, 30, this);

        addComponents();
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

    private JRadioButton createRadioButton(String text, int x, int y, int width, int height) {
        JRadioButton radioButton = new JRadioButton(text);
        radioButton.setBounds(x, y, width, height);
        radioButton.setBackground(new Color(148, 148, 184));
        return radioButton;
    }

    private Button createButton(String text, int x, int y, int width, int height, ActionListener listener) {
        Button button = new Button(text);
        button.setBounds(x, y, width, height);
        button.setBackground(new Color(148, 184, 148));
        button.setForeground(Color.BLACK);
        button.setFont(new Font("SansSerif", Font.BOLD, 15));
        button.addActionListener(listener);
        return button;
    }

    private void addComponents() {
        add(namelb);
        add(nameTF);
        add(nationalitylb);
        add(nationalityTF);
        add(adharlb);
        add(adharTf);
        add(addresslb);
        add(addressTf);
        add(genderlb);
        add(male);
        add(female);
        add(phonelb);
        add(phoneTF);
        add(savebtn);
        add(backbtn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == savebtn) {
            handleSave();
        } else if (e.getSource() == backbtn) {
            handleBack();
        }
    }

    private void handleSave() {
        String name = nameTF.getText();
        String nationality = nationalityTF.getText();
        String adhar = adharTf.getText();
        String address = addressTf.getText();
        String phonenum = phoneTF.getText();
        String gender = male.isSelected() ? "Male" : "Female";

        if (name.isEmpty() || nationality.isEmpty() || adhar.isEmpty() || address.isEmpty() || phonenum.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all the details");
        } else {
            try {
                DBConnection con = new DBConnection();
                String query = "INSERT INTO PassengerDetails (name, nationality, adhar, address, gender, phonenum) VALUES ('" +
                        name + "', '" + nationality + "', '" + adhar + "', '" + address + "', '" + gender + "', '" + phonenum + "')";
                con.stm.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Details Saved Successfully");
                setVisible(false);
                new Home();
            } catch (Exception exp) {
                exp.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + exp.getMessage());
            }
        }
    }

    private void handleBack() {
        setVisible(false);
        new Home();
    }

    public static void main(String[] args) {
        new AddCustDetails();
    }
}

