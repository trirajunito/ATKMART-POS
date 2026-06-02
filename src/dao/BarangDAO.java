package dao;

import config.Database;
import model.Barang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BarangDAO {

    Connection conn = Database.getConnection();

    public void insertBarang(Barang barang) {

        try {

            String sql = "INSERT INTO barang(nama_barang,harga,stok) VALUES(?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, barang.getNamaBarang());
            ps.setInt(2, barang.getHarga());
            ps.setInt(3, barang.getStok());

            ps.executeUpdate();

            System.out.println("Data berhasil disimpan");

        } catch (Exception e) {
            System.out.println("Gagal simpan : " + e.getMessage());
        }
    }

    // GET ALL
    public List<Barang> getAllBarang() {

        List<Barang> listBarang = new ArrayList<>();

        try {

            String sql = "SELECT * FROM barang";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Barang barang = new Barang();

                barang.setId(rs.getInt("id"));
                barang.setNamaBarang(rs.getString("nama_barang"));
                barang.setHarga(rs.getInt("harga"));
                barang.setStok(rs.getInt("stok"));

                listBarang.add(barang);
            }

        } catch (Exception e) {
            System.out.println("Gagal ambil data : " + e.getMessage());
        }

        return listBarang;
    }

    // GET BARANG BY NAMA
    public Barang getBarangByNama(String nama) {

        try {

            String sql = "SELECT * FROM barang WHERE nama_barang=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, nama);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Barang barang = new Barang();

                barang.setId(rs.getInt("id"));
                barang.setNamaBarang(rs.getString("nama_barang"));
                barang.setHarga(rs.getInt("harga"));
                barang.setStok(rs.getInt("stok"));

                return barang;
            }

        } catch (Exception e) {

            System.out.println("Error : " + e.getMessage());
        }

        return null;
    }

    // UPDATE
    public void updateBarang(Barang barang) {

        try {

            String sql = "UPDATE barang SET nama_barang=?, harga=?, stok=? WHERE id=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, barang.getNamaBarang());
            ps.setInt(2, barang.getHarga());
            ps.setInt(3, barang.getStok());
            ps.setInt(4, barang.getId());

            ps.executeUpdate();

            System.out.println("Data berhasil diupdate");

        } catch (Exception e) {
            System.out.println("Gagal update : " + e.getMessage());
        }
    }

    // DELETE
    public void deleteBarang(int id) {

        try {

            String sql = "DELETE FROM barang WHERE id=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

            System.out.println("Data berhasil dihapus");

        } catch (Exception e) {
            System.out.println("Gagal hapus : " + e.getMessage());
        }
    }
}