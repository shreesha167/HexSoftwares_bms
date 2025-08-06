package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener{

    JLabel l1, l2, l3, l4;
    JButton b1, b2, b3;
    JTextField t1;
    JPasswordField p2;

    Login (){
        setTitle("Automated Teller Machine");
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.png"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);

        JLabel l1 = new JLabel(i3);
        l1.setBounds(70, 10, 100, 100);
        add(l1);

        JLabel l2 = new JLabel("Welcome to ATM");
        l2.setFont(new Font("Osward", Font.BOLD, 38));
        l2.setBounds(200, 40, 400, 40);
        add(l2);

        JLabel l3 = new JLabel("Card Number:");
        l3.setFont(new Font("Raleway", Font.BOLD, 28));
        l3.setBounds(200, 150, 200, 40);
        add(l3);
        t1 = new JTextField();
        t1.setBounds(getBounds().x + 400, 150, 230, 40);
        t1.setFont(new Font("Arial", Font.PLAIN, 14));
        add(t1);

        JLabel l4 = new JLabel("Pin Number:");
        l4.setFont(new Font("Raleway", Font.BOLD, 28));
        l4.setBounds(200, 200, 200, 40);
        add(l4);
        p2 = new JPasswordField();
        p2.setBounds(getBounds().x + 400, 200, 230, 40);
        p2.setFont(new Font("Arial", Font.PLAIN, 14));
        add(p2);

        b1 = new JButton("Sign in");
        b1.setBounds(400, 300, 100, 40);
        b1.addActionListener(this); 
        add(b1);

        b2 = new JButton("Clear");
        b2.setBounds(520, 300, 110, 40);
        b2.addActionListener(this);
        add(b2);

        b3 = new JButton("Sign up");
        b3.setBounds(400, 360, 230, 40);
        b3.addActionListener(this);
        add(b3);

        getContentPane().setBackground(Color.WHITE);
        setSize(900,500);
        setVisible(true);
        setLocation(300, 100);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            if(ae.getSource() == b1) {
                Conn c1 = new Conn();
                String cardno  = t1.getText();
                String pin  = new String(p2.getPassword());
                String q  = "select * from login where cardno = '"+cardno+"' and pin = '"+pin+"'";

                ResultSet rs = c1.s.executeQuery(q);
                if(rs.next()){
                    setVisible(false);
                    new Transactions(pin).setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or PIN");
                }
            }
            else if(ae.getSource() == b2) {
                t1.setText("");
                p2.setText("");
            }
            else if(ae.getSource() == b3) {
                setVisible(false);
                new Signup().setVisible(true);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    public static void main(String[] args) {
        new Login();
    }

}