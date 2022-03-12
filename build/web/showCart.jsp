<%-- 
    Document   : showCart
    Created on : Feb 23, 2022, 10:59:07 AM
    Author     : kiman
--%>

<%@page import="java.util.Map"%>
<%@page import="anhtvk.cart.CartObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book store</title>
    </head>
    <body>
        <c:if test="${not empty param.isGetAll}">
            <a href="getAllProductsAction">Go back to shopping page</a><br/>
        </c:if>
        <c:if test="${not empty param.txtSearchValue}">
            <a href="searchShoppingAction?txtSearchValue=${param.txtSearchValue}">Go back to shopping page</a><br/>
        </c:if>
        <c:if test="${(empty param.txtSearchValue) and (empty param.isGetAll)}">
            <a href="shoppingPage">Go back to shopping page</a><br/>
        </c:if>
        <a href="">Go back to 
            <c:if test="${not empty sessionScope.USERNAME}">
                search
            </c:if>
            <c:if test="${empty sessionScope.USERNAME}">
                login
            </c:if>
             page</a>
        <h1>Your cart includes</h1>
        <c:set var="cart" value="${sessionScope.CART}"/>
        
        <c:if test="${not empty cart}">
            <c:set var="items" value="${cart.items}"/>
            <c:if test="${not empty items}">
                <form action="deleteItemFromCarAction">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Title</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Subtotal</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${items}" varStatus="counter">
                            <tr>
                                <td style="text-align: right;">
                                    ${counter.count}
                                .</td>
                                <td>
                                    ${item.value.name}
                                </td>
                                <td style="text-align: right;">
                                    ${item.value.amount}
                                </td>
                                <td style="text-align: right;">
                                    ${item.value.price}
                                </td>
                                <td style="text-align: right;">
                                    ${item.value.amount * item.value.price}
                                </td>
                                <td>
                                    <input type="checkbox" name="chkItem" value="${item.key}" />
                                </td>
                            </tr>
                        </c:forEach>   
                        <tr>
                            <td colspan="5" style="text-align: right;">
                                Total: <c:out default="0" value="${cart.totalPrice}"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="1">
                                <c:if test="${not empty param.isGetAll}">
                                    <a href="getAllProductsAction">Add more book</a>
                                </c:if>
                                <c:if test="${not empty param.txtSearchValue}">
                                    <a href="searchShoppingAction?txtSearchValue=${param.txtSearchValue}">Add more book</a>
                                </c:if>
                                <c:if test="${(empty param.txtSearchValue) and (empty param.isGetAll)}">
                                    <a href="shoppingPage">Add more book</a>
                                </c:if>
                            </td>
                            <td>
                                <input type="submit" value="Remove Your Choice Item" />
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
            </c:if>
        </c:if>
        
        <c:if test="${empty sessionScope}">
            <h2>No cart!!!</h2>
        </c:if>
    </body>
</html>
