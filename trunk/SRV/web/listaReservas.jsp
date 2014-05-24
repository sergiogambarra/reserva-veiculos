<%-- 
    Document   : listaReservas
    Created on : 21/04/2014, 18:38:38
    Author     : Paula
--%>

<%@page import="srv.modelo.Servidor"%>
<%@include file="ValidarLogin.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html lang="pt-br">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>Lista de Reservas</title>
  <link rel="stylesheet" href="css/styleLogin.css" type='text/css'>
  <link rel="stylesheet" href="css/styleContent.css" type='text/css'>
</head>
<body>
	<section class="container">
		<div class="cabecalho">
      <div class="cabecalhoLateral">
        <%
                        Servidor servidor = new Servidor();
                            if (request.getSession().getAttribute("administrador") != null) {
                                servidor = (Servidor)request.getSession().getAttribute("administrador");
                            }
                        %>
                    <div class="cabecalhoUsuario">Bem vindo, <%= servidor.getNome() %></div>
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
              Você está em: 
              <script type="text/javascript">
                var pagina = document.title;
                document.write(pagina);
              </script>
            </div>
          <div class="filtroData">
        <!--
            <form action="#" class="filtroDataFormulario">
                  <label name="filtroLabelDataInicio">De</label>
                  <input type="date" name="filtroDataInicio" class="inputDataInicio"/>
                  <label name="filtroLabelDataRetorno">Até</label>
                  <input type="date" name="dataInicio" class="inputDataInicio"/>
            </form>
          -->
        </div>
        
          <table class="tabelaListaVeiculos">
            <thead>
              <p>Minhas Reservas</p>
              <td id="Responsavel" class="colunaDuzentos">Responsável</td>
              <td id="DataSaida" class="colunaDuzentos">Data de saída</td>
              <td id="HorarioSaida" class="colunaDuzentos">Horário de saída</td>
              <td id="Destino" class="colunaDuzentos">Destino</td>
              <td id="Placa" class="colunaDuzentos">Placa</td>
              <td id="Modelo" class="colunaDuzentos">Modelo</td>
              <td id="Acoes" class="colunaAcoesHead" >Ações</td>
            </thead>
            <tbody>
              <tr>
                <td headers="Responsavel">Responsável</td>
                <td headers="DataSaida">Data de saída</td>
                <td headers="HorarioSaida">Horário de saída</td>
                <td headers="Destino">Destino</td>
                <td headers="Placa">Placa</td>
                <td headers="Modelo">Modelo</td>
                <td headers="Acoes" class="colunaAcoes">
                    <div class="divColunaAcoes">
                      <ul>
                        <li><a href="#" ><div class="iconeEditar" alt="Editar informações da reserva." title="Editar reserva"></div></a></li>
                        <li><a href="#" ><div class="iconeVisualizar" alt="Visualizar informações da reserva." title="Visualizar reserva"></div></a></li>
                        <li><a href="#" ><div class="iconeDeletar" alt="Deletar reserva." title="Deletar reserva"></div></a></li>
                      </ul>
                    </div>
                </td>
              </tr>
              
        <table class="tabelaListaVeiculos">
            <thead>
                
              <br><br><p>Outras Reservas</p>
              <td id="Responsavel" class="colunaDuzentos">Responsável</td>
              <td id="DataSaida" class="colunaDuzentos">Data de saída</td>
              <td id="HorarioSaida" class="colunaDuzentos">Horário de saída</td>
              <td id="Destino" class="colunaDuzentos">Destino</td>
              <td id="Placa" class="colunaDuzentos">Placa</td>
              <td id="Modelo" class="colunaDuzentos">Modelo</td>
              <td id="Acoes" class="colunaAcoesHead" >Ações</td>
            </thead>
            <tbody>
              <tr>
                <td headers="Responsavel">Responsável</td>
                <td headers="DataSaida">Data de saída</td>
                <td headers="HorarioSaida">Horário de saída</td>
                <td headers="Destino">Destino</td>
                <td headers="Placa">Placa</td>
                <td headers="Modelo">Modelo</td>
                <td headers="Acoes" class="colunaAcoes">
                    <div class="divColunaAcoes">
                      <ul>
                        <%
                            if (request.getSession().getAttribute("administrador") != null) {
                        %>  
                        <li><a href="#" ><div class="iconeEditar" alt="Editar informações da reserva." title="Editar reserva"></div></a></li>
                        <%
                        }
                        %>
                        <li><a href="#" ><div class="iconeVisualizar" alt="Visualizar informações da reserva." title="Visualizar reserva"></div></a></li>
                        <%
                            if (request.getSession().getAttribute("administrador") != null) {
                        %>  
                        <li><a href="#" ><div class="iconeDeletar" alt="Deletar reserva." title="Deletar reserva"></div></a></li>
                        <%
                        }
                        %>
                        
                      </ul>
                    </div>
                </td>
              </tr>
              
            </tbody>
          </table>
            
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
