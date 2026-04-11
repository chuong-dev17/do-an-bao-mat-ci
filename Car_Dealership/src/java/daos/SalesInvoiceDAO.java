package daos;

import dtos.SalesInvoiceDTO;
import dtos.SalesPersonDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtil;

public class SalesInvoiceDAO {

    public List<SalesInvoiceDTO> getList(String custID) {
        Connection conn = null;
        List<SalesInvoiceDTO> list = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT invoiceID, invoiceDate, salesID, carID, custID FROM SalesInvoice WHERE custID = ?";
                PreparedStatement st = conn.prepareStatement(sql);
                st.setString(1, custID);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    list.add(new SalesInvoiceDTO(
                            rs.getString("invoiceID"),
                            rs.getString("invoiceDate"),
                            rs.getString("salesID"),
                            rs.getString("carID"),
                            rs.getString("custID")
                    ));
                }
            }
        } catch (Exception e) {
            System.err.println("Error fetching SalesInvoice list: " + e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public void create(SalesInvoiceDTO s) {
        Connection conn = null;
        String sql = "  INSERT INTO SalesInvoice ([invoiceID],[invoiceDate],[salesID],[carID],[custID])\n"
                + "  VALUES(?,?,?,?,?)";
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                PreparedStatement st = conn.prepareStatement(sql);
                st.setString(1, s.getInvoiceID());
                st.setString(2, s.getInvoiceDate());
                st.setString(3, s.getSalesID());
                st.setString(4, s.getCarID());
                st.setString(5, s.getCustID());
                st.executeUpdate();
            }
        } catch (Exception e) {
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public SalesInvoiceDTO getInvoiceByID(String invoiceID) {
        Connection conn = null;
        String sql = "SELECT [invoiceID]\n"
                + "      ,[invoiceDate]\n"
                + "      ,[salesID]\n"
                + "      ,[carID]\n"
                + "      ,[custID]\n"
                + "  FROM [Car_Dealership].[dbo].[SalesInvoice]\n"
                + "  WHERE [invoiceID] = ?";
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                PreparedStatement st = conn.prepareStatement(sql);
                st.setString(1, invoiceID);
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    return new SalesInvoiceDTO(
                            rs.getString("invoiceID"),
                            rs.getString("invoiceDate"),
                            rs.getString("salesID"),
                            rs.getString("carID"),
                            rs.getString("custID"));
                }
            }
        } catch (Exception e) {
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    public static void main(String[] args) {
        SalesInvoiceDAO dao = new SalesInvoiceDAO();
        SalesInvoiceDTO in = dao.getInvoiceByID("444");
        
        System.out.println(in);
    }
}
