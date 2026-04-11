package securitytest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Intentional SQL Injection sample for CI/SAST demonstration.
 * Do not use this pattern in production code.
 */
public class SQLInjectionDemo {

    public ResultSet searchByUsername(Connection conn, String username) throws Exception {
        // Intentional vulnerability: concatenating user input directly into SQL.
        String sql = "SELECT * FROM Users WHERE username = '" + username + "'";
        Statement st = conn.createStatement();
        return st.executeQuery(sql);
    }

    public int deleteById(Connection conn, String id) throws Exception {
        // Another SQLi pattern for scanners to detect.
        String sql = "DELETE FROM Users WHERE id = " + id;
        Statement st = conn.createStatement();
        return st.executeUpdate(sql);
    }
}
