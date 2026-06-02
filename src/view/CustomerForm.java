package view;

import dao.CustomerDAO;
import model.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CustomerForm extends JFrame {

    JTextField txtNama, txtHp, txtAlamat;

    JTable table;

    DefaultTableModel model;

    CustomerDAO dao = new CustomerDAO();

    int selectedId = 0;

    public CustomerForm(){

        setTitle("Data Customer");
        setSize(900,600);
        setLocationRelativeTo(null);
        setLayout(null);

        getContentPane().setBackground(new Color(15,23,42));

        JLabel title = new JLabel("DATA CUSTOMER");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("SansSerif",Font.BOLD,28));
        title.setBounds(30,20,300,40);

        add(title);

        JLabel lblNama = new JLabel("Nama Customer");
        lblNama.setForeground(Color.WHITE);
        lblNama.setBounds(30,100,120,30);

        txtNama = new JTextField();
        txtNama.setBounds(160,100,220,30);

        JLabel lblHp = new JLabel("No HP");
        lblHp.setForeground(Color.WHITE);
        lblHp.setBounds(30,150,120,30);

        txtHp = new JTextField();
        txtHp.setBounds(160,150,220,30);

        JLabel lblAlamat = new JLabel("Alamat");
        lblAlamat.setForeground(Color.WHITE);
        lblAlamat.setBounds(30,200,120,30);

        txtAlamat = new JTextField();
        txtAlamat.setBounds(160,200,220,30);

        JButton btnSimpan = new JButton("Simpan");
        btnSimpan.setBounds(30,260,100,35);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setBounds(145,260,100,35);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setBounds(260,260,100,35);

        add(lblNama);
        add(txtNama);

        add(lblHp);
        add(txtHp);

        add(lblAlamat);
        add(txtAlamat);

        add(btnSimpan);
        add(btnUpdate);
        add(btnDelete);

        // TABLE
        model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Nama");
        model.addColumn("No HP");
        model.addColumn("Alamat");

        table = new JTable(model);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(420,80,430,400);

        add(sp);

        tampilData();

        // CLICK TABLE
        table.getSelectionModel().addListSelectionListener(e -> {

            int row = table.getSelectedRow();

            if(row != -1){

                selectedId =
                        Integer.parseInt(model.getValueAt(row,0).toString());

                txtNama.setText(model.getValueAt(row,1).toString());
                txtHp.setText(model.getValueAt(row,2).toString());
                txtAlamat.setText(model.getValueAt(row,3).toString());
            }
        });

        // SIMPAN
        btnSimpan.addActionListener(e -> {

            Customer customer = new Customer();

            customer.setNamaCustomer(txtNama.getText());
            customer.setNoHp(txtHp.getText());
            customer.setAlamat(txtAlamat.getText());

            dao.insertCustomer(customer);

            refreshTable();
        });

        // UPDATE
        btnUpdate.addActionListener(e -> {

            Customer customer = new Customer();

            customer.setId(selectedId);
            customer.setNamaCustomer(txtNama.getText());
            customer.setNoHp(txtHp.getText());
            customer.setAlamat(txtAlamat.getText());

            dao.updateCustomer(customer);

            refreshTable();
        });

        // DELETE
        btnDelete.addActionListener(e -> {

            dao.deleteCustomer(selectedId);

            refreshTable();
        });

        setVisible(true);
    }

    private void tampilData(){

        List<Customer> list =
                dao.getAllCustomer();

        for(Customer c : list){

            model.addRow(new Object[]{

                    c.getId(),
                    c.getNamaCustomer(),
                    c.getNoHp(),
                    c.getAlamat()
            });
        }
    }

    private void refreshTable(){

        model.setRowCount(0);

        tampilData();

        txtNama.setText("");
        txtHp.setText("");
        txtAlamat.setText("");
    }
}