package daos;

import dtos.SalesPersonDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtil;

public class SalesPersonDAO {

    public List<SalesPersonDTO> getList() {
    List<SalesPersonDTO> list = new ArrayList<>();
    String sql = "SELECT [salesID], [salesName], [birthday], [sex], [salesAddress] FROM [Car_Dealership].[dbo].[SalesPerson]";

    try (Connection conn = DBUtil.getConnection();
         PreparedStatement st = conn.prepareStatement(sql);
         ResultSet rs = st.executeQuery()) {

        while (rs.next()) {
            list.add(new SalesPersonDTO(
                    rs.getString("salesID"),
                    rs.getString("salesName"),
                    rs.getString("birthday"),
                    rs.getString("sex"),
                    rs.getString("salesAddress")));
        }
    } catch (Exception e) {
        e.printStackTrace(); // In lỗi ra console để dễ debug
    }

    // Log kết quả trả về từ cơ sở dữ liệu
    if (list.isEmpty()) {
        System.out.println("No sales data found.");
    } else {
        System.out.println("Found " + list.size() + " sales data.");
    }
    
    return list;
}

    public static void main(String[] args) {
        SalesPersonDAO dao = new SalesPersonDAO();
        List<SalesPersonDTO> list = dao.getList();
        
        if (list.isEmpty()) {
            System.out.println("No salespersons found.");
        } else {
            for (SalesPersonDTO salesPerson : list) {
                System.out.println(salesPerson);          
            }
        }
    }
}
