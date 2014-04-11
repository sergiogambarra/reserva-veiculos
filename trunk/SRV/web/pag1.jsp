<%-- 
    Document   : pag1
    Created on : 10/04/2014, 12:40:08
    Author     : Erick
--%>
<%@include file="ValidarLogin.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Login SRV</title>
    </head>
    <body>
        <h1>Bem vindo ao SRV</h1>
        
        <br><br><a href="pag2.jsp">próxima página</a><br>
        
        <div id="desl">
            <input type="button" value="DESLOGAR USUÁRIO" class="" onClick="location. href= 'ControleLogin?action=deslogar' ">
            
        </div>
    </body>
</html>
