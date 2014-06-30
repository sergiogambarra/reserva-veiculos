<%-- 
    Document   : listaVeiculos
    Created on : 21/04/2014, 18:38:38
    Author     : Paula
--%>

<%


%>
<%@page import="srv.modelo.Veiculo"%>
<%@page import="java.util.List"%>
<%@include file="ValidarLoginAdministrador.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Lista de Veículos</title>
        <link rel="stylesheet" href="css/styleLogin.css" type='text/css'>
        <link rel="stylesheet" href="css/styleContent.css" type='text/css'>
        <script type="text/javascript" type="text/javascript" src="js/validacoesJs.js"></script>
    </head>
    <body>
        <section class="container">
            <div class="cabecalho">
                <div class="cabecalhoLateral">
                    <%@include file="cabecalhoNomeUsuario.jsp"%>
                    <div class="cabecalhoLogout" id="desl"><a href='ControleLogin?action=deslogar'>Sair</a>&nbsp;|&nbsp;<a href='ControleServidor?action=formAlterarSenha'>Alterar Senha</a></div>
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

                            <!-- Parte especifica de parametros de pesquisa -->
                            <form name="consultaVeiculo" action="ControleVeiculo"> 
                                <input type="hidden" name="action" value="consultarVeiculo"/>    
                                
                                <div class="formFiltro">
                                    <label for="ano">Ano</label>
                                    <input type="text" id="ano" name="ano" placeholder="ANO" onKeyPress="return mascaraMatSiap(event);" maxlength="4"/>
                                </div>
                                <div class="formFiltro">
                                    <label for="placa">Placa</label>
                                    <input type="text" id="placa" name="placa" placeholder="XXX0000" onKeyPress="return mascaraAlfanumerica(event)" pattern="[A-Z]{3}-[0-9]{4}" maxlength="8"/>
                                </div>
                                <div class="formFiltro">
                                    <label for="renavam">Renavam</label>
                                    <input type="text" id="renavam" name="renavam" placeholder="RENAVAM" onKeyPress="return mascaraMatSiap(event);" maxlength="11"/>
                                </div>
                                <div class="formFiltroConsultar">
                                    <input type="submit" value="Consultar"/>
                                </div>
                            </form>

                        </div>

                        <table class="tabelaListaVeiculos">
                            <thead>
                            <td id="Placa" class="colunaDuzentos">Placa</td>
                            <td id="Modelo">Modelo</td>
                            <td id="Marca" class="colunaDuzentos">Marca</td>
                            <td id="Renavam" class="colunaDuzentos">Renavam</td>
                            <td id="Acoes" class="colunaAcoesHead" >Ações</td>
                            </thead>
                            <tbody>
                                <%
                                    List<Veiculo> lista = (List<Veiculo>) request.getAttribute("listaveic");
                                    for (int i = 0; i < lista.size(); i++) {
                                        Veiculo veic = lista.get(i);
                                %>
                                <tr>
                                    <td headers="Placa"><%= veic.getPlaca()%></td>
                                    <td headers="Modelo"><% out.print(veic.getModelo());%></td>
                                    <td headers="Marca"><% out.print(veic.getMarca());%></td>
                                    <td headers="Renavam"><% out.print(veic.getRenavam());%></td>
                                    <td headers="Acoes" class="colunaAcoes">
                                        <div class="divColunaAcoes">
                                            <ul>
                                                <li><a href="ControleVeiculo?action=editarVeiculo&placa=<%= veic.getPlaca()%>"><div class="iconeEditar" alt="Editar Veículo." title="Editar Veículo"></div></a></li>
                                                <li><a href="ControleVeiculo?action=visualizarVeiculo&placa=<%= veic.getPlaca()%>"><div class="iconeVisualizar" alt="Visualizar informações do Veículo." title="Visualizar Veículo"></div></a></li>
                                                <li><a href="ControleVeiculo?action=excluirVeiculo&placa=<%= veic.getPlaca()%>"><div class="iconeDeletar" alt="Deletar Veículo." title="Deletar Veículo" onclick="return exluirCadastro()"></div></a></li>
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
