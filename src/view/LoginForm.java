package view;

import dao.UserDAO;

import javax.swing.*;
import java.awt.*;

public class LoginForm extends JFrame {

    JTextField txtUsername = new JTextField();
    JPasswordField txtPassword = new JPasswordField();

    JButton btnLogin = new JButton("LOGIN");

    UserDAO userDAO = new UserDAO();

    public LoginForm() {

        setTitle("Login Aplikasi");
        setSize(400,300);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(15,23,42));

        JLabel title = new JLabel("ATKMART POS");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial",Font.BOLD,24));
        title.setBounds(130,30,200,30);

        JLabel lblUser = new JLabel("Username");
        lblUser.setForeground(Color.WHITE);
        lblUser.setBounds(50,90,100,25);

        txtUsername.setBounds(150,90,180,30);

        JLabel lblPass = new JLabel("Password");
        lblPass.setForeground(Color.WHITE);
        lblPass.setBounds(50,140,100,25);

        txtPassword.setBounds(150,140,180,30);

        btnLogin.setBounds(120,200,150,35);
        btnLogin.setBackground(new Color(59,130,246));
        btnLogin.setForeground(Color.WHITE);

        add(title);

        add(lblUser);
        add(txtUsername);

        add(lblPass);
        add(txtPassword);

        add(btnLogin);

        btnLogin.addActionListener(e -> login());

        setVisible(true);
    }

    private void login() {

        String username = txtUsername.getText();
        String password = String.valueOf(txtPassword.getPassword());

        boolean isLogin = userDAO.login(username,password);

        if(isLogin){

            JOptionPane.showMessageDialog(this,"Login berhasil");

            new Dashboard();

            dispose();

        }else{

            JOptionPane.showMessageDialog(this,"Username / Password salah");
        }
    }
}