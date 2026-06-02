package dao;

import config.Database;
import model.DetailTransaksi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;

public class TransaksiDAO {

    Connection conn = Database.getConnection();

    public void simpanTransaksi(List<DetailTransaksi> list, int total) {

        try {

            // simpan transaksi
            String sqlTransaksi =
                    "INSERT INTO transaksi(total) VALUES(?)";

            PreparedStatement psTransaksi =
                    conn.prepareStatement(sqlTransaksi,
                            Statement.RETURN_GENERATED_KEYS);

            psTransaksi.setInt(1, total);

            psTransaksi.executeUpdate();

            ResultSet rs = psTransaksi.getGeneratedKeys();

            int transaksiId = 0;

            if(rs.next()){

                transaksiId = rs.getInt(1);
            }

            // simpan detail
            for(DetailTransaksi d : list){

                String sqlDetail =
                        "INSERT INTO detail_transaksi(transaksi_id,barang_id,qty,subtotal) VALUES(?,?,?,?)";

                PreparedStatement psDetail =
                        conn.prepareStatement(sqlDetail);

                psDetail.setInt(1, transaksiId);
                psDetail.setInt(2, d.getBarangId());
                psDetail.setInt(3, d.getQty());
                psDetail.setInt(4, d.getSubtotal());

                psDetail.executeUpdate();

                // update stok
                String sqlUpdate =
                        "UPDATE barang SET stok = stok - ? WHERE id=?";

                PreparedStatement psUpdate =
                        conn.prepareStatement(sqlUpdate);

                psUpdate.setInt(1, d.getQty());
                psUpdate.setInt(2, d.getBarangId());

                psUpdate.executeUpdate();
            }

            System.out.println("Transaksi berhasil");

        } catch (Exception e) {

            System.out.println("Gagal transaksi : " + e.getMessage());
        }
    }
}