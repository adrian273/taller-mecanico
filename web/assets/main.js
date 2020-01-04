
/* Adrian Verdugo <github: adrian273> */


const BASE = $("#base").val();

const data_tipo = [
    {id: 1, valor: "auto"},
    {id: 2, valor: "camion"},
    {id: 3, valor: 'camioneta'},
    {id: 4, valor: 'moto'}
];

const data_tipo_log = [
    {id: 5, valor: "Mantencion"},
    {id: 6, valor: "Reparacion Mayor"},
    {id: 7, valor: 'Reparacion Menor'}
];

const color = [
    "rojo", "azul",
    "negro", "amarillo",
    "verde"
];

$(document).ready(function () {
    $("#menu-app").load(BASE + "/main.jsp");
    $("#app").load(BASE + "/home.jsp");

});

/**
 * 
 * @param {this} that
 * @param {string} name
 * @returns {seleccion de menus}
 */
function selectMain(that, name) {
    $(".main").removeClass("btn-active");
    $(that).addClass('btn-active');
    $("#menu-app").removeClass("fadeInLeft");
    $("#submenu-app").removeClass("fadeInRight");
    $("#submenu-app").removeClass("fadeInUp");
    switch (name) {
        case "automovil":
            $("#submenu-app").addClass("fadeInRight");
            $("#submenu-app").load(BASE + "/vehiculo/main.jsp");
            $("#submenu-app").html("");
            break;
        case "home":
            $("#submenu-app").html("");
            $("#app").load(BASE + "/home.jsp");
            break;
        case "registros":
            $("#submenu-app").addClass("fadeInLeft");
            $("#submenu-app").load(BASE + "/registro/main.jsp");
            break;
        case "usuarios":
            $("#submenu-app").addClass("fadeInUp");
            $("#submenu-app").load(BASE + "/usuarios/main.jsp");
            break;
        default:
            break;
    }
}

/**
 * 
 * @param {this} that
 * @param {number} ident
 * @returns {tipo de evento a cargar}
 */
function openData(that, ident) {
    $(".sub-main").removeClass("btn-active");
    $(that).addClass('btn-active');
    switch (ident) {
        case "myModalAutomovil":
            automovil();
            break;
        case "addNewAutomovil":
            addAutomovilView();
            break;
        case "addNewLog":
            addNewLogView();
            break;
        case "myModalLog":
            log();
            break;
        case "addNewUser":
            addNewUser();
            break;
        case "myModalUser":
            user();
    }
}

// Users

function addNewUser() {
    $("#app").load(BASE + "/usuarios/create.jsp");
}


function user() {
    $.ajax({
        url: BASE + "/user",
        data: {
            action: "user-data"
        },
        type: "get",
        dataType: "json"
    }).done(function (res) {
        console.log(res);
        //$("#result-automovil").html("");
        var template = "";
        for (let i in res) {
            template += `
                <tr id="index-${res[i].id}" onclick="viewDataUser(${res[i].id})" class='table-pointer'>
                    <td class="index-id">${(res[i].id)} <span class="msg-updated"></span></td>
                    <td class="index-email">${res[i].email}</td>
                    <td class="index-pass">${res[i].pass}</td>
                    <td class="index-nombre">${res[i].nombre}</td>
                </tr>
            `;
            //$("#result-automovil").append(template);
        }
        $("#result-user").html(template);
    }).fail(function (jqXHR, txtStatus, error) {
        console.log(txtStatus + " " + error);
    });
    $("#app").load(BASE + "/usuarios/index.jsp");
}

function addUser() {
    var validation = false;
    var errors = "";
    if ($("#email").val() === "") {
        validation = true;
        errors += `El email es requerido <br>`;
    }
    if ($("#nombre").val() === "") {
        validation = true;
        errors += `El nombre es requerido <br>`;
    }
    if ($("#pass").val() === "") {
        validation = true;
        errors += `La contraseña es requerida <br>`;
    }

    if (validation === false) {
        $.ajax({
            url: `${BASE}/user`,
            data: $("#form-user").serialize(),
            type: 'post',
            dataType: 'json'
        }).done(function (res) {
            console.log(res);
            if (res.type === "success") {
                $("#form-user").trigger('reset');
                Swal.fire({
                    icon: 'success',
                    title: 'Agregado con Exito',
                    text: 'se agrego un nuevo automovil'
                });
            }
        }).fail(function (jqXHR, txt, error) {
            console.log(txt + "-->" + error);
            if (txt === "parsererror") {
                Swal.fire({
                    icon: 'error',
                    title: `${txt}`,
                    html: `Error Al ingresar datos : ${error}`
                });
            }
        });
    } else {
        Swal.fire({
            icon: 'error',
            title: 'Datos requeridos',
            html: `${errors}`
        });
    }
}

