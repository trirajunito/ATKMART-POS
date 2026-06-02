package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

public class LaporanDAO {

    Connection conn;

    public LaporanDAO() {

        try {

            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/db_penjualan",
                    "root",
                    ""
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public List<Object[]> getInventory() {

        List<Object[]> list = new ArrayList<>();

        try {

            String sql = "SELECT * FROM barang";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                Object[] data = {

                        rs.getInt("id"),
                        rs.getString("nama_barang"),
                        rs.getInt("stok")
                };

                list.add(data);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return list;
    }
}