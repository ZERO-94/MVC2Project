<%-- 
    Document   : login
    Created on : Mar 6, 2022, 10:24:43 PM
    Author     : kiman
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login page</title>
    </head>
    <body>
        <h1>Login page</h1>
        <form action="loginAction" method="POST">
            <c:set var="error" value="${param.error}" />
            <c:if test="${not empty error}">
                <font color="red">
                    ${error}
                </font><br/>
            </c:if>
            Username <input type="text" name="txtUsername" value="${param.txtUsername}" /><br/>        
            Password <input type="password" name="txtPassword" value="" /><br/>
            <input type="submit" value="Login"/>
            <input type="reset" value="Reset" />
        </form>
        <br/>
        <a href="shoppingAction">Book Store</a><br/>
        <a href="createAccountPage">Sign Up</a>
    </body>
</html>
