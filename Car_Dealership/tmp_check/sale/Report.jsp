<%@page import="dtos.TopMechanic"%>
<%@page import="dtos.PartRevenue"%>
<%@page import="dtos.BestSellingCarModel"%>
<%@page import="dtos.CarRevenueByYear"%>
<%@page import="dtos.CarSalesByYear"%>
<%@page import="dtos.SalesPersonDTO"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Car Sales Statistics</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <style>
            body {
                background-color: #f8f9fa;
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            }
            .container {
                margin-top: 30px;
            }
            .card {
                margin-bottom: 25px;
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                transition: transform 0.3s ease;
            }
            .card:hover {
                transform: translateY(-5px);
            }
            table {
                border-radius: 10px;
                overflow: hidden;
            }
            th {
                background: #007bff;
                color: white;
                text-align: center;
                padding: 12px;
            }
            td {
                text-align: center;
                padding: 10px;
            }
            h2, h4 {
                color: #333;
            }
            .card-title {
                font-weight: bold;
                color: #007bff;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2 class="text-center mb-4">Car Sales Statistics</h2>
            <div class="row">
                <div class="col-md-6">
                    <div class="card p-4">
                        <h4 class="text-center card-title">Cars Sold by Year</h4>
                        <table class="table table-striped table-bordered">
                            <thead>
                                <tr><th>Year</th><th>Cars Sold</th></tr>
                            </thead>
                            <tbody>
                                <c:forEach var="sd" items="${salesData}">
                                    <tr>
                                        <td>${sd.year}</td>
                                        <td>${sd.totalSold}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="card p-4">
                        <h4 class="text-center card-title">Revenue from Parts</h4>
                        <table class="table table-striped table-bordered">
                            <thead>
                                <tr><th>Part Name</th><th>Total Revenue (VND)</th></tr>
                            </thead>
                            <tbody>
                                <c:forEach var="pr" items="${partsRevenue}">
                                    <tr>
                                        <td>${pr.partName}</td>
                                        <td>${String.format("%.0f", pr.totalRevenue)}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <div class="card p-4">
                        <h4 class="text-center card-title">Best Selling Car Models</h4>
                        <table class="table table-striped table-bordered">
                            <thead>
                                <tr><th>Model</th><th>Total Sold</th></tr>
                            </thead>
                            <tbody>
                                <c:forEach var="bs" items="${bestSellingCars}">
                                    <tr>
                                        <td>${bs.model}</td>
                                        <td>${bs.totalSold}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="card p-4">
                        <h4 class="text-center card-title">Top 3 Mechanics</h4>
                        <table class="table table-striped table-bordered">
                            <thead>
                                <tr><th>Mechanic ID</th><th>Mechanic Name</th></tr>
                            </thead>
                            <tbody>
                                <c:forEach var="tm" items="${topMechanics}">
                                    <tr>
                                        <td>${tm.mechanicID}</td>
                                        <td>${tm.mechanicName}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <br>
        <br>
    </body>
</html>