function viewDataUser(id) {
    if (id !== undefined) {
        $.ajax({
            url: `${BASE}/user`,
            data: {
                action: 'one-data',
                id: id
            },
            type: 'get',
            dataType: 'json'
        }).done(function (res) {
            template = `
                <div class="row">             
                    <input type="hidden" name="action" id="action" value="update">
                     <input type="hidden" name="id_user" id="id_user" value="${res.id}">
                <button onclick="deleteUser('${res.id}'); return false;" class="btn btn-danger">Eliminar </button>
</div>
                <div class="row">
            <div class="col">
                <!-- Material input -->
                <div class="md-form">
                    <label for="tipo">Email</label>
                    <input type="email" name="email" value="${res.email}" id="email" class="form-control">
                </div>
            </div>
            <div class="col">
                <!-- Material input -->
                <div class="md-form">
                    <label for="pass">Contraseña</label>
                    <input value="${res.pass}" type="password" name="pass" id="pass" class="form-control">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <div class="md-form">
                    <label for="nombre">Nombre</label>
                    <input value="${res.nombre}" type="text" name="nombre" id="nombre" class="form-control">
                </div>
            </div>
        </div>
            `;
            $("#modal-data-user").html(template);
            $(".nombre-user").html(res.nombre);
        }).fail(function (jqXHR, txt, error) {
            console.log(txt + "-->" + error);
        });
    }
    $("#userModal").modal();
}

function deleteUser(id) {
    Swal.fire({
        title: 'Estas seguro?',
        text: "De eliminar este usuario",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Si, borrarlo <i class="far fa-trash-alt"></i>',
        cancelButtonText: 'Cancelar <i class="far fa-window-close"></i>'

    }).then((result) => {
        if (result.value) {
            $.ajax({
                url: `${BASE}/user`,
                data: {
                    action: "delete",
                    id: id
                },
                type: 'post',
                dataType: 'json'
            }).done(function (res) {
                if (res.type === "success") {
                    Swal.fire(
                            'Eliminado',
                            'Se elimino con exito!',
                            'success'
                            );
                    $("#userModal").modal("hide");
                    $("#index-" + id).remove();
                }
            }).fail(function (jqXHR, txt, error) {
                Swal.fire(
                        `${txt}`,
                        `${error}`,
                        'error'
                        );
            });

        }
    });
}

function updateUser() {
    Swal.fire({
        title: 'Estas seguro',
        text: "de editar este usuario?",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Si, editar nomas <i class="far fa-save"></i>',
        cancelButtonText: 'Cancelar <i class="far fa-window-close"></i>'
    }).then((result) => {
        if (result.value) {
            $.ajax({
                url: `${BASE}/user`,
                data: $("#form-update-user").serialize(),
                type: 'post',
                dataType: 'json'
            }).done(function (res) {
                console.log(res)
                if (res.type === "success") {
                    var ident = $("#id_user").val();
                    $(`#index-${ident} .index-id`).html(`${ident}<span><small class="badge badge-success">
                        <i class="fas fa-check"> Edicion Reciente</i>
                    </small></span>`);
                    $(`#index-${ident} .index-id`).html(res.id);
                    $(`#index-${ident} .index-nombre`).html(res.nombre);
                    $(`#index-${ident} .index-email`).html(res.email);
                    $(`#index-${ident} .index-pass`).html(res.pass);
                    Swal.fire(
                            'Editado',
                            'Se actualizo con exito!',
                            'success'
                            );
                }
            }).fail(function (jqXHR, txt, error) {
                Swal.fire(
                        `${txt}`,
                        `${error}`,
                        'error'
                        );
            });

        }
    });
}

//------------------------------------------------------------------------------
/**
 * 
 * @returns {vista de datos de todos lo automoviles}
 */
