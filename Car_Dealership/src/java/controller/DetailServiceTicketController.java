/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import daos.PartsUsedDAO;
import daos.ServiceMechanicDAO;
import dtos.PartsUsed;
import dtos.ServiceMechanicDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin_Coder
 */
@WebServlet(name = "DetailServiceTicketController", urlPatterns = {"/DetailServiceTicketController"})
public class DetailServiceTicketController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DetailServiceTicketController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DetailServiceTicketController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy serviceTicketID từ tham số request
            String serviceTicketIDParam = request.getParameter("serviceTicketID");
            PartsUsedDAO dao2 = new PartsUsedDAO();
            List<PartsUsed> detailP = dao2.getListByID(serviceTicketIDParam);
            if (serviceTicketIDParam != null) {
                int serviceTicketID = Integer.parseInt(serviceTicketIDParam);

                // Gọi DAO để lấy dữ liệu chi tiết
                ServiceMechanicDAO dao = new ServiceMechanicDAO();
                List<ServiceMechanicDTO> detailM = dao.getListByID(serviceTicketID);

                // Gửi dữ liệu ra JSP
                request.setAttribute("detailM", detailM);
                request.setAttribute("detailP", detailP);
                request.getRequestDispatcher("customer/DetailServiceTicket.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            // Nếu không thể chuyển đổi ID, trả về lỗi
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid serviceTicketID.");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
