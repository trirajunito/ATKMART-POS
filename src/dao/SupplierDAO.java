package dao;

import config.Database;
import model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAO {

    Connection conn = Database.getConnection();

    // INSERT
    public void insertSupplier(Supplier supplier){

        try {

            String sql =
                    "INSERT INTO supplier(nama_supplier,no_hp,alamat) VALUES(?,?,?)";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(1, supplier.getNamaSupplier());
            ps.setString(2, supplier.getNoHp());
            ps.setString(3, supplier.getAlamat());

            ps.executeUpdate();

            System.out.println("Supplier berhasil disimpan");

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    // GET ALL
    public List<Supplier> getAllSupplier(){

        List<Supplier> list = new ArrayList<>();

        try {

            String sql = "SELECT * FROM supplier";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                Supplier supplier = new Supplier();

                supplier.setId(rs.getInt("id"));
                supplier.setNamaSupplier(rs.getString("nama_supplier"));
                supplier.setNoHp(rs.getString("no_hp"));
                supplier.setAlamat(rs.getString("alamat"));

                list.add(supplier);
            }

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

        return list;
    }

    // UPDATE
    public void updateSupplier(Supplier supplier){

        try {

            String sql =
                    "UPDATE supplier SET nama_supplier=?, no_hp=?, alamat=? WHERE id=?";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(1, supplier.getNamaSupplier());
            ps.setString(2, supplier.getNoHp());
            ps.setString(3, supplier.getAlamat());
            ps.setInt(4, supplier.getId());

            ps.executeUpdate();

            System.out.println("Supplier berhasil diupdate");

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    // DELETE
    public void deleteSupplier(int id){

        try {

            String sql =
                    "DELETE FROM supplier WHERE id=?";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

            System.out.println("Supplier berhasil dihapus");

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
}