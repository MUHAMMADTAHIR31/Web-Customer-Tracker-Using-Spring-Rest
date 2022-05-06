<%-- 
    Document   : index
    Created on : May 5, 2022, 1:59:06 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <hr>
        <a href="${pageContext.request.contextPath}/api/customers">Get ALL Customers </a>
    </body>
</html>
