<%-- 
    Document   : header
    Created on : 02-12-2019, 16:18:23
    Author     : adrian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<%
    String path = request.getContextPath();
%>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="<%= path%>/assets/mdb/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="<%= path%>/assets/mdb/css/bootstrap.min.css"/>

        <link rel="stylesheet" href="<%= path%>/assets/css/style.css"/>
        <link rel="stylesheet" href="<%= path%>/assets/css/animated.css"/>
        <link rel="stylesheet" href="<%= path%>/assets/node_modules/sweetalert2/dist/sweetalert2.css"/>
        <script src="https://kit.fontawesome.com/e53674a86e.js" crossorigin="anonymous"></script>
        <title>Taller Mecanico</title>
    </head>
    <body>
        <input type="hidden" id="base" value="<%= request.getContextPath()%>">
        <header>
            <jsp:include page="../layouts/nav-bar.jsp"></jsp:include>
            <jsp:include page="../layouts/content.jsp"></jsp:include>
        </header>

