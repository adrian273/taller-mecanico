<%-- 
    Document   : login
    Created on : 04-12-2019, 16:04:39
    Author     : adrian
--%>

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

        <div class="view intro-2 p-8" 
             style="background: url(<%= request.getContextPath()%>/assets/images/2.jpg) no-repeat center center;">
            <div class="full-bg-img">
                <div class="mask rgba-purple-light flex-center p-3">
                    <div class="container-fluid text-center white-text wow fadeInUp p-5">
                        <div class="mt-5">
                            <form action="login" method="post">
                                <input type="hidden" name="action" value="auth">
                                <div class="col-md-6 offset-3">
                                    <div class="card">
                                    <div class="card-header bg-primary">
                                        <h1>Login</h1>
                                    </div>
                                    <div class="card-body">
                                        <div class="form-group">
                                            <label for="email">Email</label>
                                            <input type="email" name="email" id="email" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label for="pass">Contraseña</label>
                                            <input type="password" name="pass" id="pass" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <button type="submit" class="btn btn-block btn-primary">
                                                Ingresar
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript" src="<%= path%>/assets/mdb/js/jquery.min.js"></script>
        <!-- Bootstrap tooltips -->
        <script type="text/javascript" src="<%= path%>/assets/mdb/js/popper.min.js"></script>
        <!-- Bootstrap core JavaScript -->
        <script type="text/javascript" src="<%= path%>/assets/mdb/js/bootstrap.min.js"></script>
        <!-- MDB core JavaScript -->
        <script type="text/javascript" src="<%= path%>/assets/mdb/js/mdb.min.js"></script>
        <script src="<%= path%>/assets/node_modules/sweetalert2/dist/sweetalert2.all.js"></script>
        <script>
            $(function () {
                $('[data-toggle="tooltip"]').tooltip();
            })
        </script>
        <!-- Your custom scripts (optional) -->
    </body>
</html>