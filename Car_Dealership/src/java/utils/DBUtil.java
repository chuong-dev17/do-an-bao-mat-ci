/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author Admin_Coder
 */
public class DBUtil {
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException, NamingException {
        Connection conn = null;
        String username = "sa";
        String password = "12345";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://host.docker.internal:1433;databaseName=Car_Dealership;encrypt=true;trustServerCertificate=true";
        conn = DriverManager.getConnection(url, username, password);
        return conn;
    }
    
    public static void closeConnection(ResultSet rs, PreparedStatement stm, Connection conn) throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
    
   public static void main(String[] args) {
    try {
        Connection conn = getConnection();
        if (conn != null) {
            System.out.println("Kết nối database thành công!");
            conn.close(); // Đóng kết nối sau khi kiểm tra
        } else {
            System.out.println("Kết nối database thất bại!");
        }
    } catch (ClassNotFoundException | SQLException | NamingException e) {
        e.printStackTrace(); // In lỗi ra console để kiểm tra
    }
}

}