function automovil() {
    $.ajax({
        url: BASE + "/automovil",
        data: {
            action: "automovil-data"
        },
        type: "get",
        dataType: "json"
    }).done(function (res) {
        console.log(res);
        //$("#result-automovil").html("");
        var template = "";
        for (let i in res) {
            template += `
                <tr id="index-${res[i].id}" onclick="viewData(${res[i].id})" class='table-pointer'>
                    <td class="index-id">${formatoPatente(res[i].patente)} <span class="msg-updated"></span></td>
                    <td class="index-tipos">${res[i].tipos}</td>
                    <td class="index-num-chasis">${res[i].num_chasis}</td>
                    <td class="index-marca">${res[i].marca}</td>
                    <td class="index-modelo">${res[i].modelo}</td>  
                    <td class="index-color">${res[i].color}</td>
                </tr>
            `;
            //$("#result-automovil").append(template);
        }
        $("#result-automovil").html(template);
    }).fail(function (jqXHR, txtStatus, error) {
        console.log(txtStatus + " " + error);
    });
    $("#app").load(BASE + "/vehiculo/index.jsp");
}

/**
 * 
 * @param {type} id
 * @returns {vista de datos por identificador}
 */
function viewData(id) {
    if (id !== undefined) {
        $.ajax({
            url: `${BASE}/automovil`,
            data: {
                action: 'one-data',
                id: id
            },
            type: 'get',
            dataType: 'json'
        }).done(function (res) {
            console.log(res);
            $("#modal-data").html(__template(res));
            $(".modelo-marca").html(`"${res.marca.toString().toLocaleLowerCase()} ${res.modelo.toString().toLowerCase()}"`);
            $(".patente").html(formatoPatente(res.patente));
        }).fail(function (jqXHR, txt, error) {
            console.log(txt + "-->" + error);
        });
    }
    $("#basicExampleModal").modal();
}

/**
 * 
 * @returns {vista de agregar nuevo automovil}
 */
function addAutomovilView() {
    $("#app").load(BASE + "/vehiculo/create.jsp");
}

/**
 * 
 * @returns {nuevo registro de auto}
 */
function add() {
    var tipo = $("#tipo").val();
    var validation = false;
    var errors = "";
    if (tipo === "") {
        validation = true;
        errors += `El Tipo de auto es requerido <br>`;
    }
    if ($("#num_chasis").val() === "") {
        validation = true;
        errors += `El Numero de Chasis es requerido <br>`;
    }
    if ($("#modelo").val() === "") {
        validation = true;
        errors += `El Modelo de auto es requerido <br>`;
    }
    if ($("#marca").val() === "") {
        validation = true;
        errors += `La marca de auto es requerida <br>`;
    }
    if ($("#patente").val() === "" || $("#patente").val().length < 6) {
        validation = true;
        errors += `La patente del auto es requerida o le faltan caracteres <br>`;
    }

    console.log(verificarPatente($("#patente").val()));

    if (verificarPatente($("#patente").val())) {
        validation = true;
        errors += `<b> Esta patente ya esta registrada </b><br>`;
    }
    if (validation === false) {
        $.ajax({
            url: `${BASE}/automovil`,
            data: $("#form-automovil").serialize(),
            type: 'post',
            dataType: 'json'
        }).done(function (res) {
            console.log(res);
            if (res.type === "success") {
                $("#form-automovil").trigger('reset');
                Swal.fire({
                    icon: 'success',
                    title: 'Agregado con Exito',
                    text: 'se agrego un nuevo automovil'
                });
            }
        }).fail(function (jqXHR, txt, error) {
            console.log(txt + "-->" + error);
            if (txt === "parsererror") {
                Swal.fire({
                    icon: 'error',
                    title: `${txt}`,
                    html: `Error Al ingresar datos : ${error}`
                });
            }
        });
    } else {
        Swal.fire({
            icon: 'error',
            title: 'Datos requeridos',
            html: `${errors}`
        });
    }
}

/**
 * 
 * @param {type} id
 * @returns {Boolean}
 */
