<%-- 
    Document   : footer
    Created on : 02-12-2019, 16:18:33
    Author     : adrian
--%>

<% String path = request.getContextPath();%>
<!-- jQuery -->
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
<script src="<%= request.getContextPath()%>/assets/main.js"></script>
</body>
</html>