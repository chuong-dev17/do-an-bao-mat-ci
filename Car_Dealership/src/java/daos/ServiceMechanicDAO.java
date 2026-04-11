/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import dtos.ServiceMechanicDTO;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.NamingException;
import utils.DBUtil;

/**
 *
 * @author Admin_Coder
 */
public class ServiceMechanicDAO {

    public List<ServiceMechanicDTO> getList(String mechanicID) {
        Connection conn = null;
        List<ServiceMechanicDTO> list = new ArrayList<>();
        String sql = "SELECT [serviceTicketID]\n"
                + "      ,[serviceID]\n"
                + "      ,[mechanicID]\n"
                + "      ,[hours]\n"
                + "      ,[comment]\n"
                + "      ,[rate]\n"
                + "  FROM [Car_Dealership].[dbo].[ServiceMehanic]"
                + "  WHERE mechanicID = ?";
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                PreparedStatement st = conn.prepareStatement(sql);
                st.setString(1, mechanicID);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    list.add(new ServiceMechanicDTO(rs.getInt("serviceTicketID"),
                            rs.getInt("serviceID"),
                            rs.getString("mechanicID"),
                            rs.getInt("hours"),
                            rs.getString("comment"),
                            rs.getString("rate")));
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
        return list;
    }

    public void updateServiceMechanic(ServiceMechanicDTO sm) {
        Connection conn = null;
        String sql = "UPDATE ServiceMehanic\n"
                + "     SET [hours] = ?, [comment] = ?, [rate] = ?\n"
                + "     WHERE [serviceTicketID] = ? AND [serviceID] = ?";
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                PreparedStatement st = conn.prepareStatement(sql);
                st.setInt(1, sm.getHours());
                st.setString(2, sm.getComment());
                st.setString(3, sm.getRate());
                st.setInt(4, sm.getServiceTicketID());
                st.setInt(5, sm.getServiceID());
                st.executeUpdate();
            }
        } catch (Exception e) {
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
    }

    public ServiceMechanicDTO getServiceMechanicByID(int serviceTicketID, int serviceID) {
        Connection conn = null;
        String sql = "SELECT [serviceTicketID]\n"
                + "                     ,[serviceID]\n"
                + "                     ,[mechanicID]\n"
                + "                     ,[hours]\n"
                + "                     ,[comment]\n"
                + "                     ,[rate]\n"
                + "                 FROM [Car_Dealership].[dbo].[ServiceMehanic]\n"
                + "		    WHERE [serviceTicketID] = ? AND [serviceID] = ?";
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                PreparedStatement st = conn.prepareStatement(sql);
                st.setInt(1, serviceTicketID);
                st.setInt(2, serviceID);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    return new ServiceMechanicDTO(
                            rs.getInt("serviceTicketID"),
                            rs.getInt("serviceID"),
                            rs.getString("mechanicID"),
                            rs.getInt("hours"),
                            rs.getString("comment"),
                            rs.getString("rate")
                    );
                }
            }
        } catch (Exception e) {
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
        return null;
    }
    
    public List<ServiceMechanicDTO> getListByID(int serviceTicketID) {
        Connection conn = null;
        List<ServiceMechanicDTO> list = new ArrayList<>();
        String sql = "SELECT [serviceTicketID]\n"
                + "      ,[serviceID]\n"
                + "      ,[mechanicID]\n"
                + "      ,[hours]\n"
                + "      ,[comment]\n"
                + "      ,[rate]\n"
                + "  FROM [Car_Dealership].[dbo].[ServiceMehanic]"
                + "  WHERE serviceTicketID = ?";
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                PreparedStatement st = conn.prepareStatement(sql);
                st.setInt(1, serviceTicketID);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    list.add(new ServiceMechanicDTO(rs.getInt("serviceTicketID"),
                            rs.getInt("serviceID"),
                            rs.getString("mechanicID"),
                            rs.getInt("hours"),
                            rs.getString("comment"),
                            rs.getString("rate")));
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
        return list;
    }
    
        public static void main(String[] args) {
        ServiceMechanicDAO dao = new ServiceMechanicDAO();
        int serviceTicketID = 10111231;
        List<ServiceMechanicDTO> list = dao.getListByID(serviceTicketID);
        
        if (list.isEmpty()) {
            System.out.println("No salespersons found.");
        } else {
            for (ServiceMechanicDTO salesPerson : list) {
                System.out.println(salesPerson);          
            }
        }
    }
}
