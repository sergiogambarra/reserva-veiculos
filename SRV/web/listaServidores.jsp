<%-- 
    Document   : listaServidores
    Created on : 21/04/2014, 18:38:38
    Author     : Paula
--%>

<%@page import="srv.modelo.Servidor"%>
<%@page import="java.util.List"%>
<%@include file="ValidarLoginAdministrador.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html lang="pt-br">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>Lista de Servidores</title>
  <link rel="stylesheet" href="css/styleLogin.css" type='text/css'>
  <link rel="stylesheet" href="css/styleContent.css" type='text/css'>
  <script type="text/javascript" type="text/javascript" src="js/validacoesJs.js"></script>
</head>
<body>
	<section class="container">
		<div class="cabecalho">
      <div class="cabecalhoLateral">
        <div class="cabecalhoUsuario">Bem vindo, Servidor Fulano de Tal</div>
        <div class="cabecalhoLogout" id="desl"><a href='ControleLogin?action=deslogar'>Logout</a></div>
      </div>
      <div class="cabecalhoImagem" alt="SRV: Sistema de Reserva de Veículos para controle de frota." title="SRV: Sistema de Reserva de Veículos.">      
      </div>
    </div>
    <div class="containerLogado">
      <%@include file="menu.jsp" %>
      <!-- A próxima div poderia servir para controle de permissões? -->
      <div class="containerLogadoBorda">
        <div class="containerLogadoDados">
          <div class="paginaAtual">
            <table class="tabelaMensagem">
                <thead>
                  <td>
                      <div class="barraNavegacao">
                          <p>Você está em: 
                            <script type="text/javascript">
                                var pagina = document.title;
                                document.write(pagina);
                            </script>
                          </p>
                      </div>
                        </td>
                        <td>
                            <div class="mensagem">
                                  <%
                                    if (request.getAttribute("mensagem") != null) {
                                  %>
                                  <p><%= request.getAttribute("mensagem")%> </p>
                                  <%
                                             }
                                  %>
                            </div>
                        </td>
                </thead>
            </table>
        </div>
          <div class="filtroData">
        <!--    <form action="#" class="filtroDataFormulario">
              <label name="filtroLabelDataInicio">De</label>
              <input type="date" name="filtroDataInicio" class="inputDataInicio"/>
              <label name="filtroLabelDataRetorno">Até</label>
              <input type="date" name="dataInicio" class="inputDataInicio"/>
            </form>
          -->
        </div>
        
          <table class="tabelaListaVeiculos">
            <thead>
              <td id="Matricula" class="colunaDuzentos">Matrícula</td>
              <td id="Servidor">Nome</td>
              <td id="CNH" class="colunaDuzentos">email</td>
              <td id="Telefone" class="colunaDuzentos">Telefone</td>
              <td id="Acoes" class="colunaAcoesHead" >Ações</td>
            </thead>
            <tbody>
                <%
                    List<Servidor> lista = (List<Servidor>) request.getAttribute("listaserv");
                    for (int i = 0; i < lista.size(); i++) {
                        Servidor serv = lista.get(i);
                %>
              <tr>
                <td headers="Matricula"><%= serv.getMatriculaSIAPE() %></td>
                <td headers="Servidor"><% out.print(serv.getNome());%></td>
                <td headers="CNH"><% out.print(serv.getEmail());%></td>
                <td headers="Telefone"><% out.print(serv.getTelefone1());%></td>
                <td headers="Acoes" class="colunaAcoes">
                    <div class="divColunaAcoes">
                      <ul>
                        <li><a href="ControleServidor?action=editarServidor&matricula=<%= serv.getMatriculaSIAPE()%>"><div class="iconeEditar" alt="Editar Servidor." title="Editar Servidor"></div></a></li>
                        <li><a href="ControleServidor?action=visualizarServidor&matricula=<%= serv.getMatriculaSIAPE()%>"><div class="iconeVisualizar" alt="Visualizar informações do Servidor." title="Visualizar Servidor"></div></a></li>
                        <li><a href="ControleServidor?action=excluirServidor&matricula=<%= serv.getMatriculaSIAPE()%>"><div class="iconeDeletar" alt="Deletar Servidor." title="Deletar Servidor" onclick="return exluirCadastro()"></div></a></li>
                      </ul>
                    </div>
                </td>
              </tr>
              <%
                    }
                %>
            </tbody>
          </table>
        </div>
      </div>
      
    </div>
	</section>
	<section class="about">
    <p class="aboutLinks">
      &copy; 2014&ndash;2014 
      <a href="http://restinga.ifrs.edu.br" target="_parent">ADS 5º semestre 2014 - IFRS Campus Restinga</a>
    </p>
      <!-- <a href="http://www.cssflow.com/mit-license" target="_blank">MIT License</a><br> -->
  </section>
</body>
</html>