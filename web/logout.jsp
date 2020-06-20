<%-- 
    Document   : logout
    Created on : 19/06/2020, 17:04:47
    Author     : MarcosPauloRMM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    
    session.invalidate();
    response.sendRedirect("home.jsp");
    
%>