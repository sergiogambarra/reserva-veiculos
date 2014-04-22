<%-- 
    Document   : listaReservas
    Created on : 21/04/2014, 18:38:38
    Author     : Paula
--%>

<%


%>    
<%@include file="ValidarLoginAdministrador.jsp" %>
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
        <div class="cabecalhoUsuario">Bem vindo, Servidor Fulano de Tal</div>
        <div class="cabecalhoLogout" id="desl"><a href='ControleLogin?action=deslogar'>Logout</a></div>
      </div>
      <div class="cabecalhoImagem" alt="SRV: Sistema de Reserva de Veículos para controle de frota." title="SRV: Sistema de Reserva de Veículos.">      
      </div>
    </div>
    <div class="containerLogado">
      <div class="containerLogadoMenu">
        <nav class="menuAcoes">
          <ul>
            <li><a href="listaReservas.jsp">Lista de Reservas</a></li>
            <li><a href="#">Lista de Veículos</a></li>
            <li><a href="ControleServidor?action=listaServidores">Lista de Servidores</a></li>
            <li><a href="#">Novo Veículo</a></li>
            <li><a href="cadastrarServidor.jsp">Novo Servidor</a></li>
          </li>
        </nav>
      </div>
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
              <td id="Placa" class="colunaDuzentos">Placa</td>
              <td id="Servidor">Servidor</td>
              <td id="Saida" class="colunaDuzentos">Saída</td>
              <td id="Retorno" class="colunaDuzentos">Retorno</td>
              <td id="Acoes" class="colunaAcoesHead" >Ações</td>
            </thead>
            <tbody>
              <tr>
                <td headers="Placa">Placa</td>
                <td headers="Veiculo">Veículo</td>
                <td headers="Saida">Saída</td>
                <td headers="Retorno">Retorno</td>
                <td headers="Acoes" class="colunaAcoes">
                    <div class="divColunaAcoes">
                      <ul>
                        <li><a href="#" ><div class="iconeEditar" alt="Editar veículo com a placa X." title="Editar veículo"></div></a></li>
                        <li><a href="#" ><div class="iconeVisualizar" alt="Visualizar informações do veículo com a placa X." title="Visualizar veículo"></div></a></li>
                        <li><a href="#" ><div class="iconeDeletar" alt="Deletar veículo com a placa X." title="Deletar veículo"></div></a></li>
                      </ul>
                    </div>
                </td>
              </tr>
              <tr>
                <td headers="Placa">Placa</td>
                <td headers="Veiculo">Veículo</td>
                <td headers="Saida">Saída</td>
                <td headers="Retorno">Retorno</td>
                <td headers="Acoes" class="colunaAcoes">
                    <div class="divColunaAcoes">
                      <ul>
                        <li><a href="#" ><div class="iconeEditar" alt="Editar veículo com a placa X." title="Editar veículo"></div></a></li>
                        <li><a href="#" ><div class="iconeVisualizar" alt="Visualizar informações do veículo com a placa X." title="Visualizar veículo"></div></a></li>
                        <li><a href="#" ><div class="iconeDeletar" alt="Deletar veículo com a placa X." title="Deletar veículo"></div></a></li>
                      </ul>
                    </div>
                </td>
              </tr>
              <tr>
                <td headers="Placa">Placa</td>
                <td headers="Veiculo">Veículo</td>
                <td headers="Saida">Saída</td>
                <td headers="Retorno">Retorno</td>
                <td headers="Acoes" class="colunaAcoes">
                    <div class="divColunaAcoes">
                      <ul>
                        <li><a href="#" ><div class="iconeEditar" alt="Editar veículo com a placa X." title="Editar veículo"></div></a></li>
                        <li><a href="#" ><div class="iconeVisualizar" alt="Visualizar informações do veículo com a placa X." title="Visualizar veículo"></div></a></li>
                        <li><a href="#" ><div class="iconeDeletar" alt="Deletar veículo com a placa X." title="Deletar veículo"></div></a></li>
                      </ul>
                    </div>
                </td>
              </tr>
              <tr>
                <td headers="Placa">Placa</td>
                <td headers="Veiculo">Veículo</td>
                <td headers="Saida">Saída</td>
                <td headers="Retorno">Retorno</td>
                <td headers="Acoes" class="colunaAcoes">
                    <div class="divColunaAcoes">
                      <ul>
                        <li><a href="#" ><div class="iconeEditar" alt="Editar veículo com a placa X." title="Editar veículo"></div></a></li>
                        <li><a href="#" ><div class="iconeVisualizar" alt="Visualizar informações do veículo com a placa X." title="Visualizar veículo"></div></a></li>
                        <li><a href="#" ><div class="iconeDeletar" alt="Deletar veículo com a placa X." title="Deletar veículo"></div></a></li>
                      </ul>
                    </div>
                </td>
              </tr>
              <tr>
                <td headers="Placa">Placa</td>
                <td headers="Veiculo">Veículo</td>
                <td headers="Saida">Saída</td>
                <td headers="Retorno">Retorno</td>
                <td headers="Acoes" class="colunaAcoes">
                    <div class="divColunaAcoes">
                      <ul>
                        <li><a href="#" ><div class="iconeEditar" alt="Editar veículo com a placa X." title="Editar veículo"></div></a></li>
                        <li><a href="#" ><div class="iconeVisualizar" alt="Visualizar informações do veículo com a placa X." title="Visualizar veículo"></div></a></li>
                        <li><a href="#" ><div class="iconeDeletar" alt="Deletar veículo com a placa X." title="Deletar veículo"></div></a></li>
                      </ul>
                    </div>
                </td>
              </tr>
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
