<%-- 
    Document   : shoppingGrid
    Created on : Feb 23, 2022, 9:47:53 AM
    Author     : kiman
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Grid</title>
    </head>
    <style>
        .grid-container {
            width:100%;
            display: flex;
            flex-wrap: wrap;
            background-color: #2196F3;
            padding: 10px;
        }
        .grid-item {
            width: 25%;
            box-sizing: border-box;
            background-color: rgba(255, 255, 255, 0.8);
            border: 1px solid rgba(0, 0, 0, 0.8);
            padding: 20px;
            font-size: 30px;
            text-align: center;
        }
    </style>
    <body>
        
        <a href="">Go back to 
            <c:if test="${not empty sessionScope.USERNAME}">
                search
            </c:if>
            <c:if test="${empty sessionScope.USERNAME}">
                login
            </c:if>
             page</a>
        
        <form action="cartPage">
            <c:if test="${requestScope.ISGETALL}">
                <input type="hidden" name="isGetAll" value="${requestScope.ISGETALL}" />
            </c:if>
            <c:if test="${not empty param.txtSearchValue}">
                <input type="hidden" name="txtSearchValue" value="${param.txtSearchValue}" />
            </c:if>
            <input type="submit" value="View your cart"/>
        </form>
        <form action="searchShoppingAction">
            <input type="text" name="txtSearchValue" value="${param.txtSearchValue}"/>
            <input type="submit" value="Search"/>
        </form>
        <form action="getAllProductsAction">
            <input type="submit" value="View all products"/>
        </form>
        <c:set var="error" value="${requestScope.ERROR}"/>
        <c:if test="${not empty error}">
            <font color="red">
            ${error.overQuantityError}
            </font><br/>
        </c:if>

        <c:set var="products" value="${requestScope.PRODUCTS}"/>
        <c:if test="${not empty products}">
            <div class="grid-container">
                <c:forEach var="product" items="${products}">

                    <div class="grid-item">
                        <form action="addItemToCartAction">
                            <h3>
                                ${product.name}
                                <input type="hidden" name="cboItemName" value="${product.name}" />
                            </h3>
                            <h4>
                                Price: ${product.price}
                                <input type="hidden" name="cboItemPrice" value="${product.price}" />
                            </h4>
                            <input type="hidden" name="cboItemId" value="${product.id}" />
                            <c:if test="${product.quantity le 0}">
                                <font color="red">
                                Sold out
                                </font>
                            </c:if>
                            <c:if test="${product.quantity gt 0}">
                                <h5>In stock: ${product.quantity}</h5>
                                <c:if test="${requestScope.ISGETALL}">
                                    <input type="hidden" name="isGetAll" value="${requestScope.ISGETALL}" />
                                </c:if>
                                <c:if test="${not empty param.txtSearchValue}">
                                    <input type="hidden" name="txtSearchValue" value="${param.txtSearchValue}" />
                                </c:if>
                                <input type="submit" value="Add book to your cart"/>
                            </c:if>
                        </form>
                    </div>
                </c:forEach>
            </div>
        </c:if>
        <c:if test="${not empty param.txtSearchValue}">
            <c:if test="${empty products}">
                <h1>No product Found!</h1>
            </c:if>
        </c:if>
    </body>
</html>
