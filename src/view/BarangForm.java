package view;

import dao.BarangDAO;
import model.Barang;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BarangForm extends JFrame {

    JTextField txtNama = new JTextField();
    JTextField txtHarga = new JTextField();
    JTextField txtStok = new JTextField();

    JButton btnSimpan = new JButton("Simpan");
    JButton btnUpdate = new JButton("Update");
    JButton btnHapus = new JButton("Hapus");

    JTable table;

    DefaultTableModel model;

    BarangDAO dao = new BarangDAO();

    int selectedId = 0;

    public BarangForm() {

        setTitle("Data ATK");
        setSize(700, 500);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel lblNama = new JLabel("Nama Barang");
        JLabel lblHarga = new JLabel("Harga");
        JLabel lblStok = new JLabel("Stok");

        lblNama.setBounds(30, 20, 100, 25);
        txtNama.setBounds(130, 20, 200, 25);

        lblHarga.setBounds(30, 60, 100, 25);
        txtHarga.setBounds(130, 60, 200, 25);

        lblStok.setBounds(30, 100, 100, 25);
        txtStok.setBounds(130, 100, 200, 25);

        btnSimpan.setBounds(30, 150, 100, 30);
        btnUpdate.setBounds(140, 150, 100, 30);
        btnHapus.setBounds(250, 150, 100, 30);

        add(lblNama);
        add(txtNama);

        add(lblHarga);
        add(txtHarga);

        add(lblStok);
        add(txtStok);

        add(btnSimpan);
        add(btnUpdate);
        add(btnHapus);

        // TABLE
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nama Barang");
        model.addColumn("Harga");
        model.addColumn("Stok");

        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 220, 620, 200);

        add(scrollPane);

        tampilData();

        // SIMPAN
        btnSimpan.addActionListener(e -> {

            Barang barang = new Barang();

            barang.setNamaBarang(txtNama.getText());
            barang.setHarga(Integer.parseInt(txtHarga.getText()));
            barang.setStok(Integer.parseInt(txtStok.getText()));

            dao.insertBarang(barang);

            resetForm();
            tampilData();
        });

        // KLIK TABLE
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int row = table.getSelectedRow();

                selectedId = Integer.parseInt(model.getValueAt(row, 0).toString());

                txtNama.setText(model.getValueAt(row, 1).toString());
                txtHarga.setText(model.getValueAt(row, 2).toString());
                txtStok.setText(model.getValueAt(row, 3).toString());
            }
        });

        // UPDATE
        btnUpdate.addActionListener(e -> {

            Barang barang = new Barang();

            barang.setId(selectedId);
            barang.setNamaBarang(txtNama.getText());
            barang.setHarga(Integer.parseInt(txtHarga.getText()));
            barang.setStok(Integer.parseInt(txtStok.getText()));

            dao.updateBarang(barang);

            resetForm();
            tampilData();
        });

        // DELETE
        btnHapus.addActionListener(e -> {

            dao.deleteBarang(selectedId);

            resetForm();
            tampilData();
        });

        setVisible(true);
    }

    private void tampilData() {

        model.setRowCount(0);

        for (Barang barang : dao.getAllBarang()) {

            Object[] row = {
                    barang.getId(),
                    barang.getNamaBarang(),
                    barang.getHarga(),
                    barang.getStok()
            };

            model.addRow(row);
        }
    }

    private void resetForm() {

        txtNama.setText("");
        txtHarga.setText("");
        txtStok.setText("");

        selectedId = 0;
    }
}