function deleteAuto(id) {
    Swal.fire({
        title: 'Estas seguro?',
        text: "Puede que tenga registros asociados y tambien se eliminaran",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Si, borrarlo <i class="far fa-trash-alt"></i>',
        cancelButtonText: 'Cancelar <i class="far fa-window-close"></i>'

    }).then((result) => {
        if (result.value) {
            $.ajax({
                url: `${BASE}/automovil`,
                data: {
                    action: "delete",
                    id: id
                },
                type: 'post',
                dataType: 'json'
            }).done(function (res) {
                if (res.type === "success") {
                    Swal.fire(
                            'Eliminado',
                            'Se elimino con exito!',
                            'success'
                            );
                    $("#basicExampleModal").modal("hide");
                    $("#index-" + id).remove();
                }
            }).fail(function (jqXHR, txt, error) {
                Swal.fire(
                        `${txt}`,
                        `${error}`,
                        'error'
                        );
            });

        }
    });
}

function updateAuto() {
    Swal.fire({
        title: 'Estas seguro',
        text: "de editar este automovil?",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Si, editar nomas <i class="far fa-save"></i>',
        cancelButtonText: 'Cancelar <i class="far fa-window-close"></i>'
    }).then((result) => {
        if (result.value) {
            $.ajax({
                url: `${BASE}/automovil`,
                data: $("#form-update-vehiculo").serialize(),
                type: 'post',
                dataType: 'json'
            }).done(function (res) {
                console.log(res)
                if (res.type === "success") {
                    var ident = $("#id_auto").val();
                    var tipo = "";
                    var color_select = "";
                    for (let i in data_tipo) {
                        if (data_tipo[i].id === Number(res.tipo)) {
                            tipo = data_tipo[i].valor;
                        }
                    }

                    for (let c in color) {
                        console.log(color[c]);
                        if (color[c] === res.color) {
                            color_select = color[c];
                        }
                    }
                    $(`#index-${ident} .index-id .msg-updated`).html(`<span><small class="badge badge-success">
                        <i class="fas fa-check"> Edicion Reciente</i>
                    </small></span>`);
                    $(`#index-${ident} .index-tipos`).html(tipo);
                    $(`#index-${ident} .index-num-chasis`).html(res.num_chasis);
                    $(`#index-${ident} .index-modelo`).html(res.modelo);
                    $(`#index-${ident} .index-marca`).html(res.marca);
                    $(`#index-${ident} .index-color`).html(color_select);

                    Swal.fire(
                            'Editado',
                            'Se actualizo con exito!',
                            'success'
                            );
                }
            }).fail(function (jqXHR, txt, error) {
                Swal.fire(
                        `${txt}`,
                        `${error}`,
                        'error'
                        );
            });

        }
    });
}

function viewLogByAuto(id) {
    //alert(id)
    $.ajax({
        url: BASE + "/automovil",
        data: {
            action: "get-log-auto",
            id: id
        },
        type: "get",
        dataType: "json"
    }).done(function (res) {
        console.log(res);
        var template = "";
        var count = 0;
        for (let i in res) {
            template += `
                <tr id="index-${res[i].id}" class=''>
                    <td class="index-id">${res[i].id}</td>
                    <td class="index-fecha">${res[i].fecha}</td>
                    <td class="index-tipo">${res[i].tipo}</td>
                    <td class="index-descripcion" title="${res[i].descripcion}">${res[i].descripcion.substring(0, 12)}...</td>
                    <td class="index-costo">$${formatPrice(Number(res[i].costo))}</td>  
                </tr>
            `;
            count += 1;
        }
        $(".count-log-auto").html(count);
        $("#result-log-auto").html(template);
    }).fail(function (jqXHR, txt, error) {
        console.log(txt + "-->" + error);
    });
    $("#modalAutoLog").modal();
}
/**
 * 
 * ----------------------------------------
 * Registros
 */

function addNewLogView() {
    $("#app").load(BASE + "/registro/create.jsp");
}

