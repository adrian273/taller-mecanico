<%-- 
    Document   : main
    Created on : 03-12-2019, 10:39:47
    Author     : adrian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="nav flex-column grey lighten-4 py-4 font-weight-bold">
    <a class="nav-link border-link sub-main peach-gradient" href="#" onclick="openData(this, 'addNewLog')">Nuevo <i class="fas fa-pencil-ruler"></i></a>
    <a class="nav-link border-link sub-main" href="#" id="view-auto" onclick="openData(this, 'myModalLog')"
       data-toggle="tooltip" title="Presionar Dos Veces (si no carga informacion)" data-placement="bottom">Mostrar <i class="far fa-list-alt"></i></a>
</nav>


<script>
    $(function () {
        $('[data-toggle="tooltip"]').tooltip();
    })
</script>