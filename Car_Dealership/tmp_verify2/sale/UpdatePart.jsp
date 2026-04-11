<%--
    Document   : UpdatePart
    Created on : Feb 19, 2025, 2:02:47 AM
    Author     : G14
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Part</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
            }

            form {
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            }

            table {
                width: 100%;
            }

            td {
                padding: 8px;
            }

            input[type="text"] {
                width: calc(100% - 16px);
                padding: 8px;
                margin: 5px 0;
                border: 1px solid #ddd;
                border-radius: 4px;
                box-sizing: border-box;
            }

            input[type="submit"] {
                background-color: #4CAF50;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }

            input[type="submit"]:hover {
                background-color: #45a049;
            }
        </style>
    </head>
    <body>
        <form method="post" accept-charset="UTF-8">
            <table>
                <thead>
                    <tr>
                        <th scope="col">Field</th>
                        <th scope="col">Value</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Part ID</td>
                        <td><input type="text" name="partID" value="${Part.partID}" readonly=""/></td>
                    </tr>
                    <tr>
                        <td>Part Name</td>
                        <td><input type="text" name="partName" value="${Part.partName}"/></td>
                    </tr>
                    <tr>
                        <td>Purchase Price</td>
                        <td><input type="text" name="purchasePrice" value="${Part.purchasePrice}"/></td>
                    </tr>
                    <tr>
                        <td>Retail Price</td>
                        <td><input type="text" name="retailPrice" value="${Part.retailPrice}"/></td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: center;"><input type="submit" value="Update" /></td>
                    </tr>
                </tbody>
            </table>
        </form>
    </body>
</html>
