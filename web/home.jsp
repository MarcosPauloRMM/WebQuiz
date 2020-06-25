<%-- 
    Document   : home
    Created on : 17/06/2020, 21:25:16
    Author     : MarcosPauloRMM
--%>


<%@page import="object.Attempt"%>
<%@page import="object.User"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="WEB-INF/JSPF/logged.jspf" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="WEB-INF/JSPF/header.jspf" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
        
        <%@ include file="WEB-INF/JSPF/menu.jspf" %>
        <%  if (logged == null) {%>
            <div class="jumbotron text-center">         
                <h1>Seja bem vindo ao desafio do Web Quiz.</h1>
            </div>
        <center>
            <h5>Atravesse os portões do conhecimento e se aventure em nossas perguntas.</h5>
            <img src="https://lh3.googleusercontent.com/AL2RmhBDzstx1TSb6Bh-ahOHs308MTdQ6CDRVr9noAp5TYVyUHt9pWQbg0-v03pNp6qD6_aZzTvMOF1VxYORZFpnP5PnHgyh3SWUxw=w1440-v1" alt="webquiz" width=50%>
        </center>
        <%}else{%>
            <div class="jumbotron text-center">         
                <h1>Olá <%=logged%>, bem vindo de volta.</h1>
            </div>
            <div class="container">
                <div class="row mt-2">
                    <div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-4">
                        Coluna da MEdia
                    </div>
                    <div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-4">
                        Minhas Tentativas
                    </div>
                </div> 
            </div> 
        <%}%>
        <br><br><br>
        <hr>
        <div class="container">
            <div class="row mt-2">
                <div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-4">
                    Ultima tentatibas
                </div>
                <div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-4">
                    Ranking
                </div>
            </div>
        </div>
        <%@ include file="WEB-INF/JSPF/footer.jspf" %>
    </body>
</html>