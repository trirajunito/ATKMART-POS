package view;

import dao.LaporanDAO;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class LaporanInventoryForm extends JFrame {

    JTable table;
    DefaultTableModel model;

    JLabel totalBarang;
    JLabel stokMenipis;

    public LaporanInventoryForm() {

        setTitle("Laporan Inventory");
        setSize(800,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());
        setBackground(new Color(11,19,43));

        // ===== HEADER =====
        JPanel header = new JPanel(new GridLayout(1,2,20,20));
        header.setBackground(new Color(11,19,43));
        header.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        totalBarang = new JLabel();
        stokMenipis = new JLabel();

        totalBarang.setFont(new Font("Segoe UI", Font.BOLD, 20));
        stokMenipis.setFont(new Font("Segoe UI", Font.BOLD, 20));

        totalBarang.setForeground(Color.WHITE);
        stokMenipis.setForeground(Color.WHITE);

        header.add(totalBarang);
        header.add(stokMenipis);

        add(header, BorderLayout.NORTH);

        // ===== TABLE =====
        model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Nama Barang");
        model.addColumn("Stok");
        model.addColumn("Status");

        table = new JTable(model);

        JScrollPane scroll = new JScrollPane(table);

        add(scroll, BorderLayout.CENTER);

        loadData();

        setVisible(true);
    }

    private void loadData() {

        LaporanDAO dao = new LaporanDAO();

        model.setRowCount(0);

        List<Object[]> list = dao.getInventory();

        int total = 0;
        int menipis = 0;

        for(Object[] row : list) {

            int stok = Integer.parseInt(row[2].toString());

            String status;

            if(stok <= 5) {

                status = "Stok Menipis";
                menipis++;

            } else {

                status = "Aman";
            }

            model.addRow(new Object[]{

                    row[0],
                    row[1],
                    stok,
                    status
            });

            total += stok;
        }

        totalBarang.setText("Total Stok Barang : " + total);
        stokMenipis.setText("Barang Menipis : " + menipis);
    }
}