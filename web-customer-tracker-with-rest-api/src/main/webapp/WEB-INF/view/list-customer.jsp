<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>List Customer</title>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
    </head>
    <body>
    
    <div id="wrapper">
        <div id="header">
            <h2>C R M- CUSTOMER RELATIONSHIP MANAGER</h2>
        </div> 
    </div>
    
    <div id="container">
        <div id="content">
            <input type="button" value="Add Customer" onclick="window.location.href='showFormForAdd'; return false;" class="add-button">  
            <!-- add out html -->
            <table>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Action</th>
                </tr>
                
                <!--loop over and print our customer-->
                <c:forEach var="tempCustomer" items="${customers}">
                    
                     <!--Update URL -->
                    <c:url var="updateLink" value="/customer/showFormForUpdate">
                        <c:param name="customerId" value="${tempCustomer.id}"/>    
                    </c:url>  
                    
                    <!--Delete URL -->
                     <c:url var="deleteLink" value="/customer/delete">
                        <c:param name="customerId" value="${tempCustomer.id}"/>    
                    </c:url>  
                                       
                    <tr>
                        <td>${tempCustomer.firstName}</td>
                        <td>${tempCustomer.lastName}</td>
                        <td>${tempCustomer.email}</td>
                        
                        <td>
                            <a href="${updateLink}">Update</a>
                            |
                            <a href="${deleteLink}" onclick="if(!(confirm('Are you sure you want to delete this customer')))return false">Delete</a>
                        </td>   
                        
                    </tr>
                </c:forEach>   
            </table>
            
            <br/><br/>
            <!--PDF URL
            <div>
                <a href="" class="btn btn-success">Export data into PDF  <i class="fa fa-file-pdf-o" aria-hidden="true"></i></a>  -->
             <input type="button" value="Download PDF " onclick="window.location.href='pdf'; return false;" class="pdf-button"> 
            </div><br/>
        </div>
    </div>   
</body>
</html>