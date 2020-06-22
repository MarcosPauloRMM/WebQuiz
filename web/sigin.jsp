<%-- 
    Document   : sigin
    Created on : 21/06/2020, 17:41:55
    Author     : MarcosPauloRMM
--%>

<%@page import="db.User"%>
<%@page import="web.DbListener"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="WEB-INF/JSPF/logged.jspf" %>



<%

	String errorMessage = null;

if (request.getParameter("sigin") != null) {
    String name = request.getParameter("name");
    String login = request.getParameter("login");
    String password = request.getParameter("password");
    try {
        User.addUser(name, login, password);
        response.sendRedirect("profile.jsp");
        session.setAttribute("user.name", name);
        session.setAttribute("user.login", login);
    } catch (Exception e) {
        DbListener.exceptionMessage = e.getMessage();
    }
}
%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="WEB-INF/JSPF/header.jspf" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <%@ include file="WEB-INF/JSPF/menu.jspf" %>
        <div class="container">
            <div class="login">
                <h1 class="mb-3">Cadastre-se...</h1>
                    
                <form method="post">
                    <div class="form-group">
                        <input class="form-control mb-3" type="text" id="user" name="name" placeholder="Name" required>
                        <input class="form-control mb-3" type="text" id="user" name="login" placeholder="Login" required>
                        <input class="form-control mb-3" type="password" id="user" name="password" placeholder="Password" required>
                        <button type="submit" class="btn btn-login" name="sigin" value="Log in">Cadastro</button>               
                    </div>  
                </form>

            </div>
        </div>
    </body>
</html>
