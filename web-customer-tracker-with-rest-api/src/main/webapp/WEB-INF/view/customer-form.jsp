<%-- 
    Document   : customer-form
    Created on : Mar 13, 2022, 11:09:31 PM
    Author     : Dell
--%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
    
    <head>
       <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Save Customer</title>
         <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/add-customer-style.css">
    </head>
    
    <body>
         <div id="wrapper">
            <div id="header">
                 <h2>CRM- CUSTOMER RELATIONSHIP MANAGER</h2>
            </div> 
        </div>
        
        <div id="container">
            <form:form action="saveCustomer" modelAttribute="customer" method="POST">
                <table>
                    <!--Hidden Key--->
                    <form:hidden path="id"/>
                    <tbody>
                        <tr>
                            <td><label>First name:</td>
                            <td><form:input path="firstName"/></td>
                        </tr>
                        
                        <tr>
                            <td><label>Last name:</td>
                            <td><form:input path="lastName"/></td>
                        </tr>
                        
                        <tr>
                            <td><label>Email:</td>
                            <td><form:input path="email"/></td>
                        </tr>
                        
                        <tr>
                            <td><label></td>
                            <td><input type="submit" value="Save" class="save"/></td>
                        </tr>
                      
                    </tbody>
                </table>
            </form:form>
            
            <div style="clear:"></div>
            <p>
                <a href="${pageContext.request.contextPath}/customer/list">Back To List</a>
            </p>    
        </div>
    </body>
</html>
