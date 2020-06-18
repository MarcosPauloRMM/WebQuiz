<%-- 
    Document   : login
    Created on : 17/06/2020, 21:27:46
    Author     : MarcosPauloRMM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="web.DbListener"%>
<%@page import="db.User"%>
<%@include file="WEB-INF/JSPF/logged.jspf" %>
<%    if (logged != null) {
        response.sendRedirect("profile.jsp");
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
                <h1 class="mb-3">Login</h1>
          <%
           String errorMessage = null;
            if(request.getParameter("session.login") != null){
                String login = request.getParameter("user.login");
                String password = request.getParameter("user.password");
                try{
                    User user = User.getUser(login, password);
                    if(user == null){
                        errorMessage = "Login/password incorrect";
                    }else{
                        session.setAttribute("user.login", login);
                        session.setAttribute("user.name", user.getName());
                        response.sendRedirect(request.getRequestURI());
                    }
                }catch(Exception ex){
                    errorMessage = ex.getMessage();
                }
            }else if(request.getParameter("session.logout") != null){
                session.removeAttribute("user.login");
                session.removeAttribute("user.name");
                response.sendRedirect(request.getRequestURI());
            }
            %>
            <%if(session.getAttribute("user.login") == null){%>
                <form method="post">
                    <div class="form-group">
                        <input class="form-control mb-3" type="text" id="user" name="user.login" placeholder="Username" required>
                        <input class="form-control mb-3" type="password" id="user" name="user.password" placeholder="Password" required>
                        <button type="submit" class="btn btn-login">Login</button>                
                    </div>  
                </form>
                <%if(errorMessage != null){%>
                <div style="color:red"><%= errorMessage %></div>
                <%}%>
            <%}%>
            <%if(DbListener.exceptionMessage != null){%>
                <h3 style="color:red"><%= DbListener.exceptionMessage %></h3>
            <%}%>
            </div>
        </div>
    </body>
</html>