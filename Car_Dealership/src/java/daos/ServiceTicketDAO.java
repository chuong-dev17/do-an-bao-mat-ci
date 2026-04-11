/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.ServiceTicketDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import utils.DBUtil;

/**
 *
 * @author tdinh
 */
public class ServiceTicketDAO {

    public ArrayList<ServiceTicketDTO> getAllServiceTicket() {
        ArrayList<ServiceTicketDTO> rs = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sql = "SELECT [serviceTicketID]\n"
                        + "      ,[dateReceived]\n"
                        + "      ,[dateReturned]\n"
                        + "      ,[custID]\n"
                        + "      ,[carID]\n"
                        + "  FROM [dbo].[ServiceTicket]";
                PreparedStatement st = cn.prepareStatement(sql);

                ResultSet table = st.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        String serviceTicketID = table.getString("serviceTicketID");
                        String dateReceived = table.getString("dateReceived");
                        String dateReturned = "" + table.getString("dateReturned");
                        String custID = table.getString("custID");
                        String carID = table.getString("carID");
                        ServiceTicketDTO svt = new ServiceTicketDTO(serviceTicketID, dateReceived, dateReturned, custID, carID);
                        rs.add(svt);
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
        return rs;
    }

    public ServiceTicketDTO checkExistServiceTicket(String serviceTicketID) {
        ServiceTicketDTO sticket = null;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sql = "SELECT [serviceTicketID]\n"
                        + "      ,[dateReceived]\n"
                        + "      ,[dateReturned]\n"
                        + "      ,[custID]\n"
                        + "      ,[carID]\n"
                        + "  FROM [dbo].[ServiceTicket]\n"
                        + "     WHERE serviceTicketID = ?";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, serviceTicketID);
                ResultSet r = st.executeQuery();
                if (r != null) {
                    while (r.next()) {
                        String sID = r.getString("serviceTicketID");
                        String dateReceived = r.getString("dateReceived");
                        String dateReturned = r.getString("dateReturned");
                        String custID = r.getString("custID");
                        String carID = r.getString("carID");
                        sticket = new ServiceTicketDTO(sID, dateReceived, dateReturned, custID, carID);
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
        return sticket;
    }

    public void insertServiceTicket(String id, String date, String dateReturn, String custId, String CarId) {

        try {
            Connection cnn = DBUtil.getConnection();
            String sql = "INSERT INTO [dbo].[ServiceTicket]\n"
                    + "           ([serviceTicketID], [dateReceived], [dateReturned], [custID], [carID])\n"
                    + "     VALUES (?, ?, ?, ?, ?); ";

            PreparedStatement st = cnn.prepareStatement(sql);

            st.setString(1, id.trim());
            st.setString(2, date.trim());
            st.setString(3, dateReturn.trim());
            st.setString(4, custId.trim());
            st.setString(5, CarId.trim());

            st.executeUpdate();

        } catch (Exception e) {
        }
    }

    public ArrayList<ServiceTicketDTO> searchServiceTicket(String custID, String carID, String dateReceived) {
        ArrayList<ServiceTicketDTO> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sql = "SELECT [serviceTicketID]\n"
                        + "      ,[dateReceived]\n"
                        + "      ,[dateReturned]\n"
                        + "      ,[custID]\n"
                        + "      ,[carID]\n"
                        + "  FROM [Car_Dealership].[dbo].[ServiceTicket]\n"
                        + "  WHERE custID LIKE ? AND carID LIKE ? AND dateReceived LIKE ?";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1,"%"+custID+"%");
                st.setString(2,"%"+carID+"%");
                st.setString(3,"%"+ dateReceived +"%");
                ResultSet table = st.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        list.add(new ServiceTicketDTO(table.getString("serviceTicketID"),
                                 table.getString("dateReceived"),
                                 table.getString("dateReturned"),
                                 table.getString("custID"),
                                 table.getString("carID")));
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
        return list;
    }
    
    public ArrayList<ServiceTicketDTO> viewServiceTicketCustomer(String custID) {
        ArrayList<ServiceTicketDTO> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sql = "SELECT [serviceTicketID]\n"
                        + "      ,[dateReceived]\n"
                        + "      ,[dateReturned]\n"
                        + "      ,[custID]\n"
                        + "      ,[carID]\n"
                        + "  FROM [Car_Dealership].[dbo].[ServiceTicket]\n"
                        + "  WHERE custID = ?";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1,custID);
                ResultSet table = st.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        list.add(new ServiceTicketDTO(table.getString("serviceTicketID"),
                                 table.getString("dateReceived"),
                                 table.getString("dateReturned"),
                                 table.getString("custID"),
                                 table.getString("carID")));
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
        return list;
    }
}
