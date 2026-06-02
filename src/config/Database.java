package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

    private static Connection connection;

    public static Connection getConnection() {

        try {

            if (connection == null || connection.isClosed()) {

                String url = "jdbc:mysql://localhost:3306/db_penjualan";
                String user = "root";
                String password = "";

                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

                connection = DriverManager.getConnection(url, user, password);

                System.out.println("Koneksi berhasil");
            }

        } catch (Exception e) {
            System.out.println("Koneksi gagal : " + e.getMessage());
        }

        return connection;
    }
}