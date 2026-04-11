/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import daos.SalesInvoiceDAO;
import dtos.SalesInvoiceDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin_Coder
 */
@WebServlet(name = "CreateInvoiceController", urlPatterns = {"/CreateInvoiceController"})
public class CreateInvoiceController extends HttpServlet {

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
            out.println("<title>Servlet CreateInvoiceController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateInvoiceController at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        // Retrieve form parameters from the request
        String invoiceID = request.getParameter("invoiceID");
        String invoiceDate = request.getParameter("invoiceDate");
        String salesID = request.getParameter("salesID");
        String carID = request.getParameter("carID");
        String custID = request.getParameter("custID");

        // Create DAO instance to interact with the database
        SalesInvoiceDAO dao = new SalesInvoiceDAO();

        // Check if the invoice already exists
        SalesInvoiceDTO data = dao.getInvoiceByID(invoiceID);

        // If the invoice exists, show error message
        if (data != null) {
            request.setAttribute("message", "Existed ID, please enter another id!");
            request.getRequestDispatcher("sale/CreateInvoice.jsp").forward(request, response);
        } else {
            // If the invoice doesn't exist, create the new invoice
            dao.create(new SalesInvoiceDTO(invoiceID, invoiceDate, salesID, carID, custID));
            // Redirect to the list page after successful creation
            request.setAttribute("message", "Success");
            request.getRequestDispatcher("sale/CreateInvoice.jsp").forward(request, response);
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
