<%-- 
    Document   : UpdateProfile.jsp
    Created on : 28/02/2025, 2:46:23 PM
    Author     : Admin_Coder
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/UpdateProfileController" method="post" accept-charset="UTF-8">
            <table>
                <tbody>
                    <tr>
                        <td>custName</td>
                        <td><input type="text" name="custName" value="${USER.custName}" /></td>
                    </tr>
                    <tr>
                        <td>phone</td>
                        <td><input type="text" name="phone" value="${USER.phone}" /></td>
                    </tr>
                    <tr>
                        <td>sex</td>
                        <td><input type="text" name="sex" value="${USER.sex}" /></td>
                    </tr>
                    <tr>
                        <td>cusAddress</td>
                        <td><input type="text" name="cusAddress" value="${USER.cusAddress}" /></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Update" /></td>
                        <td></td>
                    </tr>
                </tbody>
            </table>            
        </form>
                    <h1>${success}</h1>
    </body>
</html>
