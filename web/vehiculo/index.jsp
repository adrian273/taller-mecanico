<%-- 
    Document   : index
    Created on : 02-12-2019, 16:23:59
    Author     : adrian
--%>
<h4 class="text-right">Lista de Automoviles <small>(presionar celdas para ver info)</small></h4>

<div class="table-fixed animated zoomIn">
    <table id="table-automovil" class="table table-striped table-hover" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th class="th-sm">Patente
                </th>
                <th class="th-sm">Tipo
                </th>
                <th class="th-sm">N° Chasis
                </th>
                <th class="th-sm">Marca
                </th>
                <th class="th-sm">Modelo
                </th>
                <th class="th-sm">Color
                </th>
            </tr>
        </thead>
        <tbody id="result-automovil">
            <tr>
                <td colspan="6">
                   Resultados no cargados <i class="far fa-dizzy fa-2x"></i>, 
                    presione boton " Mostrar <i class="far fa-list-alt"></i> " nuevamente...
                </td>
            </tr>
        </tbody>

    </table>
</div>

<!-- Modal -->
<div class="modal fade" id="basicExampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true" >
    <div class="modal-dialog modal-fluid" role="document">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <h5 class="modal-title" id="exampleModalLabel">Edicion de <span class="modelo-marca"></span></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    Patente: <span class="patente"></span>
                    <span aria-hidden="true">&times;</span>
                </button>

            </div>
            <form id="form-update-vehiculo">
                <div class="modal-body" id="modal-data" style="color:black;">

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar <i class="far fa-window-close"></i></button>
                    <button type="button" onclick="updateAuto()" class="btn btn-primary">Guardar Cambios <i class="far fa-save"></i></button>
                </div>
                </footer>
            </form>
        </div>
    </div>
</div>



<!-- Modal Auto LOg -->
<div class="modal fade" id="modalAutoLog" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true" >
    <div class="modal-dialog modal-fluid" role="document">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <h5 class="modal-title" id="exampleModalLabel">Lista de Registro <span class="modelo-marca"></span></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    Patente: <span class="patente"></span>
                    <span aria-hidden="true">&times;</span>
                </button>

            </div>
            <div class="modal-body" id="modal-data-log-auto" style="color:black;">
                <table id="table-log-auto" class="table table-striped table-hover" cellspacing="0" width="100%">
                    <thead class="bg-primary">
                        <tr>
                            <th class="th-sm">Id
                            </th>
                            <th class="th-sm">Fecha
                            </th>
                            <th class="th-sm">Tipo
                            </th>
                            <th class="th-sm">Descripcion
                            </th>
                            <th class="th-sm">Costo
                            </th>

                        </tr>
                    </thead>
                    <tbody id="result-log-auto">

                    </tbody>

                </table>
                <b>Total Registros: <span class="count-log-auto"></span></b>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar <i class="far fa-window-close"></i></button>
            </div>
            </footer>
        </div>
    </div>
</div>