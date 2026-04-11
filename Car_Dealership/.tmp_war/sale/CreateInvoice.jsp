<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Create Invoice</title>
    </head>
    <body>
        <h2>Create New Invoice</h2>
        <h2>Create Sales Invoice</h2>

        <form action="${pageContext.request.contextPath}/ListMyInvoiceController" method="post">
            <label for="invoiceID">Invoice ID:</label>
            <input type="text" id="invoiceID" name="invoiceID" required><br>

            <label for="invoiceDate">Invoice Date:</label>
            <input type="text" id="invoiceDate" name="invoiceDate" required><br>

            <label for="salesID">Sales ID:</label>
            <input type="text" id="salesID" name="salesID" required><br>

            <label for="carID">Car ID:</label>
            <input type="text" id="carID" name="carID" required><br>

            <label for="custID">Customer ID:</label>
            <input type="text" id="custID" name="custID" required><br>

            <input type="submit" value="Create Invoice">
        </form>

        <h3 style="color:red;">${message}</h3>
    </body>
</html>