function addLog() {
    var validation = false;
    var errors = "";
    var patente = $("#patente").val();
    if (patente === "" || patente.length < 6) {
        validation = true;
        errors += "[*] Patente Requerida o muy corta (6 caracteres) <br>"
    }
    if ($("#id").val() === "") {
        validation = true;
        errors += "[*] Patente No Valida! <br>";
    }
    if ($("#tipo").val() === "") {
        validation = true;
        errors += "[*] Tipo de Registro Requerido<br>";
    }
    if ($("#fecha").val() === "") {
        validation = true;
        errors += "[*] Fecha Requerida<br>";
    }
    if ($("#costo").val() === "") {
        validation = true;
        errors += "[*] Costo Requerido<br>";
    }

    console.log(verificarPatente(patente));
    if (!verificarPatente(patente)) {
        validation = true;
        errors += "[*] Patente No Registrada <br>";
    }
    if (validation) {
        Swal.fire({
            icon: 'error',
            title: 'Datos requeridos',
            html: `${errors}`
        });
    } else {
        $.ajax({
            url: BASE + "/reparaciones",
            data: $("#form-registro").serialize(),
            type: "post",
            dataType: "json"
        }).done(function (res) {
            if (res.type === "success") {
                Swal.fire({
                    icon: 'success',
                    title: 'Agregado con Exito',
                    text: 'se agrego un nuevo registro'
                });
                $("#captionPatente").html("").css({color: "wite", "background-color": "transparent"});

                $("#form-registro").trigger('reset');
            }
        }).fail(function (jqXHR, txt, error) {
            console.log(txt + "--" + error);
        });
    }
}

/**
 * 
 * @returns {vista de datos de todos lo automoviles}
 */
function log() {
    $.ajax({
        url: BASE + "/reparaciones",
        data: {
            action: "get-all"
        },
        type: "get",
        dataType: "json"
    }).done(function (res) {
        console.log(res);
        var template = "";
        for (let i in res) {
            template += `
                <tr id="index-${res[i].id}" onclick="viewDataLog(${res[i].id})" class='table-pointer'>
                    <td class="index-id">${res[i].id}</td>
                    <td class="index-fecha">${res[i].fecha}</td>
                    <td class="index-tipo">${res[i].tipo}</td>
                    <td class="index-descripcion" title="${res[i].descripcion}">${res[i].descripcion.substring(0, 12)}...</td>
                    <td class="index-costo">$${formatPrice(Number(res[i].costo))}</td>  
                    <td class="index-patente">${formatoPatente(res[i].patente)}</td>
                </tr>
            `;
        }
        $("#result-log").html(template);
    }).fail(function (jqXHR, txtStatus, error) {
        console.log(txtStatus + " " + error);
    });
    $("#app").load(BASE + "/registro/index.jsp");
}

/**
 * 
 * @param {type} id
 * @returns {vista de datos por identificador}
 */
function viewDataLog(id) {
    if (id !== undefined) {
        $.ajax({
            url: `${BASE}/reparaciones`,
            data: {
                action: 'one-data',
                id: id
            },
            type: 'get',
            dataType: 'json'
        }).done(function (res) {
            console.log(res);
            $("#modal-data").html(__templateLog(res));
            $(".registro-patente").html(`patente: ${formatoPatente(res.patente)}`);
            $(".patente").html(formatoPatente(res.patente) + ` <input type="hidden" value="${res.patente}" id="patente-up-${id}">`);
        }).fail(function (jqXHR, txt, error) {
            console.log(txt + "-->" + error);
        });
    }
    $("#logModal").modal();
}

/**
 * 
 * @param {type} id
 * @returns {Boolean}
 */
function deleteLog(id) {
    Swal.fire({
        title: 'Estas seguro?',
        text: "se borraran de forma permanente",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Si, borrarlo <i class="far fa-trash-alt"></i>',
        cancelButtonText: 'Cancelar <i class="far fa-window-close"></i>'
    }).then((result) => {
        if (result.value) {
            $.ajax({
                url: `${BASE}/reparaciones`,
                data: {
                    action: "delete",
                    id: id
                },
                type: 'post',
                dataType: 'json'
            }).done(function (res) {
                if (res.type === "success") {
                    Swal.fire(
                            'Eliminado',
                            'Se elimino con exito!',
                            'success'
                            );
                    $("#logModal").modal("hide");
                    $("#index-" + id).remove();
                }
            }).fail(function (jqXHR, txt, error) {
                Swal.fire(
                        `${txt}`,
                        `${error}`,
                        'error'
                        );
            });
        }
    });
}

