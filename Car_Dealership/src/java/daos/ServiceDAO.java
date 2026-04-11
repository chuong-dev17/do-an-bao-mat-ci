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
import dtos.ServiceDTO;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.NamingException;
import utils.DBUtil;

/**
 *
 * @author Admin_Coder
 */
public class ServiceDAO {

    public List<ServiceDTO> getList() {
        List<ServiceDTO> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT TOP (1000) [serviceID]\n"
                        + "      ,[serviceName]\n"
                        + "      ,[hourlyRate]\n"
                        + "  FROM [Car_Dealership].[dbo].[Service]\n"
                        + "  WHERE [Status] = 'Active';";
                PreparedStatement st = conn.prepareStatement(sql);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    list.add(new ServiceDTO(rs.getInt("serviceID"),
                            rs.getString("serviceName"),
                            rs.getString("hourlyRate")));
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

    public void deleteService(int serviceID) {
        Connection conn = null;
        String sql = "UPDATE Service\n"
                + "SET Status = 'Deactive'\n"
                + "WHERE serviceID = ?";
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                PreparedStatement st = conn.prepareStatement(sql);
                st.setInt(1, serviceID);
                st.executeUpdate();
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
    }

    public void updateService(ServiceDTO s) {
        Connection conn = null;
        String sql = "UPDATE Service \n"
                + "  SET [serviceName] = ? , [hourlyRate] = ?\n"
                + "  WHERE [serviceID] = ?";
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                PreparedStatement st = conn.prepareStatement(sql);
                st.setString(1, s.getServiceName());
                st.setString(2, s.getHourlyRate());
                st.setInt(3, s.getServiceID());
                st.executeUpdate();
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
    }

    public ServiceDTO getServiceByID(int serviceID) {
        Connection conn = null;
        String sql = "SELECT [serviceID], [serviceName], [hourlyRate] FROM Service WHERE serviceID = ?";
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                PreparedStatement st = conn.prepareStatement(sql);
                st.setInt(1, serviceID);
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    return new ServiceDTO(rs.getInt("serviceID"),
                            rs.getString("serviceName"),
                            rs.getString("hourlyRate"));
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
        return null;
    }

    public void addService(ServiceDTO s) {
        Connection conn = null;
        String sql = "INSERT INTO Service (serviceID, serviceName, hourlyRate)\n"
                + "VALUES (?,?,?)";
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                PreparedStatement st = conn.prepareStatement(sql);
                st.setInt(1, s.getServiceID());
                st.setString(2, s.getServiceName());
                st.setString(3, s.getHourlyRate());
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
}
