package securitytest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.http.HttpServletRequest;

/**
 * Intentional vulnerable code for Security CI testing only.
 * Remove this file after validating alerts.
 */
public class SqlInjectionVulnDemo {

    public ResultSet findUserByName(HttpServletRequest request, Connection conn) throws Exception {
        String username = request.getParameter("username");

        // Vulnerable pattern: SQL built by string concatenation from user input.
        String sql = "SELECT * FROM users WHERE username = '" + username + "'";

        Statement st = conn.createStatement();
        return st.executeQuery(sql);
    }
}
