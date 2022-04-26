<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href=<c:url value="../../../pub/css/style.css"/> rel="stylesheet" type="text/css"/>
    <title>Bounce King </title>

</head>



<div class="container">

<%--    <a href="/index">Bounce King</a> &nbsp; | &nbsp;--%>
<%--    <a href="/shop/products">Castles</a> &nbsp; | &nbsp;--%>
<%--    <a href="/user/register">Sign Up</a> &nbsp; | &nbsp;--%>
<%--    <a href="">Cart</a>--%>

        <h1>
            <a href="/index">Bounce King</a>
        </h1>
        <nav>
            <ul class="navList">
                <li>
                    <a href="/user/register">Sign Up</a>
                </li>
                <li>
                    <a href="/shop/products">Castles</a>
                </li>
                <li>
                    <a href="/shop/checkout">Cart</a>
                </li>
            </ul>
        </nav>

<%--    <sec:authorize access="hasAuthority('ADMIN')">--%>
<%--    &nbsp; | &nbsp;<a href="/user/search">Search</a>--%>
<%--    </sec:authorize>--%>

    <sec:authorize access="!isAuthenticated()">
     <a href="/login/login">Login</a>
    </sec:authorize>

    <sec:authorize access="isAuthenticated()">
     <a href="/login/logout">Logout</a>
    <sec:authentication property="principal.username" />
    </sec:authorize>



    <hr>
