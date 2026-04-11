<%-- 
    Document   : AddP
    Created on : Feb 28, 2025, 12:37:27 AM
    Author     : G14
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/AddParts">
            <table>
                <tbody>
                    <tr>
                        <td>PartID</td>
                        <td><input type="text" name="partID" value="" /></td>
                    </tr>
                    <tr>
                        <td>PartName</td>
                        <td><input type="text" name="partName" value="" /></td>
                    </tr>
                    <tr>
                        <td>purchasePrice</td>
                        <td><input type="text" name="purchasePrice" value="" /></td>
                    </tr>
                    <tr>
                        <td>retailPrice</td>
                        <td><input type="text" name="retailPrice" value="" /></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Update" /></td>
                        <td></td>
                    </tr>
                </tbody>
            </table>

        </form>
    </body>
</html>
