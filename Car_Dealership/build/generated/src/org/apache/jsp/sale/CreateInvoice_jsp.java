package org.apache.jsp.sale;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import dtos.CustomerDTO;
import daos.CustomerDAO;
import dtos.CarsDTO;
import daos.CarsDAO;
import dtos.SalesPersonDTO;
import java.util.List;
import daos.SalesPersonDAO;

public final class CreateInvoice_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>Create Invoice</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h2>Create New Invoice</h2>\n");
      out.write("\n");
      out.write("        <form action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/CreateInvoiceController\" method=\"post\">\n");
      out.write("            <label for=\"invoiceID\">Invoice ID:</label>\n");
      out.write("            <input type=\"text\" id=\"invoiceID\" name=\"invoiceID\" required><br>\n");
      out.write("\n");
      out.write("            <label for=\"invoiceDate\">Invoice Date:</label>\n");
      out.write("            <input type=\"date\" id=\"invoiceDate\" name=\"invoiceDate\" required><br>\n");
      out.write("\n");
      out.write("            <label for=\"salesID\">Sales ID:</label>\n");
      out.write("<!--            <select name=\"salesID\" id=\"salesID\" required>\n");
      out.write("                <option value=\"\">-- Select Sales ID --</option>\n");
      out.write("                ");

                    SalesPersonDAO dao = new SalesPersonDAO();
                    List<SalesPersonDTO> list = dao.getList();
                    for (SalesPersonDTO sale : list) {
                
      out.write("\n");
      out.write("                    <option value=\"");
      out.print( sale.getSalesID() );
      out.write('"');
      out.write('>');
      out.print( sale.getSalesID() );
      out.write(" - ");
      out.print( sale.getSalesName() );
      out.write("</option>\n");
      out.write("                ");

                    }
                
      out.write("\n");
      out.write("                \n");
      out.write("            </select><br>-->\n");
      out.write("                <input type=\"text\" name=\"salesID\" value=\"\" /><br>\n");
      out.write("            \n");
      out.write("\n");
      out.write("            <label for=\"carID\">Car ID:</label>\n");
      out.write("            <select name=\"carID\" id=\"carID\" required>\n");
      out.write("                <option value=\"\">-- Select Car ID --</option>\n");
      out.write("                ");

                    CarsDAO carsDAO = new CarsDAO();
                    List<CarsDTO> carsList = carsDAO.getList();
                    for (CarsDTO car : carsList) {
                
      out.write("\n");
      out.write("                    <option value=\"");
      out.print( car.getCarID() );
      out.write('"');
      out.write('>');
      out.print( car.getCarID() );
      out.write(" - ");
      out.print( car.getModel() );
      out.write(" - ");
      out.print( car.getColour() );
      out.write(" - ");
      out.print( car.getYear() );
      out.write("</option>\n");
      out.write("                ");

                    }
                
      out.write("\n");
      out.write("            </select><br>\n");
      out.write("\n");
      out.write("            <label for=\"custID\">Customer ID:</label>\n");
      out.write("             <select name=\"custID\" id=\"custID\" required>\n");
      out.write("                <option value=\"\">-- Select Customer ID --</option>\n");
      out.write("                ");

                    CustomerDAO customerDAO = new CustomerDAO();
                    List<CustomerDTO> customerList = customerDAO.getList();
                    
                    for (CustomerDTO customer : customerList) {
                
      out.write("\n");
      out.write("                    <option value=\"");
      out.print( customer.getCustID() );
      out.write('"');
      out.write('>');
      out.print( customer.getCustID() );
      out.write(" - ");
      out.print( customer.getCustName() );
      out.write("</option>\n");
      out.write("                ");

                    }
                
      out.write("\n");
      out.write("            </select><br>\n");
      out.write("            \n");
      out.write("            <input type=\"submit\" value=\"Create Invoice\">\n");
      out.write("        </form>\n");
      out.write("\n");
      out.write("        <h3 style=\"color:red;\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${message}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</h3>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
