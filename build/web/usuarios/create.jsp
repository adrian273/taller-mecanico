<%-- 
    Document   : create
    Created on : 04-12-2019, 16:33:21
    Author     : adrian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<form id="form-user" method="post">
    <input type="hidden" value="store" name="action">
    <div class="mt-8 animated zoomIn" style="margin:100px;">
        <h1>Agregar Nuevo Usuario</h1>
        <div class="row">
            <div class="col">
                <!-- Material input -->
                <div class="md-form">
                    <label for="tipo">Email</label>
                    <input type="email" name="email" id="email" class="form-control">
                </div>
            </div>
            <div class="col">
                <!-- Material input -->
                <div class="md-form">
                    <label for="pass">Contraseña</label>
                    <input value="" type="password" name="pass" id="pass" class="form-control">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <div class="md-form">
                    <label for="nombre">Nombre</label>
                    <input value="" type="text" name="nombre" id="nombre" class="form-control">
                </div>
            </div>
        </div>
        
        <div class="col pt-4">
            <button type="button" onclick="addUser()" id="btn-form-automovil" class="btn btn-primary btn-block">Agregar <i class="fas fa-plus"></i></button>
        </div>
    </div>
</form>