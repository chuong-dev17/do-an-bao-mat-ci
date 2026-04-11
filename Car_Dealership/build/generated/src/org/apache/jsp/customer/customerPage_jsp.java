package org.apache.jsp.customer;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class customerPage_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"vi\">\n");
      out.write("    <head>\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("        <title>Dashboard</title>\n");
      out.write("\n");
      out.write("        <!-- Bootstrap CSS -->\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css\">\n");
      out.write("\n");
      out.write("        <style>\n");
      out.write("            body {\n");
      out.write("                font-family: Arial, sans-serif;\n");
      out.write("                margin: 0;\n");
      out.write("                padding: 0;\n");
      out.write("                display: flex;\n");
      out.write("                flex-direction: column;\n");
      out.write("                height: 100vh;\n");
      out.write("                overflow: hidden;\n");
      out.write("            }\n");
      out.write("            .navbar {\n");
      out.write("                background-color: #e74c3c;\n");
      out.write("                padding: 10px 20px;\n");
      out.write("            }\n");
      out.write("            .navbar-brand {\n");
      out.write("                color: white;\n");
      out.write("                font-size: 22px;\n");
      out.write("                font-weight: bold;\n");
      out.write("            }\n");
      out.write("            .navbar-brand img {\n");
      out.write("                height: 40px;\n");
      out.write("                margin-right: 10px;\n");
      out.write("            }\n");
      out.write("            .navbar .welcome-text {\n");
      out.write("                color: white;\n");
      out.write("                font-size: 18px;\n");
      out.write("            }\n");
      out.write("            .sidebar {\n");
      out.write("                width: 250px;\n");
      out.write("                background: #d84315;\n");
      out.write("                color: white;\n");
      out.write("                padding: 20px;\n");
      out.write("                display: flex;\n");
      out.write("                flex-direction: column;\n");
      out.write("                height: calc(100vh - 56px);\n");
      out.write("            }\n");
      out.write("            .sidebar .list-group-item {\n");
      out.write("                background: #d84315;\n");
      out.write("                color: white;\n");
      out.write("                border: none;\n");
      out.write("                text-align: center;\n");
      out.write("                transition: 0.3s;\n");
      out.write("            }\n");
      out.write("            .sidebar .list-group-item:hover {\n");
      out.write("                background: #ff7043;\n");
      out.write("                color: white;\n");
      out.write("            }\n");
      out.write("            .content {\n");
      out.write("                flex-grow: 1;\n");
      out.write("                padding: 20px;\n");
      out.write("            }\n");
      out.write("            iframe {\n");
      out.write("                width: 100%;\n");
      out.write("                height: calc(100vh - 56px);\n");
      out.write("                border: none;\n");
      out.write("            }\n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");

            if (session.getAttribute("USER") == null) {
                response.sendRedirect("../index.html");
                return; // Important: Stop further processing if redirected
            }
        
      out.write("\n");
      out.write("        <!-- Navbar -->\n");
      out.write("        <nav class=\"navbar navbar-dark\">\n");
      out.write("            <div class=\"container-fluid\">\n");
      out.write("                <a class=\"navbar-brand d-flex align-items-center\" href=\"#\">\n");
      out.write("                    <img src=\"./img/customer_icon.png\" alt=\"Logo\">\n");
      out.write("                </a>\n");
      out.write("                <span class=\"welcome-text\"><i class=\"bi bi-person-circle\"></i> Chào mừng, ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${USER.custName}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("!</span>\n");
      out.write("            </div>\n");
      out.write("        </nav>\n");
      out.write("\n");
      out.write("        <div class=\"d-flex\">\n");
      out.write("            <!-- Sidebar -->\n");
      out.write("            <div class=\"sidebar d-flex flex-column\">\n");
      out.write("                <div class=\"list-group flex-grow-1\">\n");
      out.write("                    <a href=\"#\" class=\"list-group-item\" onclick=\"loadPage('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/ListMyInvoiceController')\">\n");
      out.write("                        <i class=\"bi bi-receipt\"></i> My Invoice\n");
      out.write("                    </a>\n");
      out.write("                    <a href=\"#\" class=\"list-group-item\" onclick=\"loadPage('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/ViewSTforCustomerController')\">\n");
      out.write("                        <i class=\"bi bi-file-earmark-check\"></i> My Service Ticket\n");
      out.write("                    </a>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"mt-auto\">\n");
      out.write("                    <a href=\"#\" class=\"list-group-item\" onclick=\"loadPage('customer/UpdateProfile.jsp')\">\n");
      out.write("                        <i class=\"bi bi-person-gear\"></i> Change Profile\n");
      out.write("                    </a>\n");
      out.write("                    <a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/LogoutController\" class=\"list-group-item list-group-item-danger\">\n");
      out.write("                        <i class=\"bi bi-box-arrow-right\"></i> Logout\n");
      out.write("                    </a>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <!-- Content -->\n");
      out.write("            <div class=\"content\">\n");
      out.write("                <iframe id=\"contentFrame\" src=\"\"></iframe>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <!-- Script -->\n");
      out.write("        <script>\n");
      out.write("            function loadPage(url) {\n");
      out.write("                document.getElementById(\"contentFrame\").src = url;\n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("\n");
      out.write("        <!-- Bootstrap JS -->\n");
      out.write("        <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js\"></script>\n");
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
