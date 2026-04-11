/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import daos.ServiceMechanicDAO;
import dtos.ServiceMechanicDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin_Coder
 */
public class UpdateServiceMechanicController extends HttpServlet {

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
            out.println("<title>Servlet UpdateServiceMechanicController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateServiceMechanicController at " + request.getContextPath() + "</h1>");
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
        String serviceTicketID_raw = request.getParameter("serviceTicketID");
        String serviceID_raw = request.getParameter("serviceID");
        try {
            int serviceTicketID = Integer.parseInt(serviceTicketID_raw);
            int serviceID = Integer.parseInt(serviceID_raw);
            ServiceMechanicDAO dao = new ServiceMechanicDAO();
            ServiceMechanicDTO sm = dao.getServiceMechanicByID(serviceTicketID, serviceID);
            request.setAttribute("serviceSM", sm);
            request.getRequestDispatcher("mechanic/UpdateSM.jsp").forward(request, response);
        } catch (Exception e) {
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String serviceTicketID_raw = request.getParameter("serviceTicketID");
        String serviceID_raw = request.getParameter("serviceID");
        String mechanicID = request.getParameter("mechanicID");
        String hours_raw = request.getParameter("hours");
        String comment = request.getParameter("comment");
        String rate = request.getParameter("rate");

        try {
            int serviceTicketID = Integer.parseInt(serviceTicketID_raw);
            int serviceID = Integer.parseInt(serviceID_raw);
            int hours = Integer.parseInt(hours_raw);
            ServiceMechanicDAO dao = new ServiceMechanicDAO();
            dao.updateServiceMechanic(new ServiceMechanicDTO(serviceTicketID, serviceID, mechanicID, hours, comment, rate));
            response.sendRedirect("ListServiceMechanicController");
        } catch (Exception e) {
        }
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
