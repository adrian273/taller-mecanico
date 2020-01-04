<%-- 
    Document   : index
    Created on : 04-12-2019, 16:33:05
    Author     : adrian
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h4 class="text-right">Lista de Usuarios <small>(presionar celdas para ver info)</small></h4>
<div class="table-fixed animated zoomIn">
    <table id="table-log" class="table table-striped table-hover" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th class="th-sm">Id
                </th>
                <th class="th-sm">Email
                </th>
                <th class="th-sm">Contraseña
                </th>
                <th class="th-sm">Nombre
                </th>
            </tr>
        </thead>
        <tbody id="result-user">
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
<div class="modal fade" id="userModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true" >
    <div class="modal-dialog modal-fluid" role="document">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <h5 class="modal-title" id="exampleModalLabel">Edicion de <span class="nombre-user"></span></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>

            </div>
            <form id="form-update-user">
                <div class="modal-body" id="modal-data-user" style="color:black;">

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar <i class="far fa-window-close"></i></button>
                    <button type="button" onclick="updateUser()" class="btn btn-primary">Guardar Cambios <i class="far fa-save"></i></button>
                </div>
                </footer>
            </form>
        </div>
    </div>
</div>