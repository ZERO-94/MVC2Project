<%-- 
    Document   : updateAccount
    Created on : Mar 12, 2022, 9:26:55 AM
    Author     : kiman
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:set var="updateError" value="${requestScope.UPDATEERRORS}"/>
        <a href="">Go back to search page</a>
        <h2>Update my account</h2>
        <form action="updateAccountAction" method="POST">
            Full name: <input type="text" name="txtFullname" 
                              <c:if test="${(not empty updateError) and (not empty updateError.invalidLastNameLength)}">
                                  value="${updateError.lastFullnameInput}" 
                              </c:if>
                              <c:if test="${(empty updateError) or (empty updateError.invalidLastNameLength)}">
                                  value="${sessionScope.FULLNAME}" 
                              </c:if>
                        />(e.g. 2-50 chars)<br/>
            <c:if test="${not empty updateError}">
                <font color="red">${updateError.invalidLastNameLength}</font><br/>
            </c:if>
            <input type="hidden" name="txtUsername" value="${sessionScope.USERNAME}" />
            <input type="submit" value="Update account" name="btAction"/>
        </form>    
        
        <h2>I want to change my password</h2>
        <c:set var="changePasswordError" value="${requestScope.CHANGEPASSWORDERRORS}"/>
        <form action="updateAccountAction" method="POST">
            Input your old password: <input type="password" name="txtOldPassword" value="" />(e.g. 6 - 30 chars)<br/>
            <c:if test="${not empty changePasswordError}">
                <font color="red">${changePasswordError.invalidOldPassword}</font><br/>
            </c:if>
            New password: <input type="password" name="txtPassword" value="" />(e.g. 6 - 30 chars)<br/>
            <c:if test="${not empty changePasswordError}">
                <font color="red">${changePasswordError.passwordLengthError}</font><br/>
            </c:if>
            Confirm new password: <input type="password" name="txtConfirm" value="" />(e.g. 6 - 30 chars)<br/>
            <c:if test="${not empty changePasswordError}">
                <font color="red">${changePasswordError.confirmNotMatched}</font><br/>
            </c:if>
            <input type="hidden" name="txtUsername" value="${sessionScope.USERNAME}" />
            <input type="submit" value="Change password" name="btAction"/>
        </form> 
    </body>
</html>
