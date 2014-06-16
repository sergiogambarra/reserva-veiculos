
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
    <body onload="funcoesOnloadVeiculo()">
        <section class="container">
            <div class="cabecalho">
                <div class="cabecalhoLateral">
                    <%@include file="cabecalhoNomeUsuario.jsp"%>
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
                        <div class="formularioCadastrarServidor">            
                            <h2>Editar Veículo</h2>
                            <div class="camposObrigatorios">
                                *Campos obrigatórios
                            </div>
                            <form action="ControleVeiculo" name="formCadastroVeiculo">
                                <input type="hidden" name="action" value="atualizarVeiculo"/>
                                <div class="formularioCadastrarServidorBox">
                                    <ul>
                                        <li>
                                            <div class="formCadastroLabel"><label for="iPlaca">*Placa</label> </div>
                                            <div class="formCadastroInput">
                                                <input type="text" id="id" name="iPlaca" placeholder="XXX0000" maxlength="7" value="${placa.placa}" readonly="readonly" onclick="naoAlterarId()"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="iAno">*Ano</label> </div>
                                            <div class="formCadastroInput">
                                                <input type="text" id="iAno" name="iAno" placeholder="ANO" value="${placa.ano}" onKeyPress="return mascaraMatSiap(event);" maxlength="4"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="iMarca">*Marca</label> </div>
                                            <div class="formCadastroInput">
                                                <input type="text" id="iMarca" name="iMarca" placeholder="MARCA" onKeyPress="return mascaraLetras(event);" maxlength="15" value="${placa.marca}"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="iModelo">*Modelo</label> </div>
                                            <div class="formCadastroInput">
                                                <input type="text" id="iModelo" name="iModelo" placeholder="MODELO" maxlength="25" value="${placa.modelo}"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel">
                                                <label for="sCombustivel">*Combustível</label> </div>
                                            <div class="formCadastroInput">
                                                <input type="radio" id="gasolina" name="combustivel" value="g"
                                                       <% if (((Veiculo) request.getAttribute("placa")).getCombustivel().equals("g")) {%>checked<% }%>
                                                       />
                                                <label class="radioManutencao" for="sCombustivel">Gasolina</label>
                                                <input type="radio" id="alcool" name="combustivel" value="a"
                                                       <% if (((Veiculo) request.getAttribute("placa")).getCombustivel().equals("a")) {%>checked<% }%>
                                                       />
                                                <label class="radioManutencao" for="sCombustivel">Álcool</label>
                                                <input type="radio" id="diesel" name="combustivel" value="c"
                                                       <% if (((Veiculo) request.getAttribute("placa")).getCombustivel().equals("c")) {%>checked<% }%>
                                                       />
                                                <label class="radioManutencao" for="sCombustivel">Diesel</label>
                                                <input type="radio" id="gnv" name="combustivel" value="n"
                                                       <% if (((Veiculo) request.getAttribute("placa")).getCombustivel().equals("n")) {%>checked<% }%>
                                                       />
                                                <label class="radioManutencao" for="sCombustivel">GNV</label>
                                                <input type="radio" id="flex" name="combustivel" value="f"
                                                       <% if (((Veiculo) request.getAttribute("placa")).getCombustivel().equals("f")) {%>checked<% }%>
                                                       />
                                                <label class="radioManutencao" for="sCombustivel">Flex</label>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="iRenavam">*Renavam</label> </div>
                                            <div class="formCadastroInput">
                                                <input type="text" id="iRenavam" name="iRenavam" placeholder="RENAVAM" maxlength="11" value="${placa.renavam}" onKeyPress="return mascaraMatSiap(event);"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="iCapacidade">*Capacidade</label> </div>
                                            <div class="formCadastroInput">
                                                <input type="text" id="iCapacidade" name="iCapacidade" placeholder="CAPACIDADE" value="${placa.capacidade}" onKeyPress="return mascaraMatSiap(event);" value="5" min="1" max="50"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel">
                                                <label for="Manutencao">*Manutenção</label>
                                            </div>
                                            <div class="formCadastroInput">
                                                <input type="radio" id="manutencaoS" name="manutencao" value="t"
                                                       <% if (((Veiculo) request.getAttribute("placa")).isManutencao()) {%>checked<% }%>          
                                                       />
                                                <label class="radioManutencao" for="manutencao" >Sim</label>
                                                <input type="radio" id="manutencaoN" name="manutencao" value="f"
                                                       <% if (!((Veiculo) request.getAttribute("placa")).isManutencao()) {%>checked<% }%>          
                                                       />
                                                <label class="radioManutencao" for="Manutencao">Não</label>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="sManDataInicial">Data Inicial</label> </div>
                                            <div class="formCadastroInput"><input type="date" id="sManDataInicial" name="sManDataInicial" placeholder="aaaa-mm-dd" value="${placa.manutencao_data_inicial}"onKeyPress="return mascaraData(event);" maxlength="10" /></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="sManDataFinal">Data Final</label> </div>
                                            <div class="formCadastroInput"><input type="date" id="sManDataFinal" name="sManDataFinal" placeholder="aaaa-mm-dd" value="${placa.manutencao_data_final}" onKeyPress="return mascaraData(event);" maxlength="10"/></div>
                                        </li>
                                        <li>
                                            <div id="btnEditHidden" class="formCadastroLabel"><input type="button" value="Editar" onclick="funcoesOnloadVeiculo()"/></div>
                                                <div id="btnSaveHidden" class="formCadastroLabel"><input type="submit" value="Salvar" onclick="return validarVeiculo()"/></div>
                                                <div class="formCadastroInput"><input type="button" value="Cancelar" onclick="window.location = ('ControleVeiculo?action=listaVeiculos')" /></div>
                                        </li>
                                    </ul>
                                </div>
                            </form>
                            </div>
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