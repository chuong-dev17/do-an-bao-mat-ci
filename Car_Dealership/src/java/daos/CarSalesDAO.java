package daos;

import dtos.BestSellingCarModel;
import dtos.BestUsedPart;
import dtos.CarRevenueByYear;
import dtos.CarSalesByYear;
import java.sql.*;
import java.util.*;
import dtos.PartRevenue;
import dtos.TopMechanic;
import utils.DBUtil;

public class CarSalesDAO {

    public static List<CarSalesByYear> getCarsSoldByYear() {
        List<CarSalesByYear> carSales = new ArrayList<>();
        String query = "SELECT YEAR(invoiceDate) AS year, COUNT(carID) AS total "
                + "FROM SalesInvoice GROUP BY YEAR(invoiceDate) ORDER BY year";
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                carSales.add(new CarSalesByYear(rs.getInt("year"), rs.getInt("total")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return carSales;
    }

    public static List<CarRevenueByYear> getCarSalesRevenueByYear() {
        List<CarRevenueByYear> revenueByYear = new ArrayList<>();
        String query = "SELECT YEAR(invoiceDate) AS year, SUM(price) AS revenue "
                + "FROM SalesInvoice GROUP BY YEAR(invoiceDate) ORDER BY year";

        try (Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                revenueByYear.add(new CarRevenueByYear(rs.getInt("year"), rs.getDouble("revenue")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return revenueByYear;
    }

    public static List<BestSellingCarModel> getBestSellingCarModels() {
        List<BestSellingCarModel> bestSellingCars = new ArrayList<>();
        String query = "SELECT c.model, COUNT(si.carID) AS total_sold "
                + "FROM SalesInvoice si "
                + "JOIN Cars c ON si.carID = c.carID "
                + "GROUP BY c.model "
                + "ORDER BY total_sold DESC";

        try (Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                bestSellingCars.add(new BestSellingCarModel(rs.getString("model"), rs.getInt("total_sold")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bestSellingCars;
    }

    public static List<BestUsedPart> getBestUsedParts() {
        List<BestUsedPart> bestUsedParts = new ArrayList<>();
        String query = "SELECT p.partName, SUM(pu.numberUsed) AS totalUsed, SUM(pu.price) AS totalRevenue "
                + "FROM PartsUsed pu "
                + "JOIN Parts p ON pu.partID = p.partID "
                + "GROUP BY p.partID, p.partName "
                + "ORDER BY totalUsed DESC";

        try (Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                bestUsedParts.add(new BestUsedPart(
                        rs.getString("partName"),
                        rs.getInt("totalUsed"),
                        rs.getDouble("totalRevenue")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bestUsedParts;
    }

    public static List<PartRevenue> getPartsRevenue() {
        List<PartRevenue> partsRevenue = new ArrayList<>();
        String query = "SELECT p.partName, SUM(sp.price) AS total_revenue "
                + "FROM PartsUsed sp "
                + "JOIN Parts p ON sp.partID = p.partID "
                + "GROUP BY p.partName "
                + "ORDER BY total_revenue DESC;";

        try (Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                partsRevenue.add(new PartRevenue(rs.getString("partName"), rs.getDouble("total_revenue")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return partsRevenue; //with ties
    }

    public static List<TopMechanic> getTop3Mechanics() {
        List<TopMechanic> topMechanics = new ArrayList<>();
        String query = "SELECT TOP 3 m.mechanicID, m.mechanicName, COUNT(sm.serviceTicketID) AS repairCount "
                + "FROM ServiceMehanic sm "
                + "JOIN Mechanic m ON sm.mechanicID = m.mechanicID "
                + "GROUP BY m.mechanicID, m.mechanicName "
                + "ORDER BY repairCount DESC";

        try (Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                topMechanics.add(new TopMechanic(
                        rs.getString("mechanicID"),
                        rs.getString("mechanicName"),
                        rs.getInt("repairCount")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return topMechanics;
    }
    //ORDER BY repairCount DESC, mechanicID ASC
}
