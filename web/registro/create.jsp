<%-- 
    Document   : create
    Created on : 03-12-2019, 10:39:56
    Author     : adrian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<form id="form-registro" method="post">
    <input type="hidden" value="store" name="action">
    <div class="mt-8 animated zoomIn" style="margin:100px;">
        <h1>Agregar Nuevo Registro</h1>
        <div class="row">
            <div class="col">
                <!-- Material input -->
                <div class="md-form">
                    <label for="tipo">Tipo</label>
                    <select name="tipo" id="tipo" class="form-control" required="required">
                        <option value=""></option>
                        <option value="5">Mantencion</option>
                        <option value="6">Reparacion Mayor</option>
                        <option value="7">Reparacion Menor</option>
                    </select>
                </div>
            </div>
            <div class="col">
                <!-- Material input -->
                <div class="md-form">
                    <label for="fecha">Fecha</label>
                    <input value="" type="date" name="fecha" id="fecha" class="form-control">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <!-- Material input -->
                <div class="md-form">
                    <label for="marca">Descripcion</label>
                    <textarea name="descripcion" id="" cols="30" rows="10" class="form-control" style="margin-top: 0px; margin-bottom: 0px; height: 53px;"></textarea>
                </div>
            </div>
            <div class="col">
                <!-- Material input -->
                <div class="md-form">
                    <label for="costo">costo (sin puntos ej: 1500)</label>
                    <input value="" type="number" id="costo" name="costo" class="form-control">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-6">
                <div class="md-form">
                    <label for="patente">Patente (sin guiones: ej kdrs01 o KDRS01)</label>
                    <input value="" type="text" onkeyup="searchPatenteData(this)" id="patente" name="patente" maxlength="6" min="6" class="form-control">
                </div>
            </div>
            <div class="col-6" id="captionPatente">
                
            </div>
        </div>
        <div class="col pt-4">
            <button type="button" onclick="addLog()" id="btn-form-automovil" class="btn btn-primary btn-block">Agregar <i class="fas fa-plus"></i></button>
        </div>
    </div>
</form>