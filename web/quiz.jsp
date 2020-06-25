<%-- 
    Document   : quiz.jsp
    Created on : 22/06/2020, 14:08:22
    Author     : MarcosPauloRMM
--%>

<%@page import="db.Question"%>
<%@page import="db.Answer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="WEB-INF/JSPF/logged.jspf" %>
<!DOCTYPE html>
<%
    Exception requestException = null;
    int numerResults = -1;
    Teste teste = new Teste();
    teste.setCodigoPergunta(request.getParameter("codigos-perguntas"));
    teste.setCodigoResposta(request.getParameter("codigos-respostas"));
    teste.setCodigoUsuario(request.getParameter("codigoUsuario"));
    if(request.getParameter("enviar")!=null){
        try{
            numerResults = new ControleQuiz().getResultadoQuiz(teste);
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
            <h1>Responda o Quiz Abaixo!</h1>  
            <br>
        </div>
        <form method="post">
            
            <%int j = 1;
            for(Question list : Question.getQuestions()){%>
            <div class="form-group" style='text-align:left'>
                <h5>
                    <b>Questão 0<%=j%>: <%=list.getDescription()%></b>              
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="answer-<%=j%>" id="exampleRadios1" value="option1">A)<br>
                        <input class="form-check-input" type="radio" name="answer-<%=j%>" id="exampleRadios2" value="option2">B)<br>
                        <input class="form-check-input" type="radio" name="answer-<%=j%>" id="exampleRadios3" value="option3">C)<br>
                        <input class="form-check-input" type="radio" name="answer-<%=j%>" id="exampleRadios4" value="option4">D)<br>
                        
                    <br>
                    </div>
                </h5>
                                       
                <%j++;}%> 
            <div style='text-align:center' >
                <button type="submit" class="btn btn-danger" name="session.attempt" id="attempt" value="newattempt">Finalizar Tentativa</button>               
            </div>  
        </div>  
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
        if(document.getElementById("quantidade-acertos  ?????????").value !== '-1'){
            adicionaClickConfirmResults();
            document.getElementById("attempt").click();
        }
    }
    
    function adicionaClickConfirmResults(){
        document.getElementById("confirma-quantidade-acertos ?????????").addEventListener("click", function(){
             window.location.href="home.jsp";
        });
    }
    
    function clickButtonAttempt(){
        let buttonAttempt = document.getElementById("enviar-respostas ???????????????");
        
        buttonAttempt.addEventListener("click", function(){
            recoverValues();
            }
        );
    }
    
    function recoverValues(){
        var answer = document.querySelectorAll('input[name^="resposta-"] ?????????????????'); 
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
            document.getElementById("codigos-respostas ????????????").value = test.codeAnswer; 
            document.getElementById("codigos-perguntas").value = test.codeQuestion;
            document.getElementById("submitar-form").click();
        }else{
            alert("Responda todas as questões para continuar");
        }
    }

</script>