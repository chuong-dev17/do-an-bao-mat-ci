/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.CarsDTO;
import dtos.CustomerDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtil;

/**
 *
 * @author tdinh
 */
public class CustomerDAO {

    public ArrayList<CustomerDTO> getAllCust(String name) {
        ArrayList<CustomerDTO> rs = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sql = "SELECT custID, custName, phone, sex, cusAddress FROM dbo.Customer WHERE 1=1";
                if (name != null && !name.trim().isEmpty()) {
                    sql += " AND custName LIKE ?";
                }

                PreparedStatement st = cn.prepareStatement(sql);
                if (name != null && !name.trim().isEmpty()) {
                    st.setString(1, "%" + name.trim() + "%");
                }

                ResultSet table = st.executeQuery();
                while (table.next()) {
                    String custid = table.getString("custID");
                    String custname = table.getString("custName");
                    String phone = table.getString("phone");
                    String sex = table.getString("sex");
                    String custadd = table.getString("cusAddress");
                    rs.add(new CustomerDTO(custid, custname, phone, sex, custadd));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rs;
    }

    public CustomerDTO checkIDCustomer(String custID) {
        CustomerDTO c = null;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sql = "SELECT [custID]\n"
                        + "      ,[custName]\n"
                        + "      ,[phone]\n"
                        + "      ,[sex]\n"
                        + "      ,[cusAddress]\n"
                        + "  FROM [dbo].[Customer]\n"
                        + "  Where custID = ?";

                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setString(1, custID);
                ResultSet table = ps.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        String custid = table.getString("custID");
                        String custname = table.getString("custName");
                        String phone = "" + table.getString("phone");
                        String sex = table.getString("sex");
                        String custadd = table.getString("cusAddress");
                        c = new CustomerDTO(custid, custname, phone, sex, custadd);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return c;
    }

    public void addCustomer(String custID, String custName, String phone, String sex, String custAddress) {
        int r = 0;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sql = "INSERT INTO [dbo].[Customer]\n"
                        + "           ([custID]\n"
                        + "           ,[custName]\n"
                        + "           ,[phone]\n"
                        + "           ,[sex]\n"
                        + "           ,[cusAddress]\n)"
                        + "     VALUES  (?,?,?,?,?)";

                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setString(1, custID);
                ps.setString(2, custName);
                ps.setString(3, phone);
                ps.setString(4, sex);
                ps.setString(5, custAddress);
                ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public void updateCustomer(String custID, String custName, String phone, String sex, String custAddress) {
        int r = 0;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sql = "UPDATE [dbo].[Customer]\n"
                        + "   SET [custName] = ?,"
                        + "[phone] = ?,"
                        + "[sex] =? ,"
                        + "[cusAddress] = ? "
                        + " WHERE custID = ?";

                PreparedStatement ps = cn.prepareStatement(sql);

                ps.setString(1, custName);
                ps.setString(2, phone);
                ps.setString(3, sex);
                ps.setString(4, custAddress);
                ps.setString(5, custID);
                r = ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

//
//    public int deleteCustomer(int custID) {
//        int r = 0;
//        Connection cn = null;
//        try {
//            cn = Connections.getConnection();
//            if (cn != null) {
//                String sql = "DELETE FROM [dbo].[Customer]\n"
//                        + "      WHERE custID = ? ";
//
//                PreparedStatement ps = cn.prepareStatement(sql);
//                ps.setInt(1, custID);
//                r = ps.executeUpdate();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (cn != null) {
//                    cn.close();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
//        return r;
//
//    }
    // check de delteted
    public CustomerDTO checkExistCustomer(String custID) {
        CustomerDTO c = null;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sql = "SELECT c.custID, c.custName, c.phone, c.sex, c.cusAddress "
                        + "FROM Customer c JOIN SalesInvoice s ON c.custID = s.custID "
                        + "WHERE c.custID = ?";

                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setString(1, custID);
                ResultSet table = ps.executeQuery();

                if (table.next()) {
                    String custid = table.getString("custID");
                    String custname = table.getString("custName");
                    String phone = table.getString("phone");
                    String sex = table.getString("sex");
                    String custadd = table.getString("cusAddress");
                    c = new CustomerDTO(custid, custname, phone, sex, custadd);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return c;
    }

    // delete customerById
    public void deletecustomerById(String id) {

        try {
            Connection cnn = DBUtil.getConnection();
            String sql = "DELETE FROM [dbo].[Customer]\n"
                    + "      WHERE custID = ?";
            
            PreparedStatement st = cnn.prepareStatement(sql);
            
            st.setString(1, id);
            
           st.executeUpdate();
               
        } catch (Exception e) {
        }
    }
    
    public List<CustomerDTO> getList() {
        List<CustomerDTO> customerList = new ArrayList<>();
        String sql = "SELECT custID, custName, phone, sex, cusAddress FROM Customer";  // Cập nhật với tên bảng của bạn
        
        // Kết nối và thực thi truy vấn
        try (Connection conn = DBUtil.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                // Tạo đối tượng CustomerDTO và ánh xạ dữ liệu
                CustomerDTO customer = new CustomerDTO(
                    rs.getString("custID"),
                    rs.getString("custName"),
                    rs.getString("phone"),
                    rs.getString("sex"),
                    rs.getString("cusAddress")
                );
                // Thêm vào danh sách
                customerList.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return customerList;
    }
    
    public static void main(String[] args) {
        CustomerDAO dao = new CustomerDAO();
        List<CustomerDTO> list = dao.getList();
        
        if (list.isEmpty()) {
            System.out.println("No salespersons found.");
        } else {
            for (CustomerDTO salesPerson : list) {
                System.out.println(salesPerson);          
            }
        }
    }

}
