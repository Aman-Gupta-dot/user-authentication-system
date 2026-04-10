package User_authentication;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;

public class LoginForm extends JFrame implements ActionListener {
    JLabel l1,l2;
    JTextField t1,t2;
    JButton b1,b2;
    ArrayList<User> use;
    Connection conn;

    LoginForm(ArrayList<User> u,Connection conn){
        this.conn=conn;
        this.use=u;
        l1=new JLabel("User:");
        l1.setBounds(100,100,100,40);

        t1=new JTextField();
        t1.setBounds(200,100,200,40);


        l2=new JLabel("Password:");
        l2.setBounds(100,150,100,40);

        t2=new JTextField();
        t2.setBounds(200,150,200,40);

        b1=new JButton("login");
        b1.setFocusable(false);
        b1.setBounds(100,200,100,40);
        b1.addActionListener(this);

        b2=new JButton("Register");
        b2.setFocusable(false);
        b2.setBounds(300,200,100,40);
        b2.addActionListener(this);


        this.add(l1);
        this.add(l2);
        this.add(b1);
        this.add(b2);
        this.add(t1);
        this.add(t2);

        this.setSize(500,400);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("LOGIN");
        this.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            boolean flag = false;
            for (int i = 0; i < use.size(); i++) {
                if (use.get(i).username.equals(t1.getText()) && use.get(i).password.equals(t2.getText())) {
                    JOptionPane.showMessageDialog(this, "login done");
                    flag = true;
                    break;
                }
            }
            if (flag == false) {
                JOptionPane.showMessageDialog(this, "invalid user id or password");
            }
        }
        if(e.getSource()==b2)
        {
            new RegisterForm(use,conn);

        }
    }


    }

