<%-- 
    Document   : quiz.jsp
    Created on : 22/06/2020, 14:08:22
    Author     : MarcosPauloRMM
--%>

<%@page import="db.Question"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="WEB-INF/JSPF/logged.jspf" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="WEB-INF/JSPF/header.jspf" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quiz</title>
    </head>
    <body>
        <%@ include file="WEB-INF/JSPF/menu.jspf" %>
        <div class="jumbotron text-center">
            <h1>Responda o Quiz Abaixo!</h1>  
            <br>
            <hr>
        </div>
        <form method="post">
            <%for(Question list : Question.getQuestions()){%>

            <div class="form-group" style='text-align:left'>
                <h5>
                    <%=list.getDescription()%>               
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="answer" id="exampleRadios1" value="option1"><%=list.getAnswer1()%><br>
                        <input class="form-check-input" type="radio" name="answer" id="exampleRadios2" value="option2"><%=list.getAnswer2()%><br>
                        <input class="form-check-input" type="radio" name="answer" id="exampleRadios3" value="option3"><%=list.getAnswer3()%><br>
                        <input class="form-check-input" type="radio" name="answer" id="exampleRadios4" value="option4"><%=list.getAnswer4()%><br>
                    </div>
                </h5>                                                
            <%}%>
            <div style='text-align:center' >
                <button type="submit" class="btn btn-danger" name="session.attempt"  value="newattempt">Finalizar Tentativa</button>               
            </div>  
        </div>  
        </form>
         
    <%@ include file="WEB-INF/JSPF/footer.jspf" %>
    </body>
</html>