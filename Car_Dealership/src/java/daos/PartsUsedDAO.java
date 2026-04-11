/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.PartsUsed;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtil;

/**
 *
 * @author Admin_Coder
 */
public class PartsUsedDAO {

    public List<PartsUsed> getListByID(String serviceTicketID) {
        List<PartsUsed> list = new ArrayList<>();
        Connection conn = null;
        String sql = "SELECT [serviceTicketID]\n"
                + "      ,[partID]\n"
                + "      ,[numberUsed]\n"
                + "      ,[price]\n"
                + "  FROM [Car_Dealership].[dbo].[PartsUsed]\n"
                + "  WHERE [serviceTicketID] = ?";
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                PreparedStatement st = conn.prepareStatement(sql);
                st.setString(1, serviceTicketID);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    list.add(new PartsUsed(rs.getString("serviceTicketID"),
                            rs.getString("partID"),
                            rs.getString("numberUsed"),
                            rs.getString("price")));
                }
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public static void main(String[] args) {
        PartsUsedDAO dao = new PartsUsedDAO();
        List<PartsUsed> list = dao.getListByID("10111232");
        
        if (list.isEmpty()) {
            System.out.println("No salespersons found.");
        } else {
            for (PartsUsed salesPerson : list) {
                System.out.println(salesPerson);          
            }
        }
    }
}
