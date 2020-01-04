<%-- 
    Document   : index
    Created on : 02-12-2019, 16:17:43
    Author     : adrian
--%>

<%
    String login = "";
    HttpSession ses = request.getSession();
    if (ses.getAttribute("login") != null) {
%>
<jsp:include page="layouts/header.jsp"></jsp:include>

<jsp:include page="layouts/footer.jsp"></jsp:include>

<%} else {
    response.sendRedirect("login.jsp");
}%>