package controller;

import daos.ServiceTicketDAO;
import dtos.ServiceTicketDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ViewSTforMechanic", urlPatterns = {"/ViewSTforMechanic"})
public class ViewSTforMechanic extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServiceTicketDAO dao = new ServiceTicketDAO();
        List<ServiceTicketDTO> list = dao.getAllServiceTicket();
        request.setAttribute("data", list);
        request.getRequestDispatcher("mechanic/ServiceTicket.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response); // Cho POST cũng load danh sách luôn
    }

    @Override
    public String getServletInfo() {
        return "View all Service Tickets for Mechanic";
    }
}
