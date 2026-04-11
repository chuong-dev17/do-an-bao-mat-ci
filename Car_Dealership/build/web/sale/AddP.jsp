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
        <title>Add Part</title>
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
                width: 350px; /* Adjust the width as needed */
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
                width: 100%;
            }

            input[type="submit"]:hover {
                background-color: #45a049;
            }
        </style>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/AddParts">
            <table>
                <tbody>
                    <tr>
                        <td>Part ID</td>
                        <td><input type="text" name="partID" value="" /></td>
                    </tr>
                    <tr>
                        <td>Part Name</td>
                        <td><input type="text" name="partName" value="" /></td>
                    </tr>
                    <tr>
                        <td>Purchase Price</td>
                        <td><input type="number" name="purchasePrice" value="" /></td>
                    </tr>
                    <tr>
                        <td>Retail Price</td>
                        <td><input type="number" name="retailPrice" value="" /></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" value="Add Part" /></td>
                    </tr>
                </tbody>
            </table>
        </form>
    </body>
</html>