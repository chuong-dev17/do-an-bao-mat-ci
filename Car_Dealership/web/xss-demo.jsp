<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>XSS Demo</title>
</head>
<body>
    <h2>Semgrep XSS Demo Page</h2>

    <form method="get">
        <input type="text" name="q" placeholder="Type payload like <script>alert(1)</script>">
        <button type="submit">Submit</button>
    </form>

    <p>Escaped EL output: <c:out value="${param.q}"/></p>
    <p>Escaped output (no scriptlet): <c:out value="${param.q}"/></p>
</body>
</html>
