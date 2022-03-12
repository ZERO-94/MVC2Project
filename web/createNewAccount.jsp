<%-- 
    Document   : createNewAccount
    Created on : Mar 2, 2022, 11:13:29 AM
    Author     : kiman
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create account</title>
    </head>
    <body>
        <a href="">Go back to login page</a>
        <h1>Create new account</h1>
        <form action="createAccountAction" method="POST">
            <c:set var="errors" value="${requestScope.CREATEERRORS}"/>
            Username: <input type="text" name="txtUsername" value="${param.txtUsername}" />(e.g. 6 - 20 chars)<br/>
            <c:if test="${not empty errors.usernameLengthError}">
                <font color="red">
                    ${errors.usernameLengthError}
                </font><br/>
            </c:if>
            <c:if test="${not empty errors.usernameIsExisted}">
                <font color="red">
                    ${errors.usernameIsExisted}
                </font><br/>
            </c:if>
            Password: <input type="password" name="txtPassword" value="" />(e.g. 6 - 30 chars)<br/>
            <c:if test="${not empty errors.passwordLengthError}">
                <font color="red">
                    ${errors.passwordLengthError}
                </font><br/>
            </c:if>
            Confirm password: <input type="password" name="txtConfirm" value="" />(e.g. 6 - 30 chars)<br/>
            <c:if test="${not empty errors.confirmNotMatched}">
                <font color="red">
                    ${errors.confirmNotMatched}
                </font><br/>
            </c:if>
            Full name: <input type="text" name="txtFullname" value="${param.txtFullname}" />(e.g. 2-50 chars)<br/>
            <c:if test="${not empty errors.fullnameLengthError}">
                <font color="red">
                    ${errors.fullnameLengthError}
                </font><br/>
            </c:if>
            <input type="submit" value="Create new account"/>
        </form>
    </body>
</html>
