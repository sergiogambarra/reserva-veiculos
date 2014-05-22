<%-- 
    Document   : visualizarServidor
    Created on : 21/04/2014, 22:14:59
    Author     : Paula
--%>

<%@page import="srv.dao.InterfaceVeiculoDAO"%>
<%@page import="java.util.List"%>
<%@page import="srv.modelo.Veiculo"%>
<%@include file="ValidarLoginAdministrador.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Dados Veiculo</title>
        <link rel="stylesheet" href="css/styleLogin.css" type='text/css'>
        <link rel="stylesheet" href="css/styleContent.css" type='text/css'> 
        <script type="text/javascript" type="text/javascript" src="js/validacoesMascara.js"></script>
        <script type="text/javascript" type="text/javascript" src="js/validacoesJs.js"></script>
        <script type="text/javascript" type="text/javascript" src="cidades-estados-1.0"></script>
    </head>
    <body onload="desabilitaVisualizarVeiculo()">
        <section class="container">
            <div class="containerLogado">
                <!-- A próxima div poderia servir para controle de permissões? -->
                <div class="containerLogadoBorda">
                    <div class="containerLogadoDados">
                        <div class="formularioCadastrarServidor">            
                            <h2>Visualizar Veículo</h2>
                            <form action="ControleVeiculo" name="formVisualizarVeiculo">
                                <div class="containerVisualizarDados">
                                    <ul>
                                        <li>
                                            <div class="formCadastroLabel"><label for="iPlaca">Placa</label> </div>
                                            <div class="formCadastroInput">
                                                <input type="text" id="iPlaca" name="iPlaca" placeholder="PLACA" maxlength="7" value="${placa.placa}"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="iAno">Ano</label> </div>
                                            <div class="formCadastroInput">
                                                <input type="text" id="iAno" name="iAno" placeholder="ANO" maxlength="9" value="${placa.ano}"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="iMarca">Marca</label> </div>
                                            <div class="formCadastroInput">
                                                <input type="text" id="iMarca" name="iMarca" placeholder="MARCA" maxlength="15" value="${placa.marca}"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="iModelo">Modelo</label> </div>
                                            <div class="formCadastroInput">
                                                <input type="text" id="iModelo" name="iModelo" placeholder="MODELO" maxlength="25" value="${placa.modelo}"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel">
                                                <label for="sCombustivel">Combustível</label> </div>
                                            <div class="formCadastroInput">
                                                <label class="radioManutencao" for="sCombustivel">Gasolina</label>
                                                <input type="radio" id="gasolina" name="combustivel" value="g"
                                                       <% if (((Veiculo) request.getAttribute("placa")).getCombustivel().equals("g")) {%>checked<% }%>
                                                       />
                                                <label class="radioManutencao" for="sCombustivel">Álcool</label>
                                                <input type="radio" id="alcool" name="combustivel" value="a"
                                                       <% if (((Veiculo) request.getAttribute("placa")).getCombustivel().equals("a")) {%>checked<% }%>
                                                       />
                                                <label class="radioManutencao" for="sCombustivel">Diesel</label>
                                                <input type="radio" id="diesel" name="combustivel" value="c"
                                                       <% if (((Veiculo) request.getAttribute("placa")).getCombustivel().equals("c")) {%>checked<% }%>
                                                       />
                                                <label class="radioManutencao" for="sCombustivel">GNV</label>
                                                <input type="radio" id="gnv" name="combustivel" value="n"
                                                       <% if (((Veiculo) request.getAttribute("placa")).getCombustivel().equals("n")) {%>checked<% }%>
                                                       />
                                            </div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="iRenavam">Renavam</label> </div>
                                            <div class="formCadastroInput">
                                                <input type="text" id="iRenavam" name="iRenavam" placeholder="RENAVAM" maxlength="11" value="${placa.renavam}"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="iCapacidade">Capacidade</label> </div>
                                            <div class="formCadastroInput">
                                                <input type="text" id="iCapacidade" name="iCapacidade" placeholder="CAPACIDADE" maxlength="2" value="${placa.capacidade}"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="Manutencao">Manutenção</label> </div>
                                            <div class="formCadastroInput"><label class="radioManutencao" for="manutencao" >Sim</label>
                                                <input type="radio" id="manutencaoS" name="manutencao" value="t"
                                                       <% if (((Veiculo) request.getAttribute("placa")).isManutencao()) {%>checked<% }%>          
                                                       />
                                                <label class="radioManutencao" for="Manutencao">Não</label>
                                                <input type="radio" id="manutencaoN" name="manutencao" value="f"
                                                       <% if (!((Veiculo) request.getAttribute("placa")).isManutencao()) {%>checked<% }%>          
                                                       />
                                            </div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="sManDataInicial">Data Inicial (dd-mm-aaaa)</label> </div>
                                            <div class="formCadastroInput"><input type="date" name="sManDataInicial" value="${placa.manutencao_data_inicial}" disabled="disabled"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="sManDataFinal">Data Final (dd-mm-aaaa)</label> </div>
                                            <div class="formCadastroInput"><input type="date" name="sManDataFinal" value="${placa.manutencao_data_final}" disabled="disabled"/></div>
                                        </li>
                                    </ul>
                                </div>
                            </form>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </body>
    </html>