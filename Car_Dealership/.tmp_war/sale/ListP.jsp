<%-- 
    Document   : ListP
    Created on : Jan 23, 2025, 2:39:27 PM
    Author     : G14
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="h"%>
<html lang="vi">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript">
            function doDelete(partID) {
                if (confirm("Are you sure to delete Part id= '" + partID + "'?")) {
                    window.location = "DeleteParts?partID=" + partID;
                }
            }
        </script>
    </head>
    <body>
        <form method="get" action="SearchParts" accept-charset="UTF-8">
            Search: <input type="text" name="search" value="${param.search}" />
            <input type="submit" value="search" />
        </form>
        <table border="1">
            <thead>
                <tr>
                    <th>partID</th>
                    <th>partName</th>
                    <th>purchasePrice</th>
                    <th>retailPrice</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <h:forEach items="${data}" var="l">
                    <tr>
                        <td>${l.partID}</td>
                        <td>${l.partName}</td>
                        <td>${l.purchasePrice}</td>
                        <td>${l.retailPrice}</td>
                        <td>
                            <a href="#" onclick="doDelete('${l.partID}')">Delete</a>
                            <a href="UpdateParts?partID=${l.partID}">Update</a>
                        </td>
                    </tr>
                </h:forEach>
            </tbody>
        </table>
        <a href="sale/AddP.jsp">Add Part</a>
    </body>
</html>