function updateLog() {
    Swal.fire({
        title: 'Estas seguro',
        text: "de editar este registro?",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Si, editar nomas <i class="far fa-save"></i>',
        cancelButtonText: 'Cancelar <i class="far fa-window-close"></i>'
    }).then((result) => {
        if (result.value) {
            $.ajax({
                url: `${BASE}/reparaciones`,
                data: $("#form-update-log").serialize(),
                type: 'post',
                dataType: 'json'
            }).done(function (res) {
                //console.log(res);
                if (res.type === "success") {
                    var ident = $("#id_log").val();
                    var tipo = "";
                    for (let i in data_tipo_log) {
                        if (data_tipo_log[i].id === Number(res.tipo)) {
                            tipo = data_tipo_log[i].valor;
                        }
                    }
                    $(`#index-${ident} .index-id`).html(`${ident}<span><small class="badge badge-success">
                        <i class="fas fa-check"> Edicion Reciente</i>
                    </small></span>`);
                    $(`#index-${ident} .index-fecha`).html(res.fecha);
                    $(`#index-${ident} .index-tipo`).html(tipo);
                    $(`#index-${ident} .index-descripcion`).html(`${res.descripcion.substring(0, 12)}...`);
                    $(`#index-${ident} .index-costo`).html(`$${formatPrice(Number(res.costo))}`);
                    $(`#index-${ident} .index-patente`).html(`${formatoPatente($(`#patente-up-${ident}`).val())}`);

                    Swal.fire(
                            'Editado',
                            'Se actualizo con exito!',
                            'success'
                            );
                }
            }).fail(function (jqXHR, txt, error) {
                Swal.fire(
                        `${txt}`,
                        `${error}`,
                        'error'
                        );
            });

        }
    });
}
//------------------------------------------------------------------------------
// Helpers
//------------------------------------------------------------------------------
/**
 * 
 * @param {this} that
 * @returns {estado de la patente}
 */

function searchPatenteData(that) {
    var status = "";
    var data;
    if (!verificarPatente(that.value)) {
        status = "No encontrada";
    } else {
        status = "Valida";
        $.ajax({
            url: BASE + "/automovil",
            data: {
                patente: that.value,
                action: "get-patente"
            },
            type: "get",
            dataType: "json",
            async: false,
            success: function (res) {
                data = res;
            }
        });
        var id = `<input type="hidden" name="id" id="id" value="${data.id}">`;
    }
    if (id === undefined) {
        id = `<input type="hidden" name="id" id="id" value="">`;
    }
    var pa;
    if (that.value.length === 6) {
        pa = formatoPatente(that.value).toUpperCase();
    } else {
        pa = that.value.toUpperCase();
    }
    var template = `Patente: ${pa}<br>
        Estado: ${status}<br>
        ${id}
    `;

    if (that.value === "") {
        $("#captionPatente").html("").css({color: "wite", "background-color": "transparent"});
    } else {
        $("#captionPatente").html(template).css({color: "wite", "background-color": "blue"});
    }
}
/**
 * @view template de ver datos de auto por identificador
 * @param {type} obj
 * @returns {String}
 */

function __template(obj) {
    var select_type = `<select name="tipo" id="tipo" class="form-control">`;

    for (let i in data_tipo) {
        if (data_tipo[i].valor === obj.tipos) {
            select_type += `<option value="${data_tipo[i].id}" selected="selected">${data_tipo[i].valor}</option>`;
        } else {
            select_type += `<option value="${data_tipo[i].id}">${data_tipo[i].valor}</option>`;
        }
    }
    select_type += "</select>";

    var select_color = `<select name="color" id="color" class="form-control">`;
    for (let c in color) {
        if (color[c] === obj.color) {
            select_color += `<option value="${color[c]}" selected="selected">${color[c]}</option>`;
        } else {
            select_color += `<option value="${color[c]}">${color[c]}</option>`;
        }
    }
    select_color += "</select>";
    return template = `
      
        <div class="row">
            <input type="hidden" name="id_auto" id="id_auto" value="${obj.id}">
            <input type="hidden" name="action" id="action" value="update">
            <div class="col"></div>
            <div class="col">
                <button style="float:right;margin-left:5px;" class="btn btn-info" 
                onclick="viewLogByAuto('${obj.id}');return false;">Ver Registros <i class="far fa-eye"></i></button>
                <button style="float:right;" class="btn btn-danger" 
                onclick="deleteAuto('${obj.id}');return false;">Eliminar <i class="far fa-trash-alt"></i></button>
            </div>
        </div>
     
        <div class="row">
            <div class="col">
                <!-- Material input -->
                <div class="md-form">
                    <label for="tipo">Tipo</label>
                    ${select_type}
                </div>
            </div>
            <div class="col">
                <!-- Material input -->
                <div class="md-form">
                    <label for="num_chasis">Numero Chasis</label>
                    <input value="${obj.num_chasis}" type="text" name="num_chasis" id="num_chasis" class="form-control">
                </div>
            </div>
        </div>
    <div class="row">
            <div class="col">
                <!-- Material input -->
                <div class="md-form">
                    <label for="marca">Marca</label>
                    <input value="${obj.marca}" type="text" id="marca" name="marca" class="form-control">
                </div>
            </div>
            <div class="col">
                <!-- Material input -->
                <div class="md-form">
                    <label for="modelo">Modelo</label>
                    <input value="${obj.modelo}" type="text" id="modelo" name="modelo" class="form-control">
                </div>
            </div>
        </div>
    <div class="row">
            <div class="col-6">
                <!-- Material input -->
                <div class="md-form">
                    <label for="color">Color</label>
                    ${select_color}
                </div>
            </div>
            
        </div>
    `;
}

/**
 * Template Registros
 * @returns {obj}
 */

function __templateLog(obj) {
    var select_type = `<select name="tipo" id="tipo" class="form-control">`;

    for (let i in data_tipo_log) {
        if (data_tipo_log[i].valor === obj.tipo) {
            select_type += `<option value="${data_tipo_log[i].id}" selected="selected">${data_tipo_log[i].valor}</option>`;
        } else {
            select_type += `<option value="${data_tipo_log[i].id}">${data_tipo_log[i].valor}</option>`;
        }
    }
    select_type += "</select>";
    return template = `
        <div class="row">
            <input type="hidden" name="id_log" id="id_log" value="${obj.id}">
            <input type="hidden" name="action" id="action" value="update">
            <div class="col"></div>
            <div class="col"><button style="float:right;" class="btn btn-danger" 
            onclick="deleteLog('${obj.id}');return false;">Eliminar <i class="far fa-trash-alt"></i></button></div>
        </div>
        <div class="row">
            <div class="col">
                <!-- Material input -->
                <div class="md-form">
                    <label for="tipo">Tipo</label>
                    ${select_type}
                </div>
            </div>
            <div class="col">
                <!-- Material input -->
                <div class="md-form">
                    <label for="fecha">Fecha</label>
                    <input value="${obj.fecha}" type="date" name="fecha" id="fecha" class="form-control">
                </div>
            </div>
        </div>
    <div class="row">
            <div class="col">
                <!-- Material input -->
                <div class="md-form">
                    <label for="costo">Costo</label>
                    <input value="${obj.costo}" type="text" id="costo" name="costo" class="form-control">
                </div>
            </div>
            <div class="col">
                <!-- Material input -->
                <div class="md-form">
                    <label for="descripcion">Descripcion</label>
                    <textarea name="descripcion" id="descripcion" cols="30" rows="10" 
                        class="form-control" style="margin-top: 0px; margin-bottom: 0px; height: 224px;">${obj.descripcion}</textarea>
                </div>
            </div>
        </div>
    
    `;
}

/**
 * 
 * @param {String} patente
 * @returns {String}
 */
function formatoPatente(patente) {
    var arr = patente.split("");
    console.log(arr);
    return `${arr[0]}${arr[1]}-${arr[2]}${arr[3]}-${arr[4]}${arr[5]}`;
}

/**
 * 
 * @param {String} patente
 * @returns {Boolean}
 */
function verificarPatente(patente) {
    var status = false;
    $.ajax({
        url: `${BASE}/automovil`,
        data: {
            action: "verificarPatente",
            patente: patente
        },
        async: false,
        type: "get",
        dataType: "json",
        success: function (res) {
            if (res.type === "no-disponible")
                status = true;
        }
    });
    return status;
}

/**
 * Formateo de precios
 * @param {type} number
 * @returns {String}
 */
function formatPrice(number) {
    number = String(number).replace(/\D/g, "");
    return number === "" ? number : Number(number).toLocaleString();
}