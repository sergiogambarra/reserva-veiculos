<%-- 
    Document   : cadastrocompraPedido
    Created on : 24/05/2013, 16:09:30
    Author     : fernando-santos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<HTML>
 <HEAD>
  <TITLE> Sites de compras via Internet </TITLE>
  <meta http-equiv="Content-type" content="text/html" charset="utf-8">
   <script language="javaScript" src="js/algoritmo.js"></script>
  <link href="estilos.css" rel="stylesheet" type="text/css">
    
	
 </HEAD>
     <!--Eventos:
	- onload: Executa oa carregar a página;
	- onunload: Executa ao sair da pagina;
	- onclick: Executa ao clicar em um componente;
	- onchange: Ao alertar o valor de um componente;
	- onblur: Ao sair do componente:
	- onfocus: Ao entrar no componente;
	- onMouserOver: Ao passar o mouse sobre o componente;
	- onsubmit: Ao submeter um formulario;
	- onkeypress: Ao pressionar uma tecla;-->
 <BODY>
     
  <h1> Cadastro de Pedidos </h1>
  <hr>

<!--<div id="geral">-->
  
      <div id="quadro3">
      <h2>Cadastro de Pedido</h2>
    <form name="frm1" method="post" action="ControlePedido" onSubmit="return verificar()">

		<label for="tamanho">Tamanho:</label> 
		<input type="text" name="tamanho" id="tamanho" onFocus="limpar(document.frm1.tamanho)" value="Digite aqui..."><br><br>
        
                <label for="cor">Cor:</label> 
		<input type="text" name="cor" id="cor" onFocus="limpar(document.frm1.cor)" value="Digite aqui..."><br><br>
        
        
               <label for="camisa" >Escolha o tipo de Camiseta:</label> 
		<select name="camisa" id="camisa" onchange="mostrarValor()">
		<option value=""> Escolha o produto </option>
		<option value="Adidas"> Camiseta </option>
		<option value="Nike"> Polo </option>
		<option value="Puma"> Mangalonga </option>
	       </select>
                 <input type="text" name="preco" id="preco" value= "0.0" readonly><br><br>
        
		<!-- Uso da classe comum para todos os componentes -->
		<input type="submit" value="Salvar" class="minhaclasse2">
		<input type="button" value="Voltar" class="minhaclasse2">
		<input type="reset" value="Limpar" class="minhaclasse2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="inicio" class="minhaclasse" onClick="location. href= 'index.jsp' ">
	  </form>
	        
  </div>
 
<!--</div>-->		

 </BODY>
</HTML>

