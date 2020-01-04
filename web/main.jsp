<%-- 
    Document   : main
    Created on : 02-12-2019, 22:37:09
    Author     : adrian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="nav flex-column grey lighten-4 py-4 font-weight-bold">
    <a class="nav-link border-link main btn-active" href="#" id="main-auto" onclick="selectMain(this, 'home')">
        Home <i class="fas fa-home"></i>
    </a>
    <a class="nav-link border-link main" href="#" id="main-auto" onclick="selectMain(this, 'automovil')">
        Automovil <i class="fas fa-truck-pickup"></i>
    </a>
    <a class="nav-link border-link main" href="#" onclick="selectMain(this, 'registros')">
        Registros <i class="far fa-list-alt"></i>
    </a>
    <a class="nav-link border-link main" href="#" onclick="selectMain(this, 'usuarios')">
        Usuarios <i class="far fa-user"></i>
    </a>
    <a class="nav-link border-link main" href="login?action=logout">
        Cerrar Session <i class="far fa-list-alt"></i>
    </a>
</nav>
