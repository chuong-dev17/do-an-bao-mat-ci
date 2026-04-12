package securitytest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Intentional vulnerable code for SAST demo only.
 * Do not use in production.
 */
public class SQLInjectionDemo {

    public ResultSet findCarByModel(Connection conn, String model) throws Exception {
        // Vulnerable pattern: direct string concatenation into SQL.
        String sql = "SELECT carID, serialNumber, model, colour, year FROM Cars WHERE model = '" + model + "'";
        Statement st = conn.createStatement();
        return st.executeQuery(sql);
    }

    public int deleteCarById(Connection conn, String carID) throws Exception {
        // Another vulnerable pattern: unsafe concatenation.
        String sql = "DELETE FROM Cars WHERE carID = '" + carID + "'";
        Statement st = conn.createStatement();
        return st.executeUpdate(sql);
    }
}
