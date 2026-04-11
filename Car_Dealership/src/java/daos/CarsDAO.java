/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.CarsDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtil;

/**
 *
 * @author tdinh
 */
public class CarsDAO {

    public ArrayList<CarsDTO> searchCars(String serialNumber, String model, String year) {
        ArrayList<CarsDTO> rs = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sql = "SELECT carID, serialNumber, model, colour, year FROM Cars WHERE 1=1";
                if (serialNumber != null && !serialNumber.isEmpty()) {
                    sql += " AND serialNumber LIKE ?";
                }
                if (model != null && !model.isEmpty()) {
                    sql += " AND model LIKE ?";
                }
                if (year != null && !year.isEmpty()) {
                    sql += " AND year = ?";
                }
                PreparedStatement st = cn.prepareStatement(sql);
                int index = 1;
                if (serialNumber != null && !serialNumber.isEmpty()) {
                    st.setString(index++, "%" + serialNumber.trim() + "%");
                }
                if (model != null && !model.isEmpty()) {
                    st.setString(index++, "%" + model.trim() + "%");
                }
                if (year != null && !year.isEmpty()) {
                    st.setString(index++, year.trim());
                }
                ResultSet table = st.executeQuery();
                while (table.next()) {
                    String carID = table.getString("carID");
                    String sn = table.getString("serialNumber");
                    String mdl = table.getString("model");
                    String colour = table.getString("colour");
                    String yr = table.getString("year");
                    rs.add(new CarsDTO(carID, sn, mdl, colour, yr));
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

    public CarsDTO checkExistCar(String carID) {
        CarsDTO c = null;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sql = "SELECT [carID]\n"
                        + "      ,[serialNumber]\n"
                        + "      ,[model]\n"
                        + "      ,[colour]\n"
                        + "      ,[year]\n"
                        + "  FROM [dbo].[Cars]"
                        + "  Where carID = ?";

                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setString(1, carID);
                ResultSet table = ps.executeQuery();
                if (table != null) {
                    while (table.next()) {

                        String serialNumber = table.getString("serialNumber");
                        String model = "" + table.getString("model");
                        String colour = table.getString("colour");
                        String year = table.getString("year");
                        c = new CarsDTO(carID, serialNumber, model, colour, year);
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

    public int addCar(String carID, String serialNumber, String model, String colour, String year) {
        int r = 0;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sql = "INSERT INTO [dbo].[Cars]\n"
                        + "           ([carID]\n"
                        + "           ,[serialNumber]\n"
                        + "           ,[model]\n"
                        + "           ,[colour]\n"
                        + "           ,[year])\n"
                        + "     VALUES (?,?,?,?,?)";

                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setString(1, carID);
                ps.setString(2, serialNumber);
                ps.setString(3, model);
                ps.setString(4, colour);
                ps.setString(5, year);
                return ps.executeUpdate();
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
        return r;
    }

    public void updateCar(String carID, String serialNumber, String model, String colour, String year) {

        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sql = "UPDATE [dbo].[Cars]\n"
                        + "   SET [year] = ?,"
                        + "   [serialNumber] = ?,"
                        + " [model]=? ,"
                        + " [colour] = ? "
                        + " WHERE [carID] = ?";

                PreparedStatement ps = cn.prepareStatement(sql);

                ps.setString(1, year);
                ps.setString(2, serialNumber);
                ps.setString(3, model);
                ps.setString(4, colour);
                ps.setString(5, carID);
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

    public CarsDTO checCarToDelete(String carID) {
        CarsDTO c = null;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sql = "SELECT c.[carID], c.[serialNumber], c.[model], c.[colour], c.[year] "
                        + "FROM Cars c "
                        + "JOIN ServiceTicket s ON c.carID = s.carID "
                        + "WHERE c.carID = ?";

                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setString(1, carID);
                ResultSet table = ps.executeQuery();
                if (table.next()) {
                    String serialNumber = table.getString("serialNumber");
                    String model = table.getString("model");
                    String colour = table.getString("colour");
                    String year = table.getString("year");
                    c = new CarsDTO(carID, serialNumber, model, colour, year);
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

    public void deleteCar(String carID) {

        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sql = "DELETE FROM [Cars]\n"
                        + "  WHERE carID = ? ";
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setString(1, carID);
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

    public List<CarsDTO> getList() {
        List<CarsDTO> carsList = new ArrayList<>();
        String sql = "SELECT carID, serialNumber, model, colour, year FROM Cars"; 

        try (Connection conn = DBUtil.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) { 

            while (rs.next()) {
                String carID = rs.getString("carID");
                String serialNumber = rs.getString("serialNumber");
                String model = rs.getString("model");
                String colour = rs.getString("colour");
                String year = rs.getString("year");

                CarsDTO car = new CarsDTO(carID, serialNumber, model, colour, year);

                carsList.add(car);
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        return carsList;
    }
    
    public static void main(String[] args) {
        CarsDAO dao = new CarsDAO();
        List<CarsDTO> list = dao.getList();
        
        if (list.isEmpty()) {
            System.out.println("No salespersons found.");
        } else {
            for (CarsDTO salesPerson : list) {
                System.out.println(salesPerson);          
            }
        }
    }
}
