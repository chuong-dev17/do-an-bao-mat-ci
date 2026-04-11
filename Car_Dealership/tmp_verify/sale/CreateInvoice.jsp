<%@page import="dtos.CustomerDTO"%>
<%@page import="daos.CustomerDAO"%>
<%@page import="dtos.CarsDTO"%>
<%@page import="daos.CarsDAO"%>
<%@page import="dtos.SalesPersonDTO"%>
<%@page import="java.util.List"%>
<%@page import="daos.SalesPersonDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Invoice</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
        }
        
        header {
            background-color: #2c3e50;
            color: #fff;
            padding: 20px;
            text-align: center;
        }

        h2 {
            margin-top: 0;
        }

        .container {
            max-width: 800px;
            margin: 50px auto;
            background-color: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        label {
            font-size: 16px;
            margin-bottom: 8px;
            display: block;
        }

        input, select {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 16px;
        }

        input[type="submit"] {
            background-color: #3498db;
            color: #fff;
            border: none;
            cursor: pointer;
            font-size: 18px;
        }

        input[type="submit"]:hover {
            background-color: #2980b9;
        }

        .message {
            color: red;
            text-align: center;
            font-size: 18px;
            font-weight: bold;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group input, .form-group select {
            width: calc(100% - 22px);
            display: inline-block;
            margin-right: 10px;
        }

        .form-group label {
            margin-right: 15px;
            display: inline-block;
            width: 150px;
        }
    </style>
</head>
<body>
<div class="container">
    <form action="${pageContext.request.contextPath}/CreateInvoiceController" method="post">
        <div class="form-group">
            <label for="invoiceID">Invoice ID:</label>
            <input type="text" id="invoiceID" name="invoiceID" required>
        </div>

        <div class="form-group">
            <label for="invoiceDate">Invoice Date:</label>
            <input type="date" id="invoiceDate" name="invoiceDate" required>
        </div>

        <div class="form-group">
            <label for="salesID">Sales ID:</label>
            <input type="text" name="salesID" value="${USER.salesID}" readonly>
        </div>

        <div class="form-group">
            <label for="carID">Car ID:</label>
            <select name="carID" id="carID" required>
                <option value="">-- Select Car ID --</option>
                <%
                    CarsDAO carsDAO = new CarsDAO();
                    List<CarsDTO> carsList = carsDAO.getList();
                    for (CarsDTO car : carsList) {
                %>
                    <option value="<%= car.getCarID() %>"><%= car.getCarID() %> - <%= car.getModel() %> - <%= car.getColour() %> - <%= car.getYear() %></option>
                <%
                    }
                %>
            </select>
        </div>

        <div class="form-group">
            <label for="custID">Customer ID:</label>
            <select name="custID" id="custID" required>
                <option value="">-- Select Customer ID --</option>
                <%
                    CustomerDAO customerDAO = new CustomerDAO();
                    List<CustomerDTO> customerList = customerDAO.getList();
                    
                    for (CustomerDTO customer : customerList) {
                %>
                    <option value="<%= customer.getCustID() %>"><%= customer.getCustID() %> - <%= customer.getCustName() %></option>
                <%
                    }
                %>
            </select>
        </div>

        <div class="form-group">
            <input type="submit" value="Create Invoice">
        </div>
    </form>

    <div class="message">
        <h3>${message}</h3>
    </div>
</div>

</body>
</html>
