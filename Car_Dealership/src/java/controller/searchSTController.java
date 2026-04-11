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

@WebServlet(name = "searchSTController", urlPatterns = {"/searchSTController"})
public class searchSTController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String custID = request.getParameter("custID").trim();
        String carID = request.getParameter("carID").trim();
        String dateReceived = request.getParameter("dateReceived").trim();
        

        ServiceTicketDAO dao = new ServiceTicketDAO();
        List<ServiceTicketDTO> list = dao.searchServiceTicket(custID, carID, dateReceived);

        request.setAttribute("data", list);
        request.getRequestDispatcher("mechanic/ServiceTicket.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Search Service Ticket Controller";
    }
}
