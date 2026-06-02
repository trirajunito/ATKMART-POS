package view;

import dao.BarangDAO;
import dao.TransaksiDAO;
import model.Barang;
import model.DetailTransaksi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class TransaksiForm extends JFrame {

    JComboBox<String> cbBarang = new JComboBox<>();
    JTextField txtQty = new JTextField();

    JButton btnTambah = new JButton("Tambah");
    JButton btnSimpan = new JButton("Simpan Transaksi");

    JTable table;

    DefaultTableModel model;

    JLabel lblTotal = new JLabel("Total : 0");

    BarangDAO barangDAO = new BarangDAO();
    TransaksiDAO transaksiDAO = new TransaksiDAO();

    List<DetailTransaksi> list = new ArrayList<>();

    int total = 0;

    public TransaksiForm(){

        setTitle("Transaksi Penjualan");
        setSize(700,500);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblBarang = new JLabel("Barang");
        lblBarang.setBounds(30,20,100,25);

        cbBarang.setBounds(120,20,200,25);

        JLabel lblQty = new JLabel("Qty");
        lblQty.setBounds(30,60,100,25);

        txtQty.setBounds(120,60,200,25);

        btnTambah.setBounds(350,40,120,30);

        add(lblBarang);
        add(cbBarang);

        add(lblQty);
        add(txtQty);

        add(btnTambah);

        // table
        model = new DefaultTableModel();

        model.addColumn("Barang");
        model.addColumn("Harga");
        model.addColumn("Qty");
        model.addColumn("Subtotal");

        table = new JTable(model);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(30,120,620,250);

        add(sp);

        lblTotal.setBounds(30,390,300,30);

        add(lblTotal);

        btnSimpan.setBounds(450,390,200,35);

        add(btnSimpan);

        loadBarang();

        // tambah item
        btnTambah.addActionListener(e -> tambahItem());

        // simpan transaksi
        btnSimpan.addActionListener(e -> simpan());

        setVisible(true);
    }

    private void loadBarang(){

        for(Barang b : barangDAO.getAllBarang()){

            cbBarang.addItem(b.getNamaBarang());
        }
    }

    private void tambahItem(){

        String namaBarang =
                cbBarang.getSelectedItem().toString();

        int qty =
                Integer.parseInt(txtQty.getText());

        Barang barang =
                barangDAO.getBarangByNama(namaBarang);

        int subtotal = barang.getHarga() * qty;

        total += subtotal;

        lblTotal.setText("Total : " + total);

        model.addRow(new Object[]{
                barang.getNamaBarang(),
                barang.getHarga(),
                qty,
                subtotal
        });

        DetailTransaksi detail =
                new DetailTransaksi();

        detail.setBarangId(barang.getId());
        detail.setNamaBarang(barang.getNamaBarang());
        detail.setHarga(barang.getHarga());
        detail.setQty(qty);
        detail.setSubtotal(subtotal);

        list.add(detail);

        txtQty.setText("");
    }

    private void simpan(){

        transaksiDAO.simpanTransaksi(list,total);

        JOptionPane.showMessageDialog(this,
                "Transaksi berhasil");

        dispose();

        new Dashboard();
    }
}