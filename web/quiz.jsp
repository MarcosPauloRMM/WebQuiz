<%-- 
    Document   : quiz.jsp
    Created on : 22/06/2020, 14:08:22
    Author     : MarcosPauloRMM
--%>

<%@page import="control.ControlAnswer"%>
<%@page import="object.Answer"%>
<%@page import="control.ControlQuestion"%>
<%@page import="java.util.ArrayList"%>
<%@page import="control.ControlQuiz"%>
<%@page import="object.Attempt"%>
<%@page import="object.Question"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="WEB-INF/JSPF/logged.jspf" %>
<!DOCTYPE html>

<%    
    if (logged == null) {
        response.sendRedirect("login.jsp");
    }

    new ControlQuestion().checkIfExistsQuestions();
    new ControlAnswer().checkIfExistsAnswers();
    
    Exception requestException = null;
    int numberResults = -1;
    Attempt attempt = new Attempt();
    attempt.setCodeQuestion(request.getParameter("codigos-perguntas"));
    attempt.setCodeAnswer(request.getParameter("codigos-respostas"));
    attempt.setCodeUser(request.getParameter("codeUser"));
    
    if(request.getParameter("enviar")!=null){
        try{
            numberResults = new ControlQuiz().getResultQuiz(attempt);
        }catch(Exception ex){
            requestException = ex;
        }
    }
%>

<html>
    <head>
        <%@include file="WEB-INF/JSPF/header.jspf" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quiz</title>
    </head>
    <body>
        <%@ include file="WEB-INF/JSPF/menu.jspf" %>
        <div class="jumbotron text-center">
            <h1><%=logged%>, responda o quiz abaixo!</h1>  
            <br>
        </div>
        <form id="form-quiz">
                <input type="hidden" id="quantidade-acertos" value="<%=numberResults != -1 ? numberResults : -1%>"/>
                <input type="hidden" id="codigo-usuario"  name="numberResults" value="<%=session.getAttribute("user.login")%>"/>
                <%int j = 1;
                ArrayList<Question> quiz = new ControlQuestion().searchQuestions();
                for(int i=0; i<quiz.size(); i++){%>
                
                    <span class="font-weight-bold"><%=j%>  - </span>
                    <span class="font-weight-bold"><%=quiz.get(i).getQuestion()%></span><br/><br/>
                    
                    <%ArrayList<Answer> alternatives = new ControlAnswer().searchAlternatives(quiz.get(i).getCodeQuestion());
                    
                        for(Answer alternative : alternatives){%>
                         <input type="radio" name="resposta-<%=j%>"  value="<%=alternative.getCodeAnswer()%>-<%=quiz.get(i).getCodeQuestion()%>">
                        
                         <%=alternative.getAnswer()%>
                         <br/>
                    <%}%>
                    <hr/>
                    <%j++;%>
                    
                <%}%>
                <button type="button" class="btn btn-primary d-none" data-toggle="modal" id="botao-alert" data-target="#modal-alerta-quantidade-acertos"></button>
                <div class="modal fade" id="modal-alerta-quantidade-acertos" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                  <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                      <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLongTitle"><%%>Resultado</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                          <span aria-hidden="true">&times;</span>
                        </button>
                      </div>
                      <div class="modal-body">
                          <span>Você acertou <%=numberResults%> questões do quiz</span>
                      </div>
                      <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-dismiss="modal" id="confirma-quantidade-acertos">Ok</button>
                      </div>
                    </div>
                  </div>
                </div>
                      
                <input type="hidden" name="codigos-perguntas" id="codigos-perguntas" />
                <input type="hidden" name="codigos-respostas" id="codigos-respostas" />
                <button type="button" class="btn btn-danger" id="enviar-respostas" value="enviar">Enviar Respostas</button>
                <input type="submit" name="enviar"  style="display:none" id="submitar-form" value="enviar" />
            </form>
         
    <%@ include file="WEB-INF/JSPF/footer.jspf" %>
    </body>
</html>
<script>
    document.addEventListener("DOMContentLoaded", function(event) {
        clickButtonAttempt();
       showResults();
    });

    function showResults(){
        if(document.getElementById("quantidade-acertos").value !== '-1'){
            addClickConfirmResults();
            document.getElementById("botao-alert").click();
        }
    }
    
    function addClickConfirmResults(){
        document.getElementById("confirma-quantidade-acertos").addEventListener("click", function(){
             window.location.href="home.jsp";
        });
    }
    
    function clickButtonAttempt(){
        let buttonAttempt = document.getElementById("enviar-respostas");
        
        buttonAttempt.addEventListener("click", function(){
            recoverValues();
            }
        );
    }
    
    function recoverValues(){
        var answer = document.querySelectorAll('input[name^="resposta-"]'); 
        var test = {
            codeQuestion : [] ,
            codeAnswer : []
        };
        let contador=0;
        let j =0;
        for(i = 0; i < answer.length; i++) { 
            if(answer[i].checked) {
                test.codeAnswer[j] = answer[i].value.split("-")[0];
                test.codeQuestion[j] = answer[i].value.split("-")[1];
                contador++;
                j++;
                
            }
        }
        if(test.codeAnswer.length === 10){
            document.getElementById("codigos-respostas").value = test.codeAnswer; 
            document.getElementById("codigos-perguntas").value = test.codeQuestion;
            document.getElementById("submitar-form").click();
        }else{
            alert("Responda todas as questões para continuar");
        }
    }

</script>