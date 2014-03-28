<%-- 
    Document   : cadastrocompra
    Created on : 20/05/2013, 21:06:53
    Author     : fernando
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<HTML>
 <HEAD>
  <TITLE> Sites de compras via Internet </TITLE>
  <meta http-equiv="Content-type" content="text/html" charset="utf-8">
   <!-- <script type="text/javascript" src="http://code.jquery.com/jquery-1.6.2.min.js"></script>-->
  <script language="javaScript" src="js/algoritmo.js"></script>
  <link href="estilos.css" rel="stylesheet" type="text/css">
    
	         <script type="text/javascript">
                         function id(el) {
                         return document.getElementById(el);
                     }
            
                        function mostraf() {
						
                         id('fisica').style.display = 'block';
                         id('juridica').style.display = 'none';
				     }
            
                        function mostraj() {
                          id('fisica').style.display = 'none';
                          id('juridica').style.display = 'block';
                    }
				
            </script>
	
         
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
  <div id="quadro1">
      

      <h2>Cadastro do Cliente</h2>
    <form name="frm" method="post" action="ControleCliente" onSubmit="return verificar()">
    <input type="hidden" name="action" value="frmccli"/>
		<label for="nome">Nome:</label> 
		<input type="text" name="nome" id="nome" onFocus="limpar(document.frm.nome)" value="Digite aqui..."><br><br>
        
        <label for="telefone">Telefone:</label> 
		<input type="text" name="telefone" id="telefone" onFocus="limpar(document.frm.telefone)" value="Digite aqui..."><br><br>
        
        <label for="endereco">Endereço:</label> 
		<input type="text" name="endereco" id="endereco" onFocus="limpar(document.frm.endereco)" value="Digite aqui..."><br><br>

		
		<label for="regmel">Tipo de Pessoa:</label><br>
		<input type="radio" name="regmel" id="regmel" value="f" onclick="javascript:mostraf();"/>Fisica 
		<input type="radio" name="regmel" id="regmel" value="j" onclick="javascript:mostraj();"/>Jurídica<br><br>
         <!-- Uso da classe específica para parágrafos -->
        
		<div id='fisica'style="position:relative; display:none">
          <span class="minhaclasse2">Pessoa Fisica</span></p><br><br>
          
        <label for="cpf" id="lacpf">CPF:</label> 
		<input type="text" name="cpf" id="cpf" onFocus="limpar(document.frm.cpf)" value="Digite aqui..."><br><br>
        
        <label for="rg" id="larg">RG:</label> 
		<input type="text" name="rg" id="rg" onFocus="limpar(document.frm.rg)" value="Digite aqui..."><br><br>
        
        <label for="datadenascimento" id="ladn">Datade Nascimento:</label> 
		<input type="text" name="datadenascimento" id="datadenascimento" onFocus="limpar(document.frm.datadenascimento)" value="Digite aqui..."><br><br>
        </div>
		<!-- Uso da classe específica para parágrafos -->
		<div id='juridica' style="position:relative; display:none">
        <span class="minhaclasse2">Pessoa Jurídica</span></p><br><br>
        
        <label for="cnpj" id="larcnpj">CNPJ:</label> 
		<input type="text" name="cnpj" id="cnpj" onFocus="limpar(document.frm.cnpj)" value="Digite aqui..."><br><br>
        
        <label for="inscricaoestadual" id="larinsest">Inscrição Estadual:</label> 
		<input type="text" name="inscricaoestadual" id="inscricaoestadual" onFocus="limpar(document.frm.inscricaoestadual)" value="Digite aqui..."><br><br>
        
        <label for="inscricaomunicipal" id="larinsmuni">Inscrição Municipal:</label> 
		<input type="text" name="inscricaomunicipal" id="inscricaomunicipal" onFocus="limpar(document.frm.inscricaomunicipal)" value="Digite aqui..."><br><br>
        <label for="datafundacao" id="lardatafun">Data de Fundacao:</label> 
		<input type="text" name="datafundacao" id="datafundacao" onFocus="limpar(document.frm.datafundacao)" value="Digite aqui..."><br><br>
       </div>      
        
		<!-- Uso da classe comum para todos os componentes -->
		<input type="submit" value="Salvar" class="minhaclasse2">
		<input type="button" value="Voltar" class="minhaclasse2">
		<input type="reset" value="Limpar" class="minhaclasse2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="inicio" class="minhaclasse" onClick="location. href= 'index.jsp' ">
	  </form>
	        
  </div>
   
</div>
<!--</div>-->	

 </BODY>
</HTML>
