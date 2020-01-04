<%-- 
    Document   : nav-bar
    Created on : 02-12-2019, 16:21:50
    Author     : adrian
--%>
<%
    String path = request.getContextPath();
%>
<nav class="navbar navbar-expand-lg navbar-dark indigo">
    <a class="navbar-brand" href="<%= path%>">Taller Mecanico <sup>Beta</sup></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText"
            aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarText">
        <!--<ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="<%= path%>">Home
                    <span class="sr-only">(current)</span>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#" onclick="selectMain(this, 'automovil')">Automovil</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Registros</a>
            </li>
        </ul> -->

    </div>
</nav>