/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import daos.CarsDAO;
import daos.CustomerDAO;
import daos.ServiceTicketDAO;
import dtos.CarsDTO;
import dtos.CustomerDTO;
import dtos.ServiceTicketDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tdinh
 */
@WebServlet(name = "CreateServiceTicketController", urlPatterns = {"/CreateServiceTicketController"})
public class CreateServiceTicketController extends HttpServlet {

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

        /* TODO output your page here. You may use following sample code. */
        request.setCharacterEncoding("UTF-8");

        String serviceTicketID = request.getParameter("serviceTicketID");
        String dateReceived = request.getParameter("dateReceived");
        String dateReturned = request.getParameter("dateReturned");
        String custID = request.getParameter("custID");
        String carID = request.getParameter("carID");

        CarsDAO dao = new CarsDAO();
        CarsDTO c = dao.checkExistCar(carID);

        ServiceTicketDAO s = new ServiceTicketDAO();
        ServiceTicketDTO sd = s.checkExistServiceTicket(serviceTicketID);

        CustomerDAO cusD = new CustomerDAO();
        CustomerDTO cus = cusD.checkIDCustomer(custID);
        if (sd != null) {
            request.setAttribute("serviceTicketIDExist", "ServiceTicket ID is EXIST !!!");
            request.getRequestDispatcher("ViewSTforSale").forward(request, response);
            return; // Dừng chương trình ngay khi forward
        }

        if (c == null) {
            request.setAttribute("CarId", "Car ID is not EXIST !!!");
            request.getRequestDispatcher("ViewSTforSale").forward(request, response);
        }

        if (cus == null) {
            request.setAttribute("CustId", "Cust ID is not EXIST !!!");
            request.getRequestDispatcher("ViewSTforSale").forward(request, response);
        }

        if (sd == null && c != null && cus != null) {
            ServiceTicketDAO s2 = new ServiceTicketDAO();

            s2.insertServiceTicket(serviceTicketID, dateReceived, dateReturned, custID, carID);
            response.sendRedirect("ViewSTforSale");

        } else {
            request.setAttribute("ERROR", "Please check format o");
            request.getRequestDispatcher("ViewSTforSale").forward(request, response);
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
