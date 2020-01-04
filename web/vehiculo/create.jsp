<%-- 
    Document   : create
    Created on : 03-12-2019, 0:15:08
    Author     : adrian
--%>
<form id="form-automovil" method="post">
    <input type="hidden" value="addAuto" name="action">
    <div class="mt-8 animated zoomIn" style="margin:100px;">
        <h1>Agregar Nuevo Automovil</h1>
        <div class="row">
            <div class="col">
                <!-- Material input -->
                <div class="md-form">
                    <label for="tipo">Tipo</label>
                    <select name="tipo" id="tipo" class="form-control" required="required">
                        <option value=""></option>
                        <option value="1">Auto</option>
                        <option value="2">Camion</option>
                        <option value="3">Camioneta</option>
                        <option value="4">Moto</option>
                    </select>
                </div>
            </div>
            <div class="col">
                <!-- Material input -->
                <div class="md-form">
                    <label for="num_chasis">Numero Chasis</label>
                    <input value="" maxlength="11" type="number" name="num_chasis" id="num_chasis" class="form-control">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <!-- Material input -->
                <div class="md-form">
                    <label for="marca">Marca</label>
                    <input value="" type="text" id="marca" name="marca" class="form-control">
                </div>
            </div>
            <div class="col">
                <!-- Material input -->
                <div class="md-form">
                    <label for="modelo">Modelo</label>
                    <input value="" type="text" id="modelo" name="modelo" class="form-control">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-6">
                <!-- Material input -->
                <div class="md-form">
                    <label for="color">Color</label>
                    <select name="color" id="color" class="form-control">
                        <option value=""></option>
                        <option value="rojo">Rojo</option>
                        <option value="azul">Azul</option>
                        <option value="negro">Negro</option>
                        <option value="amarillo">Amarillo</option>
                        <option value="verde">Verde</option>
                    </select>
                </div>
            </div>
            <div class="col-6">
                <div class="md-form">
                    <label for="patente">Patente (sin guiones: ej BKXZ83)</label>
                    <input value="" type="text" id="patente" name="patente" maxlength="6" min="6" class="form-control">
                </div>
            </div>
        </div>
        <div class="col pt-4">
            <button type="button" onclick="add()" id="btn-form-automovil" class="btn btn-primary btn-block">Agregar <i class="fas fa-plus"></i></button>
        </div>
    </div>
</form>