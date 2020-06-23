<%-- 
    Document   : home
    Created on : 17/06/2020, 21:25:16
    Author     : MarcosPauloRMM
--%>


<%@page import="db.Attempt"%>
<%@page import="db.User"%>
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
                        <%@ include file="WEB-INF/JSPF/media.jspf" %>
                    </div>
                    <div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-4">
                        <%@ include file="WEB-INF/JSPF/myattempts.jspf" %>
                    </div>
                </div> 
            </div> 
        <%}%>
        <br><br><br>
        <hr>
        <div class="container">
            <div class="row mt-2">
                <div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-4">
                    <%@ include file="WEB-INF/JSPF/lastattempts.jspf" %>
                </div>
                <div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-4">
                    <%@ include file="WEB-INF/JSPF/ranking.jspf" %>
                </div>
            </div>
        </div>
        <%@ include file="WEB-INF/JSPF/footer.jspf" %>
    </body>
</html>