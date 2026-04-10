package User_authentication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

public class RegisterForm extends JFrame implements ActionListener {
    ArrayList<User> user;

    JLabel l1,l2,l3,l4,l5,l6,l7;
    JTextField t1,t2,t3,t4,t5,t6;
    JButton b1;
    Connection conn;
    RegisterForm(ArrayList<User> user,Connection conn)
    {
        this.conn=conn;
        l1=new JLabel("New User Register");
        l1.setFont(new Font("Times New Roman",Font.PLAIN,32));
        l1.setBounds(220,100,250,60);

        l2=new JLabel("First name");
        l2.setBounds(30,200,100,40);

        t1=new JTextField();
        t1.setBounds(130,200,150,40);

        l3=new JLabel("Last name");
        l3.setBounds(30,250,100,40);

        t2=new JTextField();
        t2.setBounds(130,250,150,40);

        l4=new JLabel("Email Address");
        l4.setBounds(30,300,100,40);

        t3=new JTextField();
        t3.setBounds(130,300,150,40);

        l5=new JLabel("Username");
        l5.setBounds(330,200,100,40);

        t4=new JTextField();
        t4.setBounds(430,200,150,40);

        l6=new JLabel("Password");
        l6.setBounds(330,250,100,40);

        t5=new JTextField();
        t5.setBounds(430,250,150,40);

        l7=new JLabel("Mobile Number");
        l7.setBounds(330,300,100,40);

        t6=new JTextField();
        t6.setBounds(430,300,150,40);

        b1=new JButton("Register");
        b1.setFocusable(false);
        b1.setBounds(250,350,100,50);
        b1.addActionListener(this);



        this.add(l1);
        this.add(l2);
        this.add(l3);
        this.add(l4);
        this.add(l5);
        this.add(l6);
        this.add(l7);

        this.add(t1);
        this.add(t2);
        this.add(t3);
        this.add(t4);
        this.add(t5);
        this.add(t6);

        this.add(b1);


        this.user=user;
        this.setSize(700,500);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("LOGIN");
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == b1) {
            boolean flag = false;
            try {
                if (t5.getText().length() > 8)
                {
                    throw new IllegalArgumentException("password should be atmost of 8 letters");
                }
                else if (!t5.getText().matches(".*[0-9].*"))
                {
                        throw new IllegalArgumentException("password should contain atleast one digit");
                }
                else if(!t5.getText().matches(".*[!@#$%^&*()].*"))
                {
                    throw new IllegalArgumentException("password should contain atleast one special character");
                }


                for (int i = 0; i < user.size(); i++) {
                    if (t4.getText().equals(user.get(i).username)) {
                        flag = true;
                        JOptionPane.showMessageDialog(this, "user already exists");
                    }
                }
                if (flag == false) {
                    String s1 = t1.getText();
                    String s2 = t2.getText();
                    String s3 = t3.getText();
                    String s4 = t4.getText();
                    String s5 = t5.getText();
                    String s6 = t6.getText();

                    User a = new User(s4, s5);
                    user.add(a);
                    try {
                        Statement statement = conn.createStatement();
                        String query2 = String.format("Insert into info values('%s','%s')", s4, s5);
                        int rows = statement.executeUpdate(query2);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }

                    JOptionPane.showMessageDialog(this, "user registered successfully");
                    this.dispose();
                    new LoginForm(user, conn);
                }
            }catch(Exception exx)
            {
                JOptionPane.showMessageDialog(this,exx.getMessage());
            }

        }
    }

}
