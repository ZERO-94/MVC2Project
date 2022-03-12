<%-- 
    Document   : search
    Created on : Feb 11, 2022, 11:24:14 AM
    Author     : kiman
--%>

<%--<%@page import="java.util.List"%>
<%@page import="anhtvk.registration.RegistrationDTO"%>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <a href="shoppingPage">Book Store</a><br/>
        <form action="logoutAction">
            <input type="submit" value="Logout" />
        </form>
        <font>

        Welcome, ${sessionScope.FULLNAME}

        <h1>Search Page</h1>
        <form action="updateAccountPage">
            <input type="submit" value="Update my account"/>
        </form>
        <form action="searchLastnameAction">
            Search <input type="text" name="txtSearchValue" 
                          value="${param.txtSearchValue}" /><br/>
            <input type="submit" value="Search"/>
        </form>

        <c:set var="error" value="${requestScope.ERRORS}"/>
        <c:if test="${not empty error}" >
            <font color="red">${error.invalidPasswordLength}</font>
            <font color="red">${error.invalidUpdateUser}</font>
            <font color="red">${error.invalidDeleteUser}</font>
        </c:if>

        <c:set var="searchValue" value="${param.txtSearchValue}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCHRESULT}"/>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Full name</th>
                                <c:if test="${sessionScope.ROLE eq true}">
                                <th>Role</th>
                                </c:if>
                                <c:if test="${sessionScope.ROLE eq true}">
                                <th>Update</th>
                                <th>Delete</th>
                                </c:if>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="updateAccountAction" method="POST">
                            <tr>
                                <td style="text-align: right;">
                                    ${counter.count}
                                    .</td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername" value="${dto.username}" />
                                </td>
                                <td>
                                    ${dto.fullname}
                                </td>
                                <c:if test="${sessionScope.ROLE eq true}">
                                    <td>

                                        <input type="checkbox" name="chkAdmin" value="ON" 
                                               <c:if test="${dto.role}">
                                                   checked="checked"
                                               </c:if>
                                               />
                                    </td>
                                </c:if>

                                <c:if test="${sessionScope.ROLE eq true}">
                                    <td>
                                        <input type="hidden" name="lastSearchValue" value="${param.txtSearchValue}" />
                                        <input type="submit" value="Update" name="btAction"/>
                                    </td>
                                    <td>
                                        <c:if test="${(dto.role ne true) and (sessionScope.USERNAME ne dto.username)}">
                                            <c:url var="deleteAccount" value="deleteAccountAction">
                                                <c:param name="pk" value="${dto.username}"/>
                                                <c:param name="lastSearchValue" value="${param.txtSearchValue}"/>
                                            </c:url>
                                            <a href="${deleteAccount}">Delete</a>
                                        </c:if>
                                    </td>
                                </c:if>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>
        <c:if test="${empty result}">
            <h2>
                No record
            </h2>
        </c:if>
    </c:if>
    <%--
    <% 
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            Cookie lastCookie = cookies[cookies.length - 1];
            String username = lastCookie.getName();
        
        %>
        <h1>Welcome, <%= username %></h1>
        <%
        }
    %>
    <form action="DispatchServlet">
        Search <input type="text" name="txtSearchValue" 
                      value="<%= request.getParameter("txtSearchValue") %>" /><br/>
        <input type="submit" value="Search" name="btAction"/>
    </form><br/>
    
    <% 
        String searchValue = request.getParameter("txtSearchValue");
        if(searchValue != null) {
            List<RegistrationDTO> result = (List<RegistrationDTO>) request.getAttribute("SEARCHRESULT");
            if(result != null) {
                %>  
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full name</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% 
                            int count = 0;
                            for(RegistrationDTO dto : result) {
                                String urlRewriting = "DispatchServlet" 
                                        + "?btAction=Delete"
                                        + "&pk=" + dto.getUsername()
                                        + "&lastSearchValue=" + searchValue;
                                %> 
                    <form action="DispatchServlet" method="POST">
                        <tr>
                            <td>
                                <%= ++count %>
                            .</th>
                            <td>
                                <%= dto.getUsername() %>
                                <input type="hidden" name="txtUsername"
                                       value="<%= dto.getUsername() %>"/>
                            </td>
                            <td>
                                <input type="text" name="txtPassword"
                                       value="<%= dto.getPassword() %>"/>
                            </td>
                            <td>
                                <%= dto.getFullname() %>
                            </td>
                            <td>
                                <input type="checkbox" name="chkAdmin"
                                       value="ON"
                                       <% 
                                       if(dto.isRole()) {
                                           %>
                                           checked="checked"
                                           <%
                                       } //this is admin
                                       %>
                                       />
                            </td>
                            <td>
                                <a href="<%= urlRewriting %>">Delete</a>
                            </td>
                            <td>
                                <input type="hidden" name="lastSearchValue" 
                                       value="<%= searchValue %>" />
                                <input type="submit" value="Update" name="btAction"/>
                            </td>
                        </tr>  
                    </form>

                            <%
                                }//end of process result
                            %>
                        
                        </tbody>
                    </table>

        <%
                } else {
                    %>  
                    <h2>
                        No record is matched!!!!
                    </h2>
        <%
                }
            }//search value
        %>
    --%>
</body>
</html>
