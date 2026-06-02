import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

class Database {
    static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/db_penjualan",
                "root",
                ""
            );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Koneksi gagal!");
            return null;
        }
    }
}

class LoginForm extends JFrame {
    JTextField user = new JTextField();
    JPasswordField pass = new JPasswordField();
    JButton login = new JButton("Login");

    LoginForm() {
        setTitle("Login");
        setSize(300,200);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel l1 = new JLabel("Username");
        JLabel l2 = new JLabel("Password");

        l1.setBounds(50,10,200,20);
        user.setBounds(50,30,200,25);

        l2.setBounds(50,60,200,20);
        pass.setBounds(50,80,200,25);

        login.setBounds(50,120,200,30);

        add(l1); add(user);
        add(l2); add(pass);
        add(login);

        login.addActionListener(e -> cekLogin());
    }

    void cekLogin() {
        try {
            Connection c = Database.getConnection();
            PreparedStatement ps = c.prepareStatement(
                "SELECT * FROM user WHERE username=? AND password=?"
            );

            ps.setString(1, user.getText());
            ps.setString(2, pass.getText());

            ResultSet r = ps.executeQuery();

            if(r.next()) {
                JOptionPane.showMessageDialog(null, "Login Berhasil!");
                new Dashboard().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Login Gagal!");
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

class Dashboard extends JFrame {

    Dashboard() {
        setTitle("Dashboard");
        setSize(400,300);
        setLayout(null);
        setLocationRelativeTo(null);

        JButton btnBarang = new JButton("Input Barang");
        btnBarang.setBounds(100,100,200,30);

        add(btnBarang);

        btnBarang.addActionListener(e -> {
            new BarangForm().setVisible(true);
        });
    }
}

class BarangForm extends JFrame {
    JTextField nama = new JTextField();
    JTextField harga = new JTextField();
    JTextField stok = new JTextField();
    JButton simpan = new JButton("Simpan");

    BarangForm() {
        setTitle("Input Barang");
        setSize(300,250);
        setLayout(null);
        setLocationRelativeTo(null);

        nama.setBounds(50,30,200,25);
        harga.setBounds(50,60,200,25);
        stok.setBounds(50,90,200,25);
        simpan.setBounds(50,130,200,30);

        add(new JLabel("Nama")).setBounds(50,10,100,20);
        add(nama);

        add(new JLabel("Harga")).setBounds(50,40,100,20);
        add(harga);

        add(new JLabel("Stok")).setBounds(50,70,100,20);
        add(stok);

        add(simpan);

        simpan.addActionListener(e -> simpanData());
    }

    void simpanData() {
        try {
            Connection c = Database.getConnection();
            PreparedStatement ps = c.prepareStatement(
                "INSERT INTO barang(nama,harga,stok) VALUES (?,?,?)"
            );

            ps.setString(1, nama.getText());
            ps.setInt(2, Integer.parseInt(harga.getText()));
            ps.setInt(3, Integer.parseInt(stok.getText()));

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

public class MainApp {
    public static void main(String[] args) {
        new LoginForm().setVisible(true);
    }
}