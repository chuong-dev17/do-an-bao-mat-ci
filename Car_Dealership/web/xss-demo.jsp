<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

    <p>Unescaped EL output: ${param.q}</p>
    <p>Unescaped scriptlet output: <%= request.getParameter("q") %></p>
</body>
</html>
