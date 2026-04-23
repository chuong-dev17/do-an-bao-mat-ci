/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dtos.Parts;
import utils.DBUtil;

/**
 *
 * @author G14
 */
public class PartsDAO {

    public List<Parts> getList() {
        List<Parts> list = new ArrayList<>();
        Connection conn = null;
        String sql = "SELECT [partID]\n"
                + "      ,[partName]\n"
                + "      ,[purchasePrice]\n"
                + "      ,[retailPrice]\n"
                + "  FROM [Car_Dealership].[dbo].[Parts]"
                + "where [Status] = 'Active';";
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                PreparedStatement st = conn.prepareStatement(sql);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    list.add(new Parts(rs.getString("partID"),
                            rs.getString("partName"),
                            rs.getString("purchasePrice"),
                            rs.getString("retailPrice")));
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
        return list;
    }

    public void deleteParts(String partsID) {
        Connection conn = null;
        String sql = "UPDATE Parts\n"
                + "SET Status = 'Deactive'\n"
                + "WHERE partID = ?";
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                PreparedStatement st = conn.prepareStatement(sql);
                st.setString(1, partsID);
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

    public Parts getPartsByID(String partID) {
        Connection conn = null;
        String sql = "SELECT * FROM Parts \n"
                + "WHERE partID = ?";
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                PreparedStatement st = conn.prepareStatement(sql);
                st.setString(1, partID);
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    return new Parts(rs.getString("partID"), rs.getString("partName"), rs.getString("purchasePrice"), rs.getString("retailPrice"));
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

    public void updateParts(Parts p) {
        Connection conn = null;
        String sql = "UPDATE [Parts] \n"
                + "SET [partName] = ? , [purchasePrice] = ? , [retailPrice] = ?\n"
                + "WHERE partID = ?";
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                PreparedStatement st = conn.prepareStatement(sql);
                st.setString(1, p.getPartName());
                st.setString(2, p.getPurchasePrice());
                st.setString(3, p.getRetailPrice());
                st.setString(4, p.getPartID());
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

        public List<Parts> searchPartbyname(String partName) {
        List<Parts> list = new ArrayList<>();
        Connection conn = null;
        String sql = "SELECT [partID]\n"
                + "      ,[partName]\n"
                + "      ,[purchasePrice]\n"
                + "      ,[retailPrice]\n"
                + "  FROM [Car_Dealership].[dbo].[Parts]"
                + "where [Status] = 'Active'and partName LIKE ? ;";
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                PreparedStatement st = conn.prepareStatement(sql);
                st.setString(1, "%" + partName + "%");
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    list.add(new Parts(rs.getString("partID"),
                            rs.getString("partName"),
                            rs.getString("purchasePrice"),
                            rs.getString("retailPrice")));
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
        return list;
    }

    public void addParts(Parts p) {
        Connection conn = null;
        String sql = "INSERT INTO [Parts]([partID],[partName],[purchasePrice],[retailPrice])\n"
                + "  VALUES (?,?,?,?)";
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                PreparedStatement st = conn.prepareStatement(sql);
                st.setString(1, p.getPartID());
                st.setString(2, p.getPartName());
                st.setString(3, p.getPurchasePrice());
                st.setString(4, p.getRetailPrice());
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
}
