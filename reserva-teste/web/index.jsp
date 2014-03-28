<%-- 
    Document   : index
    Created on : 20/05/2013, 20:47:48
    Author     : fernando
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="estilos.css" rel="stylesheet" type="text/css">
        <title>JSP Page</title>
    </head>
    <body>
	     <IMG SRC=imagem1.jpg style="width:1400px;height:550px;"> 
        
        <h1>Escolha seu produto</h1>
        
        <!--<input type="button" value="inicio" class="MeuBotao">-->
		<input type="button" value="Cadastro de Clientes" onClick="location. href= 'cadastrocompra.jsp' ">&nbsp;&nbsp;
		
                <input type="button" value="Lista de Produtos" onClick="location. href= 'comprovantedepagamento?action=listar_clientes' ">
        <p>Alteracao ana</p>
    </body>
</html>
