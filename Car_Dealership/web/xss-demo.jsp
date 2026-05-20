<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>XSS Demo</title>
</head>
<body>
    <h2>Semgrep XSS Demo Page</h2>

    <%
        String username = request.getParameter("username");
    %>

<p>Welcome, <c:out value="${fn:escapeXml(param.username)}" /></p>    
</body>
</html>