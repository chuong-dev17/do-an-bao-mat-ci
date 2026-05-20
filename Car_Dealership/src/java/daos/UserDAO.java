package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utils.DBUtil;
import dtos.CustomerDTO;
import dtos.MechanicDTO;
import dtos.SalesPersonDTO;
import java.sql.SQLException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.naming.NamingException;

public class UserDAO {

    private boolean matchesHash(String rawPassword, String expectedHash) throws NoSuchAlgorithmException {
        if (rawPassword == null || expectedHash == null || expectedHash.isBlank()) {
            return false;
        }

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(rawPassword.getBytes(StandardCharsets.UTF_8));
        StringBuilder hex = new StringBuilder(hashBytes.length * 2);
        for (byte hashByte : hashBytes) {
            hex.append(String.format("%02x", hashByte & 0xff));
        }
        return expectedHash.equalsIgnoreCase(hex.toString());
    }

    private String hashPassword(String rawPassword) throws NoSuchAlgorithmException {
        if (rawPassword == null) {
            return null;
        }

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(rawPassword.getBytes(StandardCharsets.UTF_8));
        StringBuilder hex = new StringBuilder(hashBytes.length * 2);
        for (byte hashByte : hashBytes) {
            hex.append(String.format("%02x", hashByte & 0xff));
        }
        return hex.toString();
    }

    public CustomerDTO loginCustomer(String custName, String phone) throws Exception {
        CustomerDTO customer = null;
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT custID, sex, cusAddress FROM Customer WHERE custName=? AND phone=?";
                PreparedStatement st = conn.prepareStatement(sql);
                st.setString(1, custName);
                st.setString(2, phone);
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    customer = new CustomerDTO(rs.getString("custID"), custName, phone, rs.getString("sex"), rs.getString("cusAddress"));
                }
            }
        } catch (ClassNotFoundException | SQLException | NamingException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return customer;
    }

    public MechanicDTO loginMechanic(String mechanicName, String password) throws Exception {
        MechanicDTO mechanic = null;
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String expectedHash = System.getenv("MECHANIC_LOGIN_HASH");
                if (expectedHash == null || expectedHash.isBlank()) {
                    return null;
                }

                String providedHash = hashPassword(password);
                String sql = "SELECT mechanicID FROM Mechanic WHERE LOWER(mechanicName)=LOWER(?) AND ? = ?";
                PreparedStatement st = conn.prepareStatement(sql);
                st.setString(1, mechanicName);
                st.setString(2, providedHash);
                st.setString(3, expectedHash);
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    mechanic = new MechanicDTO(rs.getString("mechanicID"), mechanicName);
                }
            }
        } catch (ClassNotFoundException | SQLException | NamingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return mechanic;
    }
    public SalesPersonDTO loginSalesPerson(String name, String password) {

        Connection cnn = null;

        try {

            cnn = DBUtil.getConnection();

            String sql = "SELECT [salesID]\n"
                    + "      ,[salesName]\n"
                    + "      ,[birthday]\n"
                    + "      ,[sex]\n"
                    + "      ,[salesAddress]\n"
                    + "  FROM [dbo].[SalesPerson]\n"
                    + " WHERE [salesName] = ?";

            PreparedStatement st = cnn.prepareStatement(sql);
            st.setString(1, name);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                SalesPersonDTO s = new SalesPersonDTO();
                s.setSalesID(rs.getString("salesID"));
                s.setSalesName(rs.getString("salesName"));
                s.setBirthday(rs.getString("birthday"));
                s.setSex(rs.getString("sex"));
                s.setSalesAddress(rs.getString("salesAddress"));

                return s;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

//    public SalesPersonDTO loginSalesPerson(String salesName) throws Exception {
//        SalesPersonDTO salesPerson = new SalesPersonDTO();
//        Connection conn = null;
//        try {
//            conn = DBUtil.getConnection();
//            if (conn != null) {
//                String sql = "SELECT salesID, birthday, sex, salesAddress FROM SalesPerson WHERE salesName=?";
//                PreparedStatement st = conn.prepareStatement(sql);
//                st.setString(1, salesName);
//                ResultSet rs = st.executeQuery();
//                if (rs.next()) {
//                    salesPerson = new SalesPersonDTO(rs.getString("salesID"), salesName, rs.getString("birthday"), rs.getString("sex"), rs.getString("salesAddress"));
//                }
//            }
//        } finally {
//            try {
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return salesPerson;
//    }

    public void updateProfile(CustomerDTO c) {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "  UPDATE Customer\n"
                        + "  SET [custName] = ?,[phone] = ?,[sex] = ?,[cusAddress] = ?\n"
                        + "  WHERE custID = ?";
                PreparedStatement st = conn.prepareStatement(sql);
                st.setString(1, c.getCustName());
                st.setString(2, c.getPhone());
                st.setString(3, c.getSex());
                st.setString(4, c.getCusAddress());
                st.setString(5, c.getCustID());
                st.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
