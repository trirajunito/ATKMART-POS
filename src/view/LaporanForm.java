package view;

import config.Database;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LaporanForm extends JFrame {

    JTable table;

    DefaultTableModel model;

    public LaporanForm(){

        setTitle("Laporan Penjualan");
        setSize(800,500);
        setLocationRelativeTo(null);
        setLayout(null);

        getContentPane().setBackground(new Color(15,23,42));

        // TITLE
        JLabel title = new JLabel("Laporan Penjualan");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("SansSerif",Font.BOLD,28));
        title.setBounds(30,20,400,40);

        add(title);

        // TABLE
        model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Tanggal");
        model.addColumn("Total");

        table = new JTable(model);

        table.setBackground(new Color(30,41,59));
        table.setForeground(Color.WHITE);

        table.setRowHeight(25);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(30,90,720,320);

        add(sp);

        tampilData();

        setVisible(true);
    }

    private void tampilData(){

        try {

            Connection conn =
                    Database.getConnection();

            String sql =
                    "SELECT * FROM laporan ORDER BY id DESC";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()){

                model.addRow(new Object[]{

                        rs.getInt("id"),
                        rs.getTimestamp("tanggal"),
                        rs.getInt("total")
                });
            }

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
}