package dao;

import config.Database;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    Connection conn = Database.getConnection();

    // INSERT
    public void insertCustomer(Customer customer){

        try {

            String sql =
                    "INSERT INTO customer(nama_customer,no_hp,alamat) VALUES(?,?,?)";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(1, customer.getNamaCustomer());
            ps.setString(2, customer.getNoHp());
            ps.setString(3, customer.getAlamat());

            ps.executeUpdate();

            System.out.println("Customer berhasil disimpan");

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    // GET ALL
    public List<Customer> getAllCustomer(){

        List<Customer> list = new ArrayList<>();

        try {

            String sql = "SELECT * FROM customer";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                Customer customer = new Customer();

                customer.setId(rs.getInt("id"));
                customer.setNamaCustomer(rs.getString("nama_customer"));
                customer.setNoHp(rs.getString("no_hp"));
                customer.setAlamat(rs.getString("alamat"));

                list.add(customer);
            }

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

        return list;
    }

    // UPDATE
    public void updateCustomer(Customer customer){

        try {

            String sql =
                    "UPDATE customer SET nama_customer=?, no_hp=?, alamat=? WHERE id=?";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(1, customer.getNamaCustomer());
            ps.setString(2, customer.getNoHp());
            ps.setString(3, customer.getAlamat());
            ps.setInt(4, customer.getId());

            ps.executeUpdate();

            System.out.println("Customer berhasil diupdate");

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    // DELETE
    public void deleteCustomer(int id){

        try {

            String sql =
                    "DELETE FROM customer WHERE id=?";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

            System.out.println("Customer berhasil dihapus");

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
}