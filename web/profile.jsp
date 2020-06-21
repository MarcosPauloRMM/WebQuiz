<%-- 
    Document   : profile
    Created on : 18/06/2020, 17:26:32
    Author     : MarcosPauloRMM
--%>


<%@page import="db.Attempt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="WEB-INF/JSPF/logged.jspf" %>
<%  if (logged == null) {
        response.sendRedirect("login.jsp");
    }

%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="WEB-INF/JSPF/header.jspf" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Perfil do Usuário</title>
    </head>
    <body>
s
        <%@ include file="WEB-INF/JSPF/menu.jspf" %>

        <div class="jumbotron text-center">
            <h1>Bem Vindo, <%=logged%></h1>
        </div>
        <div class="container mt-5">

            <div class="row mt-5">

                <div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-4">
                    <h2   class="text-center">Suas ultimas notas:</h2>
                    <table class="table table-striped mt-2">
                        <thead>
                            <tr>
                                <th style='text-align:center'>Nota:</th>
                            </tr>
                        </thead>
                            <%@ include file="WEB-INF/JSPF/myattempts.jspf" %>                     
                    </table>
                </div>
                <div class="col-6">
                    <h2 class="text-center">Sua Média
                        <br>
                        <br>
                        <br>
  
                    </h2>
                </div>
                <a href="quiz.jsp" class="btn btn-quiz">Realizar Quiz</a>
            </div>
        </div>
        <%@ include file="WEB-INF/JSPF/footer.jspf" %>
    </body>
</html>

