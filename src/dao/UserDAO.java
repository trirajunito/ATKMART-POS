package dao;

import config.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    Connection conn = Database.getConnection();

    public boolean login(String username, String password) {

        try {

            String sql = "SELECT * FROM user WHERE username=? AND password=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {

            System.out.println("Login gagal : " + e.getMessage());
        }

        return false;
    }
}